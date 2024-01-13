package com._5xstar.servercleaner.impl;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 通过数组关联文件位置，实现双级查找：第一级：内存里；第二级：文件中
 * 庞海文  2024-1-9 更新
 **/

class LinkPosJudgerImpl  implements LinkPosJudger {

    //全部装载最大长度
    final private static int maxLoadAllNum=2000;

    //文件片段的长度
    final private static int fileUnitSize=100; //个数据单元

    //数据单元大小
    final private int UNIT_SIZE;   

    //数据文件
    private RandomAccessFile raf=null;

    //数据文件长度
    final private int length;     //个数据单元

    //索引数组长度
    final private int arrLength;  //个LinkPos

    //索引数组
    private int[] poses=null;  //位置
    private byte[][] bytKeyes;  //键值

    final private boolean hasLoadAll;  //是否全部加载


    //构造函数  hsLA，如果已经全部加载，hsLA[0]=true;
   LinkPosJudgerImpl(final  int UNIT_SIZE, final RandomAccessFile raf, final int length)throws IOException
    {
        super();
        this.UNIT_SIZE=UNIT_SIZE;
        this.length=length;
        if(length<=maxLoadAllNum){
            this.hasLoadAll=true;
            this.arrLength=length;
            this.bytKeyes=new byte[length][];
            raf.seek(0);
            for(int i=0;i<length;i++){
                this.bytKeyes[i]=new byte[UNIT_SIZE];
                raf.read(this.bytKeyes[i],0,UNIT_SIZE);
            }
        }else{
            this.raf=raf;  //不能全部加载，保留
            this.hasLoadAll=false;
            this.arrLength=length/fileUnitSize+1;
            this.poses=new int[this.arrLength];
            this.bytKeyes=new byte[this.arrLength][];
            raf.seek(0);
            this.poses[0]=0;
            this.bytKeyes[0]=new byte[UNIT_SIZE];
            raf.read(this.bytKeyes[0],0,UNIT_SIZE);
            for(int i=1;i<this.arrLength-1;i++){
                this.poses[i]=i*fileUnitSize;
                raf.seek(this.poses[i]*UNIT_SIZE);
                this.bytKeyes[i]=new byte[UNIT_SIZE];
                raf.read(this.bytKeyes[i],0,UNIT_SIZE);
            }
            final int lstIdx=this.arrLength-1;
            this.poses[lstIdx]=this.length-1;
            raf.seek(this.poses[lstIdx]*UNIT_SIZE);
            this.bytKeyes[lstIdx]=new byte[UNIT_SIZE];
            raf.read(this.bytKeyes[lstIdx],0,UNIT_SIZE);
        }
        System.out.println("hasLoadAll="+hasLoadAll+" length="+length+" arrLength="+arrLength);  //测试
    }

    //检查是否包含
    @Override
    public boolean checkIn(final byte[] bytKey)throws IOException
    {
        //检查头
        byte[] btk=bytKeyes[0];
        int def=0;
        for(int i=0;i<UNIT_SIZE;i++){
            if(btk[i]!=bytKey[i]){
                def=btk[i]-bytKey[i];
                break;
            }
        }
        if(def==0)return true;
        if(def>0 || (hasLoadAll && arrLength==1))return false;  //比第一个小，或只有一个元素

        //检查尾
        btk=bytKeyes[arrLength-1];
        def=0;
        for(int i=0;i<UNIT_SIZE;i++){
            if(btk[i]!=bytKey[i]){
                def=btk[i]-bytKey[i];
                break;
            }
        }
        if(def==0)return true;
        if(def<0 || (hasLoadAll &&  arrLength==2))return false;

        int start=0;
        int end=arrLength-1;
        int mid;
        while( start < end -1 ){    //不相邻，中值不是start和end中一个
            mid=(start+end)/2;
            btk=bytKeyes[mid];
            def=0;
            for(int i=0;i<UNIT_SIZE;i++){
                if(btk[i]!=bytKey[i]){
                    def=btk[i]-bytKey[i];
                    break;
                }
            }
            if(def==0)return true;
            if(def<0){  //中值较小
                start=mid;
            }else{     //中值较大
                end=mid;
            }
        }

        if(!hasLoadAll) {
            //文件检查
            start = poses[start];
            end = poses[end];
            btk = new byte[UNIT_SIZE];
            while (start < end - 1) {
                mid = (start + end) / 2;
                raf.seek(mid * UNIT_SIZE);
                raf.read(btk, 0, UNIT_SIZE);
                def = 0;
                for (int i = 0; i < UNIT_SIZE; i++) {
                    if (btk[i] != bytKey[i]) {
                        def = btk[i] - bytKey[i];
                        break;
                    }
                }
                if (def == 0) return true;
                if (def < 0) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }
        return false;
    }
}
