package com._5xstar.servercleaner.impl;

import com._5xstar.servercleaner.ServerCleaner;
import com._5xstar.servercleaner.Cleaner;
import com._5xstar.servercleaner.Judger;
import com._5xstar.servercleaner.Preparer;
import com._5xstar.servercleaner.util.Util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class ServerCleanerImpl implements ServerCleaner {
    @Override
    public Preparer getPreparer(final int UNIT_SIZE,
                                final String keyRegex,
                                final int group,
                                final Set<List<String>> includeDirNames,
                                final List<String> excludeRegexes,
                                final boolean onlyFileName,
                                final boolean cantainRoot,
                                final List<String> webSites,
                                final String userName) throws IOException {
        return new PreparerImpl(UNIT_SIZE, keyRegex, group, includeDirNames, excludeRegexes, onlyFileName, cantainRoot, webSites, userName);
    }

    /**
     * 从序列化文件恢复对象
     * @param serFile 序列化文件
     * @param data 数据文件
     * @return 准备器
     * @throws IOException
     */
    @Override
    public Preparer getPreparerBySerFile(File serFile, File data) throws IOException {
        if(serFile==null || !serFile.exists() || serFile.isDirectory()
           || data==null || !data.exists() || data.isDirectory())throw new IOException("文件不存在或是目录！");
        PreparerImpl preparer = Util.getObject(serFile, PreparerImpl.class);
        preparer.init(data);
        return preparer;
    }

    @Override
    public Judger getJudger( Preparer preparer, File src) throws IOException {
        assert (preparer!=null);
        return new JudgerImpl(  preparer,   src);
    }

    @Override
    public Cleaner getCleaner(Preparer preparer, Judger judger) {
        assert (preparer!=null && judger!=null);
        return new CleanerImpl(preparer,judger);
    }
}
