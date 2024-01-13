package com._5xstar.servercleaner.constant;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用到的常数
 * 庞海文  2024-1-8
 */
public class Const {
    /**
     * 默认键的字节数
     */
    public static int UNIT_SIZE=16;
    /**
     * 键的字节数，每次调整的安全距离
     */
    public static int UNIT_SIZE_SAFE=8;
    /**
     * 准备数据用
     */
    public static String  prepareFileHead = "temp_";
    public static String prepareFilePath = null;  //临时数据放在运行根目录
    /**
     * 默认网页匹配器，图片文件
     */
    //图片
    public static String[] imageTypes=new String[]{"jpg", "jpeg", "gif", "png", "bmp", "tif", "pcx", "tga", "exif", "fpx", "svg", "psd", "cdr", "pcd", "dxf", "ufo", "eps", "ai", "raw", "WMF", "webp", "avif", "apng"};
    private static String imgRegex=null;
    public static String getImgRegex(){
        if(imgRegex==null) {
            final StringBuilder sb = new StringBuilder();
            sb.append("([\\w\\.\\/\\-\\!%\\$]+?\\.(?:");
            for (int i = 0; i < imageTypes.length - 1; i++) sb.append(imageTypes[i]).append('|');
            sb.append(imageTypes[imageTypes.length - 1]).append("))(?=[^\\w\\.\\/\\-\\!%\\$]+|$)");
            imgRegex=sb.toString();
        }
        return imgRegex;
    }
    public static int imgGroup = 1;

    /**
     * 超文本文件名过滤正则表达式
     */
    public static String htmlRegex = "\\.(?:htm[l]*|[aj]sp|php|vm|css|js)$";
    public static Matcher htmlMatcher=null;
    public static Matcher getHtmlMatcher(){
        if(htmlMatcher==null){
            htmlMatcher=Pattern.compile(htmlRegex, Pattern.CASE_INSENSITIVE).matcher("");
        }
        return htmlMatcher;
    }

    static {
        System.out.println("start to properties");
        final Properties p = new Properties();
        FileInputStream in = null;
        try{
            File propFile = new File("com/_5xstar/servercleaner/constant/Const.properties");  //检查运行目录
            System.out.println("run path propFile = " + propFile.getAbsolutePath());
            if(!propFile.exists()) {
                final URL url = Const.class.getResource("com/_5xstar/servercleaner/constant/Const.properties");
                System.out.println("url=" + url);
                if(url!=null){
                    final File _propFile = new File(url.getFile());
                    if(_propFile.exists()){  //写出运行目录
                        FileUtils.copyFile(_propFile, propFile);
                        propFile=_propFile;
                    }
                }
            }
            if(propFile.exists()) {
                in = new FileInputStream(propFile);
                p.load(in);
                String s = p.getProperty("UNIT_SIZE");
                if (s != null) {
                    try {
                        UNIT_SIZE = Integer.parseInt(s);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("UNIT_SIZE=" + UNIT_SIZE);
                s = p.getProperty("UNIT_SIZE_SAFE");
                if (s != null) {
                    try {
                        UNIT_SIZE_SAFE = Integer.parseInt(s);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("UNIT_SIZE_SAFE=" + UNIT_SIZE_SAFE);
                s = p.getProperty("prepareFileHead");
                if (s != null) prepareFileHead = s;
                System.out.println("prepareFileHead=" + prepareFileHead);
                s = p.getProperty("prepareFilePath");
                if (s != null) prepareFilePath = s;
                System.out.println("prepareFilePath=" + prepareFilePath);
                s = p.getProperty("imageTypes");
                if (s != null) {
                    imageTypes = s.split(",");
                }
                System.out.print("imageTypes=[" + imageTypes[0]);
                for (int i = 1; i < imageTypes.length; i++) System.out.print(", " + imageTypes[i]);
                System.out.println("]");
                s = p.getProperty("imgGroup");
                if (s != null) {
                    try {
                        imgGroup = Integer.parseInt(s);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("imgGroup=" + imgGroup);
                s = p.getProperty("htmlRegex");
                if (s != null) htmlRegex = s;
                System.out.println("htmlRegex=" + htmlRegex);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(in!=null)try{in.close();}catch (IOException e){}
        }
    }


}
