package com._5xstar.servercleaner.impl;

import com._5xstar.servercleaner.Judger;
import com._5xstar.servercleaner.Preparer;
import com._5xstar.servercleaner.util.Util;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.regex.Matcher;

/**
 * 使用准备好的主键集合，比较另一个集合中的元素，是否在集合中
 * 庞海文  2024-1-9
 */
public class JudgerImpl implements Judger {

    //数据单元大小
    final private int UNIT_SIZE;

    //供对比的数据文件
    final private File src;
    final private RandomAccessFile rafSrc;
    //算法判断器
    final private LinkPosJudger itLPF;
    //对比是否为空集，空集时没有意义
    final private boolean isEmpty;
    //操作文件路径匹配器
    final private Matcher matcher;

    final private List<Matcher> excludeMatchers;
    final private boolean excludeOn;
    


    /**
     * 构造函数
     * @UNIT_SIZE 键的字节数
     * @param src  供对比的数据文件
     * @throws IOException
     */
    public JudgerImpl(final Preparer preparer, final File src) throws IOException {
        super();
        this.UNIT_SIZE=preparer.getUNIT_SIZE();
        this.matcher=preparer.getMatcher();
        this.excludeMatchers=preparer.getExcludeMatchers();
        this.excludeOn=this.excludeMatchers!=null && !this.excludeMatchers.isEmpty();
        this.src=src;
        int length;
        if(src!=null) {
            this.rafSrc = new RandomAccessFile(src, "r");  //只读
            length = ((int) rafSrc.length()) / UNIT_SIZE;
        }else{
            this.rafSrc = null;
            length = 0;
        }
        if (length == 0) {
            this.isEmpty=true;
            this.itLPF = null;
        } else {
            this.isEmpty=false;
            this.itLPF = new LinkPosJudgerImpl(this.UNIT_SIZE, rafSrc, length);
        }
        final File[] fs = preparer.getSerFiles();
        if(fs!=null && fs.length==2){//删除序列化对象
            System.out.println("删除序列化对象及数据");
            fs[0].delete();
            fs[1].delete();
        }
    }

    /**
     * 提供检查服务
     * @param strKey
     * @return
     * @throws IOException
     */
    @Override
    public boolean checkIn(final String strKey)throws IOException{
        //System.out.println("checkIn="+strKey);
        if(this.excludeOn && Util.checkIn(strKey, this.excludeMatchers))return true;  //非操作范围
        synchronized (this.matcher){
            this.matcher.reset(strKey);
            if(!this.matcher.find())return true; //不是操作文件，默认是True
        }
        if(isEmpty)return false;  //如果无原集，全部返回false;
        final byte[] tar = Util.createBytKey(this.UNIT_SIZE, strKey);  //创建一个字节数组主键
        return this.itLPF.checkIn(tar);
    }
    
    //对象回收时调用
    private boolean hasClosed = false;  //是否已经关闭
    @Override
    public void close() throws IOException {
        if (hasClosed) return;
        hasClosed = true;
        if (this.rafSrc != null) try {
            this.rafSrc.close();
        } catch (Exception e735) {
        }
        if (this.src != null && this.src.exists()) this.src.delete();
    }
}
