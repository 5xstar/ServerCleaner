package com._5xstar.servercleaner.impl;

import com._5xstar.servercleaner.Cleaner;
import com._5xstar.servercleaner.Judger;
import com._5xstar.servercleaner.Preparer;

/**
 * 清理器的实现
 * 庞海文 2024-1-11
 */
public class CleanerImpl implements Cleaner {

    final private Preparer preparer;
    /**
     * 获取准备器
     * @return
     */
    public Preparer getPreparer(){
        return this.preparer;
    }

    final private Judger judger;
    /**
     * 获取判断器
     * @return
     */
    public Judger getJudger(){
        return this.judger;
    }

    /**
     * 构造函数
     * @param preparer
     * @param judger
     */
    public CleanerImpl(final Preparer preparer, final Judger judger){
        this.preparer=preparer;
        this.judger=judger;
    }

}
