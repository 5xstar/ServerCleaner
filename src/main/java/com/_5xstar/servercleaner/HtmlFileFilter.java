package com._5xstar.servercleaner;

import com._5xstar.servercleaner.constant.Const;

import java.util.regex.Matcher;

/**
 * Html文件过滤器
 * 庞海文 2024-1-11
 */
public interface HtmlFileFilter {

    /**
     * 默认超文本过滤器
     */
    HtmlFileFilter defaultFilter = new HtmlFileFilter(){};

    /**
     * 判断文件是否是超文本
     * @param htmlMatcher  超文本文件名的匹配正则匹配器
     * @param fileName  文件名
     * @return  是超文本
     */
    default boolean checkByFileName(Matcher htmlMatcher, final String fileName){
        assert (fileName!=null);
        if(htmlMatcher==null)htmlMatcher= Const.getHtmlMatcher();
        synchronized (htmlMatcher) {
            htmlMatcher.reset(fileName);
            return htmlMatcher.find();
        }
    }
    default boolean checkByFileName(final String fileName){
        return checkByFileName(null, fileName);
    }
}
