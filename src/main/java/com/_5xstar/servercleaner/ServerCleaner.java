package com._5xstar.servercleaner;

import com._5xstar.servercleaner.constant.Const;
import com._5xstar.servercleaner.impl.ServerCleanerImpl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * 大集合判断器服务接口
 * 庞海文  2024-1-9
 */
public interface ServerCleaner {
    /**
     * 创建BigSetJudger
     */
    ServerCleaner serverCleaner = new ServerCleanerImpl();

    /**
     * 获取数据准备器
     * @param UNIT_SIZE 键的字节长度
     * @param keyRegex  操作文件名和路径匹配正则表达式，文件目录用“/”分隔，可以为空
     * @param group  提取位置
     * @param includeDirNames   所选目录的顺序列表的集合
     * @param excludeRegexes   最终键值排除
     * @param onlyFileName  是否只采用文件名作为键值，true适用于文件不管在什么目录，同一个文件有唯一的文件名
     * @param cantainRoot  当不是只采用文件名时，是否包含操作目录本身，比如操作的是ROOT目录，那么就不可以选择true
     * @param webSites   自己的网址，即“://”到“/”的部分，可以为空
     * @param userName  用户名，可以为空
     * @return
     * @throws IOException
     */
    Preparer getPreparer(final int UNIT_SIZE,
                         final String keyRegex,
                         final int group,
                         final Set<List<String>> includeDirNames,
                         final List<String> excludeRegexes,
                         final boolean onlyFileName,
                         final boolean cantainRoot,
                         final List<String> webSites,
                         final String userName) throws IOException;
    default Preparer getPreparer(final boolean onlyFileName, final boolean cantainRoot, final List<String> excludeRegexes) throws IOException{
        return getPreparer(Const.UNIT_SIZE,
        Const.getImgRegex(),
        Const.imgGroup,
                null,
                excludeRegexes,
                onlyFileName,
                cantainRoot,
        null,
        null);
    }
    default Preparer getPreparer() throws IOException{
        return getPreparer(false,true,null);
    }
    default Preparer getPreparer(final boolean onlyFileName) throws IOException{
        return getPreparer(onlyFileName,true,null);
    }
    default Preparer getPreparer(final List<String> excludeRegexes) throws IOException{
        return getPreparer(false,true,excludeRegexes);
    }
    default Preparer getPreparer(final boolean onlyFileName, final List<String> excludeRegexes) throws IOException{
        return getPreparer(onlyFileName,true,excludeRegexes);
    }
    default Preparer getPreparer(final boolean onlyFileName, final boolean cantainRoot) throws IOException{
        return getPreparer(onlyFileName,cantainRoot,null);
    }
    default Preparer getPreparer(final Set<List<String>> includeDirNames, final List<String> excludeRegexes, final boolean cantainRoot) throws IOException{
        return getPreparer(Const.UNIT_SIZE,
                Const.getImgRegex(),
                Const.imgGroup,
                includeDirNames,
                excludeRegexes,
                false,
                cantainRoot,
                null,
                null);
    }
    default Preparer getPreparer(final Set<List<String>> includeDirNames, final List<String> excludeRegexes) throws IOException{
        return getPreparer(includeDirNames, excludeRegexes, true);
    }

    /**
     * 从序列化文件恢复对象
     * @param serFile 序列化文件
     * @param data 数据文件
     * @return 准备器
     * @throws IOException
     */
    Preparer getPreparerBySerFile(final File serFile, final File data)throws IOException;


    /**
     * 获取判断器
     * @param preparer  数据准备器
     * @param src  供对比的数据文件
     * @return  判断器
     * @throws IOException
     */
    Judger getJudger(final Preparer preparer, final File src) throws IOException;

    /**
     * 获取清理器
     * @param preparer 准备器
     * @param judger  判断器
     * @return 清理器
     * @throws IOException
     */
    Cleaner getCleaner(final Preparer preparer, final Judger judger);


}
