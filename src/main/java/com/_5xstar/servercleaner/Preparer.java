package com._5xstar.servercleaner;

import com._5xstar.servercleaner.util.Util;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;

/**
 * 从字符串生成大集合文件
 * 庞海文  2024-1-9
 */
public interface Preparer extends Closeable, Serializable {

    /**
     * 获取序列化文件，如果需要保留这两个文件，请在获取判断器前备份，否则判断器构建成功后删除
     * @return Preparer对象，数据
     */
    File[] getSerFiles();

    /**
     * 获取键的字节长度
     * @return
     */
    int getUNIT_SIZE();

    /**
     * 获取主键文件匹配器
     * @return
     */
    Matcher getMatcher();
    int getGroup();

    /**
     * 获取不操作的正则匹配器集合
     * @return
     */
    List<Matcher> getExcludeMatchers();

    /**
     * 搜索网页时用，如果不为null或空，其他目录的图片如果不在excludeMatchers中设置会被删除
     * @return
     */
    Set<List<String>> getIncludeDirNames();

    /**
     * 是否只取文件名
     * @return
     */
    boolean getOnlyFileName();

    /**
     * 获取操作网址列表
     * @return
     */
    List<String> getWebSites();

    /**
     *
     * @return
     */
    boolean getCantainRoot();

    /**
     * 加入一个主键
     * @param strKey
     * @throws IOException
     */
    void addSeq(final String strKey) throws IOException;

    /**
     * @param url 文件链接
     * @param base 路径
     * @throws IOException
     */
    default void addSeqFromUrl(String url, final String base) throws IOException {
        //System.out.println("addSeqFromUrl="+url);  //测试
        if (url == null) return;
        final boolean onlyFileName = getOnlyFileName();
        final List<String> webSites = getWebSites();
        int idx = url.indexOf("://");
        if(idx>-1){  //默认协议头
            int start = idx + 3;
            int end = url.indexOf("/", start);
            if(end < 0)return;  //无网站
            if(webSites!=null && !webSites.isEmpty() && !webSites.contains(url.substring(start,end)))return;  //过滤外链接
            if(onlyFileName) {  //只取文件名
                idx = url.lastIndexOf("/", ++end);
                if (idx < 0) addSeq(url.substring(end));
                else addSeq(url.substring(idx + 1));
                return;
            }
            url = url.substring(end+1);
            if(url==null || "".equals(url=url.trim()))return;
            url = url.replaceAll("//[/]*", "/");
            if(url.startsWith("/"))url = url.substring(1);
        }else{
            url = url.replaceAll("//[/]*", "/");
            if(onlyFileName) {  //只取文件名
                idx = url.lastIndexOf("/");
                if (idx < 0) addSeq(url);
                else addSeq(url.substring(idx + 1));
                return;
            }
            if(url.startsWith("/"))url = url.substring(1);
            else if(base!=null)url=base+url;
        }
        addSeq(url);
    }
    default void addSeqFromUrl(final String url) throws IOException {
        addSeqFromUrl(  url,  null);  //默认情况是采用path，即非“/”开头
    }

    /**
     * 从超文本中获取图片主键
     * @param html  超文本
     * @param base  路径基础，onlyFileName=false有效
     * @throws IOException
     */
    default void addSeqsFromHtml(final String html, final String base) throws IOException {
        if(html==null)return;
        final ArrayList<String> urls = new ArrayList<>();
        final Matcher matcher = getMatcher();
        final int group = getGroup();
        synchronized (matcher){
            matcher.reset(html);
            int start = 0;
            String url;
            while (matcher.find(start)) {
                urls.add(matcher.group(group).trim());
                start = matcher.end();
            }
        }
        if(!urls.isEmpty()){
            for(String url : urls) addSeqFromUrl(url, base);
        }
    }
    default void addSeqsFromHtml(final String html) throws IOException {
        addSeqsFromHtml(html, null);
    }

    /**
     * 用某个目录准备文件
     * @param filter  超文本文件后缀过滤器
     * @param htmlMatcher   超文本文件后缀过滤器用的正则匹配器
     * @param dir   文件目录
     * @param parentLevel  父目录层
     * @throws IOException
     */
    default void prepare(HtmlFileFilter filter, Matcher htmlMatcher, final File dir, final int parentLevel)throws IOException{
        if(dir==null || !dir.exists() || dir.isFile())return;
        final HtmlFileFilter _filter = filter==null?HtmlFileFilter.defaultFilter:filter;
        Handler handler;
        if(getOnlyFileName()){  //适用于文件名是唯一编码的情况
            handler = new Handler() {
                @Override
                public boolean fileHandle(String parentPath, String fileName, File file) throws IOException {
                    if(_filter.checkByFileName(htmlMatcher,fileName))addSeqsFromHtml(  Util.getText(file),  null);
                    return false;
                }
            };
        }else {  //适用于文件名没有唯一编码的情况
            handler = new Handler() {
                @Override
                public boolean fileHandle(String parentPath, String fileName, File file) throws IOException {
                    if (_filter.checkByFileName(htmlMatcher, fileName)) {
                        addSeqsFromHtml(Util.getText(file), parentPath);
                    }
                    return false;
                }
            };
        }
        Util.treeTraversal(dir, parentLevel, getIncludeDirNames(), getOnlyFileName(),getCantainRoot(),handler);
    }
    default void prepare(HtmlFileFilter filter, Matcher htmlMatcher, final File dir)throws IOException{
        prepare( filter, htmlMatcher,   dir,   0);
    }
    default void prepare(final File dir)throws IOException{
        prepare( null, null,   dir,   0);
    }
    default void prepare(final File dir, final int parentLevel)throws IOException{
        prepare( null, null,   dir,   parentLevel);
    }

    //准备完成
    File finished() throws IOException;

}
