package com._5xstar.servercleaner.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;

import com._5xstar.servercleaner.Handler;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.input.BOMInputStream;

/**
 * 工具
 * 庞海文  2024-1-8 改
**/
public class Util {

    /**
     * 创建字节数组
     * @param strKey
     * @return
     */
    public static byte[] createBytKey(final int UNIT_SIZE, final String strKey){
        final int lth=strKey.length();
        final int end = lth<UNIT_SIZE?lth:UNIT_SIZE ;
        final char[] dst=new char[end];
        strKey.getChars(0,end,dst,0);
        final byte[] _bytKey=new byte[UNIT_SIZE ];
        for(int i = 0; i < end; i++){
            _bytKey[i]=(byte)dst[i];  //不用.getBytes()
        }
        if(lth<UNIT_SIZE ){
            for(int i=lth;i<UNIT_SIZE ;i++){
                _bytKey[i]=(byte)0;
            }
        }
        return _bytKey;
    }

    /**
     * 读取文本文件中的文本
     * @param htmlFile  文件
     * @return  文本
     * @throws IOException
     */
    public static String getText(final File htmlFile)throws IOException{
        return FileUtils.readFileToString(htmlFile, detectEncoding(htmlFile));
    }
    private static String detectEncoding(final File f) throws IOException {
        try(InputStream inputStream = new FileInputStream(f);
            BOMInputStream bomInputStream = BOMInputStream.builder().setInputStream(inputStream).get();
           )
        {
            try {
                if (bomInputStream.hasBOM()) {
                    return bomInputStream.getBOMCharsetName();
                } else {
                    return null;  //采用默认字符集
                }
            }finally{
                try{bomInputStream.close();}catch (Exception e){}
                try{inputStream.close();}catch (Exception e){}
            }
        }
    }

    /**
     * 父目录层次转换为字符串
     * @param dir
     * @param parentLevel
     * @return
     */
    private static String getBase(final File dir, int parentLevel) {
        String base;
        if(parentLevel != 0){
            if(parentLevel<0)parentLevel=-parentLevel;
            base = "/";
            File parent = dir;
            for(int i = 0; i < parentLevel-1; i++){
                parent = parent.getParentFile();
                base = "/"+parent.getName()+base;
            }
            base = parent.getParentFile().getName() + base;
        }else{
            base = "";
        }
        return base;
    }

    /**
     * 文件目录树分层遍历
     * @param root  被遍历的根目录
     * @param parentLevel  根目录到站点根目录的距离，onlyFileName=false有效，
     *                     例如：Tomcat站点webapps/test1/test2/root的parentLevel=2或-2；
     *                           webapps/ROOT/test2/root的parentLevel=1或-1；
     * @param onlyFileName  是否仅获取文件名，对于资源有唯一文件名的站点适用，例如图片空间
     * @param cantainRoot   onlyFileName=false有效，路径中是否包含参数root的名称，
     *                      除了root参数是默认站点，例如Tomcat站点webapps/ROOT，否则都是true
     * @param handler  文件或目录处理器
     * @throws IOException
     */
    public static void treeTraversal(final File root,
                                     final int parentLevel,  //cantainRoot=true，该参数才起作用
                                     final Set<List<String>> includeDirNames,
                                     final boolean onlyFileName,
                                     final boolean cantainRoot,
                                     final Handler handler)throws IOException{
        if(root==null || !root.exists() || root.isFile())return;
        final boolean includeOn = includeDirNames!=null && !includeDirNames.isEmpty();
        int level = 0;

            //分层遍历法获取全部文件名
            ArrayList<File> ds1 = new ArrayList<>();   //上一层目录
            ArrayList<List<String>> ps1 = new ArrayList<>();   //上一层对应路径列表
            String base;
            if(!onlyFileName && cantainRoot) {
                base = getBase(root, parentLevel);
            }else{
                base = "";
            }
            List<String> p = new ArrayList<>();
            p.add(root.getName());
            if(includeOn && !checkInclude(p, level, includeDirNames)){
                throw new IOException("根目录不在获取中");
            }
            ds1.add(root);
            ps1.add(p);
            ArrayList<File> ds2 = new ArrayList<>();   //下一层目录
            ArrayList<List<String>> ps2 = new ArrayList<>();   //下一层对应路径列表
            String[] lst;
            File d, f;
            List<String> np;
            int dn;
            boolean delDir;
            while (!ds1.isEmpty()) {
                level++;  //第一层开始
                for (int i = 0; i < ds1.size(); i++) {
                    d = ds1.get(i);
                    p = ps1.get(i);
                    lst = d.list();
                    if (lst != null && lst.length > 0) {
                        dn = 0;
                        for (String n : lst) {
                            f = new File(d, n);
                            if (f.isFile()) {
                                if(handler.fileHandle(onlyFileName?null:buildPath(base,p), n, f))dn++;
                            } else {
                                np = new ArrayList<>(p);
                                np.add(n);
                                if(includeOn && !checkInclude(np, level, includeDirNames)){
                                    continue;  //目录非获取
                                }
                                ds2.add(f);
                                ps2.add(np);
                            }
                        }
                        delDir = dn == lst.length;
                    }else{
                        delDir =true;
                    }
                    if(delDir)handler.dirHandle(d);  //对空目录进行处理
                }
                ds1 = ds2;
                ps1 = ps2;
                ds2 = new ArrayList<>();
                ps2 = new ArrayList<>();
            }

    }

    private static boolean checkInclude(final List<String> dirNames, final int level, final Set<List<String>> includeDirNames){
        for(List<String> ls : includeDirNames){
            if(checkInclude( dirNames,  level, ls))return true;
        }
        return false;
    }
    private static boolean checkInclude(final List<String> dirNames, final int level, final List<String> ls){
        for(int i = 0; i < ls.size() && i < level+1; i++){
            if(!dirNames.get(i).equals(ls.get(i)))return false;
        }
        return true;  //在可对比的范围内没发现不匹配
    }
    private static String buildPath(final String base, final List<String> p){
        return base + String.join("/", p) + "/";
    }

    /**
     * 获取序列化对象
     * @param serFile 序列化文件
     * @return 对象
     * @throws IOException
     */
    public static <T extends Serializable> T getObject(final File serFile, final Class<T> clazz)throws IOException
    {
        if(serFile==null || !serFile.exists() || serFile.isDirectory())throw  new IOException("文件不存在或是目录");
        if(clazz==null)throw  new IOException("类型为空");
        ObjectInputStream in=null;
        try{
            in=new ObjectInputStream(new FileInputStream(serFile));
            return (T)in.readObject();
        }catch(Exception e){
            throw  new IOException(e);
        }finally{
            if(in!=null)try{in.close();}catch(Exception e){}
        }
    }

    /**
     * 写入对象
     * @param serFile
     * @param obj
     * @param <T>
     * @throws IOException
     */
    public static  <T extends Serializable> void writeObject(final File serFile, final T obj)throws IOException
    {
        if(serFile==null || obj==null || serFile.isDirectory())throw  new IOException("文件不存在或是目录，或要写入的对象为空");
        //写入文件
        ObjectOutputStream out=null;
        try{
            out=new ObjectOutputStream(new FileOutputStream(serFile));
            out.writeObject(obj);
        }finally{
            if(out!=null)try{out.close();}catch(Exception e){}
        }
    }

    /**
     * 读取一个整数
     * @param in
     * @return
     * @throws IOException
     */
    public static int readInt(final InputStream in)throws IOException{
        final byte[] bts = new byte[4];
        if(in.read(bts)!=4)throw new IOException("文件不满4字节");
        return ((bts[0] & 0xFF)<<8*3) + ((bts[1] & 0xFF)<<8*2) + ((bts[2] & 0xFF)<<8) + (bts[3] & 0xFF);
    }

    public static void writeInt(final int data, final OutputStream out)throws IOException{
        final byte[] bts = new byte[4];
        bts[0] = (byte)((data >> 8*3) & 0xFF);
        bts[1] = (byte)((data >> 8*2) & 0xFF);
        bts[2] = (byte)((data >> 8) & 0xFF);
        bts[3] = (byte)(data & 0xFF);
        out.write(bts);
    }

    public static boolean checkIn(final String strKey, final List<Matcher> excludeMatchers){
        assert (strKey!=null && excludeMatchers!=null && !excludeMatchers.isEmpty());
        //System.out.println("strKey="+strKey+" excludeMatchers="+excludeMatchers);
        for(Matcher matcher : excludeMatchers){
            synchronized (matcher) {
                matcher.reset(strKey);
                if (matcher.find()) return true;
            }
        }
        //System.out.println(false);
        return false;
    }

    private static boolean checkAndRemove(final String strKey, final List<Matcher> includeMatchers){
        //System.out.println("strKey="+strKey+" excludeMatchers="+excludeMatchers);
        for(Matcher matcher : includeMatchers){
            synchronized (matcher) {
                matcher.reset(strKey);
                if (matcher.find()){
                    includeMatchers.remove(matcher);
                    return true;
                }
            }
        }
        return false;
    }
}







