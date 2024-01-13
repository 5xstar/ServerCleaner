package com._5xstar.servercleaner.impl;

import com._5xstar.servercleaner.Preparer;
import com._5xstar.servercleaner.constant.Const;
import com._5xstar.servercleaner.util.Util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 从字符串生成BAK
 * 庞海文  2024-1-8
 */
public class PreparerImpl implements Preparer {
    final private static int pkgScLth = 500;  //字节数组主键数量
    
    //主键单位
    private int UNIT_SIZE;


    @Override
    public int getUNIT_SIZE(){
        return this.UNIT_SIZE;
    }

    final private String keyRegex;
    private transient Matcher matcher;
    @Override
    public Matcher getMatcher() {
        return matcher;
    }

    final private transient int group;
    @Override
    public int getGroup() {
        return group;
    }

    final private List<String> excludeRegexes;
    private transient List<Matcher> excludeMatchers;
    @Override
    public List<Matcher> getExcludeMatchers(){
        return this.excludeMatchers;
    }

    private transient Set<List<String>> includeDirNames=null;
    /**
     * 搜索网页时用，如果不为null或空，其他目录的图片如果不在excludeMatchers中设置会被删除
     * @return
     */
    @Override
    public Set<List<String>> getIncludeDirNames() {
        return this.includeDirNames;
    }

    private transient boolean excludeOn;

    final private boolean onlyFileName;
    @Override
    public boolean getOnlyFileName() {
        return onlyFileName;
    }
    final private boolean cantainRoot;
    @Override
    public boolean getCantainRoot(){
        return this.cantainRoot;
    }

    final private List<String> webSites;
    @Override
    public List<String> getWebSites() {
        return webSites;
    }

    //用户名
    final private String userName;
    //顺序加入主键
    private transient byte[][] pkgSc = new byte[pkgScLth][];
    //主键集合元素数
    private transient int pkgScIdx = 0;

    //临时文件:用于建立主键顺序文件
    private transient File srcFile;
    private transient File tarFile;


    //用户顺序号
    private static int userNum = 0;

    //是否序列化生成
    private transient boolean isSer = false;

    private transient File[] serFiles = null;
    /**
     * 获取序列化文件
     * @return Preparer对象，数据
     */
    @Override
    public File[] getSerFiles() {
        return this.serFiles;
    }

    /**
     * 构造函数
     * @UNIT_SIZE  键的字节长度
     * @param userName 用户名
     * @throws IOException
     */
    public PreparerImpl(final int UNIT_SIZE,
                        final String keyRegex,
                        final int group,
                        final Set<List<String>> includeDirNames,
                        final List<String> excludeRegexes,
                        final boolean onlyFileName,
                        final boolean cantainRoot,
                        final List<String> webSites,
                        final String userName) throws IOException {
        this.UNIT_SIZE=UNIT_SIZE;
        if(keyRegex==null){
            this.keyRegex=Const.getImgRegex();
            this.group=Const.imgGroup;
        }else{
            this.keyRegex=keyRegex;
            this.group=group;
        }
        this.includeDirNames = includeDirNames;
        this.excludeRegexes=excludeRegexes;
        this.onlyFileName=onlyFileName;
        this.cantainRoot=cantainRoot;
        this.webSites=webSites;
        this.userName = userName==null?"":userName;
        init();
    }
    private void init(){
        this.matcher= Pattern.compile(this.keyRegex,Pattern.CASE_INSENSITIVE).matcher("");
        this.excludeOn = this.excludeRegexes!=null && !this.excludeRegexes.isEmpty();
        if(this.excludeOn){
            this.excludeMatchers = new ArrayList<>();
            for(String regex : this.excludeRegexes)
                this.excludeMatchers.add(Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(""));
        }
        final String sk = Const.prepareFileHead + this.userName.hashCode() + "_"+(++userNum);
        this.srcFile = new File(Const.prepareFilePath, sk + ".p1");
        if (this.srcFile.exists()) this.srcFile.delete();
        this.tarFile = new File(Const.prepareFilePath, sk + ".p2");
        if (this.tarFile.exists()) this.tarFile.delete();
    }

    void init(final File data)throws IOException{
        FileInputStream in = null;
        FileOutputStream out = null;
        try{
            in = new FileInputStream(data);
            final int us = Util.readInt(in);
            if(us!=this.UNIT_SIZE)throw new IOException("键值单位长度不匹配");
            init();
            out = new FileOutputStream(this.tarFile);
            final byte[] dt = new byte[this.UNIT_SIZE];
            final int length = ((int)data.length()-Integer.BYTES)/this.UNIT_SIZE;
            for(int i = 0; i < length; i++ ){
                in.read(dt);
                out.write(dt);
            }
            isSer = true;
        }finally {
            if(in!=null)try{in.close();}catch (Exception e){}
            if(out!=null)try{out.close();}catch (Exception e){}
        }
    }

    /**
     * 加入一个主键
     * @param strKey
     * @throws IOException
     */
    @Override
    public synchronized void addSeq(final String strKey) throws IOException {
        System.out.println("addSeq="+strKey);  //测试
        if(this.excludeOn && Util.checkIn(strKey, this.excludeMatchers))return;  //非操作范围
        final int length = strKey.length();
        if(length>this.UNIT_SIZE){
            //throw new IOException("“"+strKey+"”长度超过“"+UNIT_SIZE+"”。");  //长度超标
            final int new_UNIT_SIZE=length+Const.UNIT_SIZE_SAFE;
            change(new_UNIT_SIZE);  //改变文件的键长和数组的键长
            this.UNIT_SIZE=new_UNIT_SIZE;
        }

        final byte[] tar = Util.createBytKey(UNIT_SIZE, strKey);  //创建一个字节数组主键

        if (pkgScIdx == 0) {
            pkgSc[pkgScIdx++] = tar;   //第一个直接加入就行
            return;
        }

        //检查头
        byte[] src = pkgSc[0];
        int def = 0;
        for (int i = 0; i < UNIT_SIZE; i++) {
            if (src[i] != tar[i]) {
                def = src[i] - tar[i];
                break;
            }
        }
        if (def > 0) {  //比第0个小，进入首位
            for(int i = pkgScIdx; i > 0; i--)pkgSc[i]=pkgSc[i-1]; //移动
            pkgSc[0] = tar;
            pkgScIdx++;
        } else if (def < 0) {   //比第0个大，忽略相等的
            if (pkgScIdx == 1) {  //原只有一个，加入第二位即可
                pkgSc[pkgScIdx++] = tar;
            } else {
                //检查尾
                src = pkgSc[pkgScIdx - 1];  //取出最后值
                def = 0;  //重置比较值
                for (int i = 0; i < UNIT_SIZE; i++) {
                    if (src[i] != tar[i]) {
                        def = src[i] - tar[i];
                        break;
                    }
                }
                if (def < 0) { //比最后1个大，直接放入最后
                    pkgSc[pkgScIdx++] = tar;
                } else if (def > 0) {   //比最后1个小
                    //内部检查
                    int start = 0;
                    int end = pkgScIdx - 1;
                    int mid;
                    while (start < end - 1) {  //推进位置
                        mid = (start + end) / 2;
                        src = pkgSc[mid];
                        def = 0;
                        for (int i = 0; i < UNIT_SIZE; i++) {
                            if (src[i] != tar[i]) {
                                def = src[i] - tar[i];
                                break;
                            }
                        }
                        if (def < 0) {   //小了，在中点之后
                            start = mid;
                        } else if (def > 0) {  //大了，在中点之前
                            end = mid;
                        } else {
                            return;  //找到相等的，忽略
                        }
                    }
                    for(int i = pkgScIdx; i > end; i--)pkgSc[i]=pkgSc[i-1]; //移动
                    pkgSc[end] = tar;
                    pkgScIdx++;
                }
            }
        }

        if (pkgScIdx == pkgScLth) {  //数据包满,写入文件
            write(); //写入数据
            pkgScIdx = 0;
        }

    }


    //完成一页，把主键写入文件
    private boolean isFirstPage = true;  //是否第一页
    private void write() throws IOException {
        //已写入的合集
        RandomAccessFile p1_RAF = null;
        //输出的合集
        RandomAccessFile p2_out = null;
        //已写入的合集的长度
        int p1_length;
        try {
            if (isFirstPage) {  //第一页，直接写入目标文件
                isFirstPage = false;
                p1_length = 0;
            } else {
                //交换文件
                final File temp = this.srcFile;
                this.srcFile = this.tarFile;
                this.tarFile = temp;
                //原集合
                p1_RAF = new RandomAccessFile(this.srcFile, "r");
                p1_length = ((int) p1_RAF.length()) / UNIT_SIZE;
            }
            //目标集合
            p2_out = new RandomAccessFile(this.tarFile, "rw");  //合集

            //System.out.println("p1_length="+p1_length+" pkgScIdx="+pkgScIdx);

            p2_out.seek(0);  //把目标文件指针放在文件开始位置
            if (p1_length == 0) {  //把第一个数组写入文件
                for (int i = 0; i < pkgScIdx; i++) {
                    p2_out.write(pkgSc[i], 0, UNIT_SIZE);
                }
            } else {
                p1_RAF.seek(0);  //把源文件指针放在文件开始位置
                //合并数据
                int def;  //比较值
                int j = 0;
                final byte[] data1 = new byte[UNIT_SIZE];
                p1_RAF.read(data1, 0, UNIT_SIZE);  //读出源文件中的第一个主键
                boolean twoF = false;
                int i = 0;
                for (; i < pkgScIdx; i++) {
                    if (twoF) break;
                    byte[] data2 = pkgSc[i];
                    def = 0;
                    for (int k = 0; k < UNIT_SIZE; k++) {
                        if (data2[k] != data1[k]) {
                            def = data2[k] - data1[k];
                            break;
                        }
                    }
                    if (def > 0) {
                        p2_out.write(data1, 0, UNIT_SIZE);
                        j++;
                        for (; j < p1_length; j++) {
                            p1_RAF.read(data1, 0, UNIT_SIZE);
                            def = 0;
                            for (int k = 0; k < UNIT_SIZE; k++) {
                                if (data2[k] != data1[k]) {
                                    def = data2[k] - data1[k];
                                    break;
                                }
                            }
                            if (def > 0) {
                                p2_out.write(data1, 0, UNIT_SIZE);
                            } else {
                                if (def == 0) {
                                    j++;
                                    if (j < p1_length) p1_RAF.read(data1, 0, UNIT_SIZE);
                                }
                                break;
                            }
                        }
                    } else {
                        if (def == 0) {
                            j++;
                            if (j < p1_length) p1_RAF.read(data1, 0, UNIT_SIZE);
                        }
                    }
                    p2_out.write(data2, 0, UNIT_SIZE);
                    if (j == p1_length) {  //1已经写入最后1个元素,本循环体结束
                        twoF = true;
                    }
                }
                if (twoF) {
                    for (; i < pkgScIdx; i++) p2_out.write(pkgSc[i], 0, UNIT_SIZE);
                } else {
                    p2_out.write(data1, 0, UNIT_SIZE);
                    for (++j; j < p1_length; j++) {
                        p1_RAF.read(data1, 0, UNIT_SIZE);
                        p2_out.write(data1, 0, UNIT_SIZE);
                    }
                }
                //System.out.println("i="+i+" j="+j+" twoF="+twoF+" pos="+p1_RAF.getFilePointer()/UNIT_SIZE +" p1_length="+p1_length+" pkgScIdx="+pkgScIdx);  //测试
            }
        } finally {
            if (p1_RAF != null) try {
                p1_RAF.close();
            } catch (IOException e) {
            }
            if (p2_out != null) try {
                p2_out.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * 改变文件键的值
     * @param new_UNIT_SIZE
     */
    private void change(final int new_UNIT_SIZE) throws IOException{
        if(pkgScIdx>0){  //改变数组
            final byte[][] temp_pkgSc = pkgSc;
            pkgSc = new byte[pkgScLth][];
            final int size = new_UNIT_SIZE-this.UNIT_SIZE;
            final byte[] data = new byte[size];
            for(int i = 0; i < size; i++)data[i]=(byte)0;
            byte[] data1;
            for(int i = 0; i < pkgScIdx; i++){
                data1 = new byte[new_UNIT_SIZE];
                System.arraycopy(temp_pkgSc[i],0, data1, 0, this.UNIT_SIZE);
                System.arraycopy(data,0,data1, this.UNIT_SIZE, size);
                pkgSc[i]=data1;
            }
        }
        if(!this.tarFile.exists())return;
        //交换文件
        final File temp = this.srcFile;
        this.srcFile = this.tarFile;
        this.tarFile = temp;
        final int length = (int)this.srcFile.length()/this.UNIT_SIZE;
        FileInputStream in = null;
        FileOutputStream out = null;
        try{
            in = new FileInputStream(this.srcFile);
            out = new FileOutputStream(this.tarFile);
            final byte[] data = new byte[new_UNIT_SIZE];
            for(int i = this.UNIT_SIZE; i < new_UNIT_SIZE; i++)data[i]=(byte)0;  //初始化填充值
            for(int i = 0; i < length; i++){
                in.read(data,i*this.UNIT_SIZE, this.UNIT_SIZE);
                out.write(data);
            }
        }finally{
            if(in!=null)try{in.close();}catch (Exception e){}
            if(out!=null)try{out.close();}catch (Exception e){}
        }
    }

    //准备完成
    @Override
    public File finished() throws IOException {
        //写入一下数组
        if (pkgScIdx > 0) {
            write();
        }
        //删除 srcFile
        this.srcFile.delete();
        this.srcFile=null;  //阻止对象回收时删除输出的文件。
        if(!isSer){
            this.serFiles = new File[]{new File(this.tarFile.getAbsolutePath()+".obj"),new File(this.tarFile.getAbsolutePath()+".dat")};
            Util.writeObject(this.serFiles[0], this);  //写入该对象
            FileOutputStream out = null;
            FileInputStream in = null;
            try{
                out = new FileOutputStream(this.serFiles[1]);
                Util.writeInt(this.UNIT_SIZE, out);
                in = new FileInputStream(this.tarFile);
                final byte[] dt = new byte[this.UNIT_SIZE];
                final int length = (int)this.tarFile.length()/this.UNIT_SIZE;
                for(int i = 0; i < length; i++){
                    in.read(dt);
                    out.write(dt);
                }
            }finally{
                if(out!=null)try{out.close();}catch (Exception e){}
                if(in!=null)try{in.close();}catch (Exception e){}
            }
        }
        return this.tarFile;
    }

    @Override
    public void close() throws IOException {
        if (this.srcFile != null)  {  //对象没有被完整使用后丢弃
            this.srcFile.delete();
            this.srcFile=null;
            this.tarFile.delete();
        }
    }
}
