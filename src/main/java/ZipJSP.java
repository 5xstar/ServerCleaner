
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 打包目录下的所有jsp，附带做一些变更
 * 庞海文  2024-1-17
 */
public class ZipJSP {

    /**
     * 在wabapps目录下运行，打包ROOT目录
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException{
        final File outF = new File("ROOTJSP.zip");
        ZipOutputStream out=null;
        try {
            out=new ZipOutputStream(new FileOutputStream(outF));
            copy2Zip("", new File("ROOT"), out);
        }finally {
            if(out!=null)try{out.close();}catch(Exception e){}
        }
    }

    /**
     * 递归写入目录
     * @param base  目录前缀
     * @param dir   当前操作目录
     * @param out   zip文件输出流
     * @throws IOException
     */
    private static void copy2Zip(final String base, final File dir, final ZipOutputStream out ) throws IOException
    {
        String[] fls=dir.list();
        if(fls==null || fls.length==0)return;
        File f;
        ArrayList<File> inFs=new ArrayList<File>(fls.length);
        ArrayList<String> zipEntryNames=new ArrayList<String>(fls.length);
        for(int i=0;i<fls.length;i++){
            f=new File(dir,fls[i]);
            if(f.isDirectory()){
                if(!fls[i].endsWith(".files")) copy2Zip(base+f.getName()+File.separator, f, out );
            }else{
                if(fls[i].endsWith(".jsp")) {
                    inFs.add(f);
                    zipEntryNames.add(base + f.getName());
                }
            }
        }
        if(!inFs.isEmpty()){
            copy2Zip(  inFs, out,  zipEntryNames);
        }
    }

    /**
     * 把文档列表写入Zip文件
     * @param inFs  文档列表
     * @param out   Zip文件输出流
     * @param zipEntryNames Zip文件中的文件名称列表
     * @throws IOException
     */
    private static void copy2Zip(final List<File> inFs, final ZipOutputStream out, final List<String> zipEntryNames) throws IOException
    {

        FileInputStream in=null;
        try{
            for(int j=0;j<inFs.size();j++){
                in=new FileInputStream(inFs.get(j));
                out.putNextEntry(new ZipEntry(zipEntryNames.get(j)));
                final int length=(int)inFs.get(j).length();
                final byte[] data=new byte[2048];
                int num;
                int mx=length/2048;
                if(length%2048>0)mx++;
                for(int i=0;i<mx;i++){
                    num=in.read(data);
                    out.write(data,0,num);
                }
                out.closeEntry();
                if(in!=null)try{in.close();in=null;}catch(Exception e){}
                update(inFs.get(j));
            }
        }finally{
            if(in!=null)try{in.close();}catch(Exception e){}
        }
    }

    /**
     * 变更utf-8文本内容
     * @param f 变更的文件
     */
    private static void update(final File f) {
         try{
             //String desc = readStringFromUTF8File(  f);
             //do something
             //writeStringToUTF8File( f, desc);
         }catch (Exception e){
             e.printStackTrace();
         }
    }
        /**
         *把字符串写入utf-8文本文件
         *@param f 文本文件
         *@param str 要写的字符串（默认编码）
         *@throws IOException 写入错误
         **/
        private static void writeStringToUTF8File(final File f, final String str)throws IOException{
            OutputStreamWriter osw =null;
            try{
                osw = new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8);
                osw.write(str);
                osw.flush();
            }finally{
                if(osw!=null)try{osw.close();}catch(Exception e){}
            }
        }

        /**
         *@return 从utf-8文本文件读取文本
         *@param f 文本文件
         *@throws IOException 读取错误
         **/
        private static String readStringFromUTF8File(final File f)throws IOException{
            BufferedReader br =null;
            StringBuilder sb=new StringBuilder((int)f.length());
            try{
                br = new BufferedReader(new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                    sb.append("\n");
                }
                return sb.toString();
            }finally{
                if(br!=null)try{br.close();}catch(Exception e){}
            }
        }

}
