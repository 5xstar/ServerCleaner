package com._5xstar.servercleaner;

import com._5xstar.servercleaner.util.Util;

import java.io.File;
import java.io.IOException;

/**
 * 判断文件是否在主键集中，进行删除
 * 庞海文  2024-1-10
 */
public interface Cleaner {

    /**
     * 获取准备器
     * @return
     */
    Preparer getPreparer();

    /**
     * 获取判断器
     * @return
     */
    Judger getJudger();


    /**
     * 默认清理方法提示删除，事件不干什么，要什么操作请覆盖该方法
     * @param f 待清理文件
     * @return
     */
    default boolean clean(final File f){
        System.out.println(f.getAbsolutePath() + " had cleaned!");
        return true;  //f.delete();
    }
    /**
     *适用于文件名是唯一编码的情况
     * @param dir  文件目录
     * @param parentLevel 逆推目录层数
     * @return
     */
    default void cleanDir(final File dir, final int parentLevel)throws IOException {
        if(dir==null || !dir.exists() || dir.isFile())return;
        Handler handler;
        if(getPreparer().getOnlyFileName()){  //分层遍历法获取全部文件名
            handler = new Handler() {
                @Override
                public boolean dirHandle(File dir) throws IOException {
                    return clean(dir);  //删除空目录;
                }

                @Override
                public boolean fileHandle(String parentPath, String fileName, File file) throws IOException {
                    if(!getJudger().checkIn(fileName)){
                        return clean(file);
                    }
                    return false;
                }
            };
        }else{
            //分层遍历法获取全部文件名
            handler = new Handler() {
                @Override
                public boolean dirHandle(File dir) throws IOException {
                    return clean(dir);  //删除空目录;
                }
                @Override
                public boolean fileHandle(String parentPath, String fileName, File file) throws IOException {
                    if(!getJudger().checkIn(parentPath + fileName)){
                        return clean(file);
                    }
                    return false;
                }
            };
        }
        Util.treeTraversal(dir,parentLevel, null, getPreparer().getOnlyFileName(), getPreparer().getCantainRoot(), handler);
    }
    default void cleanDir(final File dir)throws IOException {
        cleanDir(dir, 0);
    }
}
