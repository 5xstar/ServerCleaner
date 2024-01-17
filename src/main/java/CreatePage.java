import java.io.BufferedInputStream;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.text.SimpleDateFormat;


/**
 * 从下载的文件生成标准网页
 * 庞海文  2024-1-17改
**/
public class CreatePage{

  //顺序ID
  private static int itemId=0;

  /**
   * 生成项目ID
   * @return
   */
  private static String getItemId() {
	  SimpleDateFormat SIP_TIMESTAMP_FORMATER =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  String time=SIP_TIMESTAMP_FORMATER.format(new Date());
	  return time.replaceAll("[\\s:]", "-")+"_"+(++itemId);
  }

	/**
	 * 获取主图 .png  .jpg  .gif
 	 * @param shortFn  网页文件名，不含路径和扩展名
	 * @param f  主图所属的网页文件，同一个目录
	 * @return  主图文件名
	 */
     private static String getMainImg(final String shortFn,final File f) {
	  //png
	  final File parent=f.getParentFile();
	  String nwfn=shortFn+".png";
	  File nf=new File(parent,nwfn);
	  if(nf.exists())return nwfn;
	  //jpg
	  nwfn=shortFn+".jpg";
	  nf=new File(parent,nwfn);
	  if(nf.exists())return nwfn;
	  //gif
	  nwfn=shortFn+".gif";
	  nf=new File(parent,nwfn);
	  if(nf.exists())return nwfn;
	  return null;
    }

	/**
	 * 获取jsp头
	 * @param itemId 项目编码
	 * @param title
	 * @param mainImg
	 * @return
	 */
  private static String getJspHead(final String itemId, final String title, final String mainImg) {
      StringBuilder sb=new StringBuilder();
      sb.append("<%@ page contentType=\"text/html;charset=UTF-8\" language=\"java\"%>");
      sb.append("<%@ page import=\"com._5xstar.netflowobserver.counter.Counter\"%>");
      sb.append("<% Counter.count(request, response, \"");
      sb.append(itemId);
      sb.append("\", \"");
      sb.append(title);
      sb.append("\", \"");
      sb.append(mainImg);
      sb.append("\", \"\");  %>");
      return sb.toString();
  }

	/**
	 * 过滤不需要处理的文件或目录
	 * @param fn  文件名，不含路径
	 * @return
	 */
  private static boolean filter(String fn) {
	  return fn.startsWith("index.") || fn.endsWith(".files") || fn.endsWith(".jsp");  //不处理目录文件和图片文件夹jsp文件
  }
  

	/**
	 * 获取无扩展名的文件名称
	 * @param fn
	 * @return
	 */
  private static String getShortName(String fn) {
	                int lidx=0;
		if ((lidx=fn.lastIndexOf('.'))>0) {
		    return fn.substring(0,lidx);
		}else{
		   return fn;
                                }
  }

	final private static String numPt="^[0-9][0-9]";
	final private static Pattern numPattern = Pattern.compile(numPt, Pattern.UNICODE_CASE);
  private static String getTitle(String shortName) {
		if(numPattern.matcher(shortName).find())return shortName.substring(2);
		else return shortName;
  }
  

  //标题
  final private static String titlePt="(<[tT][iI][tT][lL][eE]>|<[tT][iI][tT][lL][eE]\\s+[^><]*>)(.*?)(</[tT][iI][tT][lL][eE]>|</[tT][iI][tT][lL][eE]\\s+[^><]*>)";  
  final private static Pattern titlePattern = Pattern.compile(titlePt, Pattern.UNICODE_CASE+Pattern.MULTILINE+Pattern.DOTALL);
  //主图
  final private static String mainImgPt="(<!--\\s+[mM][aA][iI][nN][iI][mM][gG]>|<!--\\s+[mM][aA][iI][nN][iI][mM][gG]\\s+[^><]*>)(.*?)(</[mM][aA][iI][nN][iI][mM][gG]\\s+-->|</[mM][aA][iI][nN][iI][mM][gG]\\s+[^><]*\\s+-->)";  
  final private static Pattern mainImgPattern = Pattern.compile(mainImgPt, Pattern.UNICODE_CASE+Pattern.MULTILINE+Pattern.DOTALL);

	/**
	 * 建立完整head
	 * @param its  存放标题和主图的数组
	 * @param headStr  头字符串
	 * @return
	 */
	private static String buildHeadText(final String[] its, final String headStr) {  
	  Matcher titleMt=titlePattern.matcher(headStr);
	  if(titleMt.find()) {
		System.out.println("title="+titleMt.group()); //测试
		String txt=titleMt.group(2);
		if(txt==null || "".equals(txt=txt.trim())) {
			StringBuilder sb=new StringBuilder();
			titleMt.appendReplacement(sb, "");
			titleMt.appendTail(sb);
			txt=sb.toString();
			titleMt=null;
			sb=null;
			return buildHeadText(its, txt);  //删除空标题
		}
		its[0]=txt;  //找到标题
		Matcher mainImgMt=mainImgPattern.matcher(headStr);
		if(mainImgMt.find()) {  //主图
		   System.out.println("mainImg="+mainImgMt.group()); //测试
		   its[1]=mainImgMt.group(2);  //找到主图
		   return headStr;  			
		}else {
		   //删除标题后返回
		   StringBuilder sb=new StringBuilder();
		   titleMt.appendReplacement(sb, "");
		   titleMt.appendTail(sb);
		   return sb.toString();  //需要加入标题和主图 its[1]==null
		}
	  }else {
		Matcher mainImgMt=mainImgPattern.matcher(headStr);
		if(mainImgMt.find()) {  //主图
		  System.out.println("mainImg="+mainImgMt.group()); //测试
		  its[1]=mainImgMt.group(2);  //找到主图
	      //删除主图后返回
		  StringBuilder sb=new StringBuilder();
		  mainImgMt.appendReplacement(sb, "");
		  mainImgMt.appendTail(sb);
		  return sb.toString();  //需要加入标题和主图 its[0]==null
		}else {
		  return headStr;  //需要加入标题和主图
		}
	  }
	  
  }
  
  final private static String capPt="<p\\s+align=[\"']center[\"']><h3>(.+?)</h3></p>";  
  final private static Pattern capPattern = Pattern.compile(capPt, Pattern.UNICODE_CASE+Pattern.DOTALL);

	/**
	 * 获取标题
	 * @param bodyStr
	 * @return
	 */
  private static String getCap(final String bodyStr) {
	  Matcher capMt=capPattern.matcher(bodyStr);
	  if(capMt.find()) {
		System.out.println("cap="+capMt.group());
		return capMt.group(1);  //设置标题 
	  }else {
		return null;	  	  
	  }
  }

	/**
	 * 处理1个文档
	 * @param currentDir 当前目录
	 * @param fn 文件名
	 */
  private static void handle(File currentDir, String fn){
     if(!filter(fn)) {
    	 File f=new File(currentDir, fn);
    	 if(f.isFile()) {
    	    handle(fn,f);
    	 }else {
    		//递归调用
    		currentDir=f;
    		String[] fns=currentDir.list();
  		    if(fns!=null && fns.length>0){
  		      for(int i=0;i<fns.length;i++) {
  			    handle(currentDir, fns[i]);
  		      }
  		    }
    	 }
     }
  }
  
  final private static String headPt="(<[hH][eE][aA][dD]>|<[hH][eE][aA][dD]\\s+[^><]*>)(.*?)(</[hH][eE][aA][dD]>|</[hH][eE][aA][dD]\\s+[^><]*>)";  
  final private static Pattern headPattern = Pattern.compile(headPt, Pattern.UNICODE_CASE+Pattern.MULTILINE+Pattern.DOTALL);
  final private static String bodyPt="(<[bB][oO][dD][yY]>|<[bB][oO][dD][yY]\\s+[^><]*>)(.*?)(</[bB][oO][dD][yY]>|</[bB][oO][dD][yY]\\s+[^><]*>)";  
  final private static Pattern bodyPattern = Pattern.compile(bodyPt, Pattern.UNICODE_CASE+Pattern.MULTILINE+Pattern.DOTALL);
  final private static Pattern tnPattern = Pattern.compile("^[0-9][0-9]$");

	/**
	 * 处理1个文档
	 * @param fn 文件名
	 * @param f  文件
	 */
  private static void handle(String fn, File f){
	 //打印处理的文件
	 System.out.println(f.getAbsoluteFile().getAbsolutePath());  //测试
	   
     //获取文件内容
	 final String code=codeString(f); //获取文件编码
	 String txt=null;
	 try {
	   txt=readStringFromFile( f,   code);
	 }catch(Exception e) {
		 e.printStackTrace();
		 return;
	 }	 
	 
	 //无扩展名的文件名称
	 String shortFn=getShortName(fn);
	 
	 //获取head部分，检查更新itemId（文件编码）字段(计数)，title(标题),summary(简介)，mainImg(主图)
	 Matcher headMt=headPattern.matcher(txt);
	 final boolean hsHead=headMt.find(); 
	 //网页数据
	 final String[] its=new String[]{null,null};   //要生成jsp页面的标题和主图
	 int start;
	 String headTxt;
	 if(hsHead) {
		headTxt=buildHeadText(its,headMt.group(2));
		start=headMt.end();
	 }else {
		start=0;
		headTxt=null;
	 }

	 //获取body部分, 检查标题，如果没有标题就加入文件名称（无后缀）标题，重命名文件itemId
	 Matcher bodyMt=bodyPattern.matcher(txt);
	 if(bodyMt.find(start)) {
		String bodyTxt=bodyMt.group(2);
		if(bodyTxt==null || "".equals(bodyTxt=bodyTxt.trim()))return;
		String cap=getCap(bodyTxt);
		StringBuilder sb=new StringBuilder();
	    if(hsHead) {
	    	if(its[0]!=null && its[1]!=null) {
				if(cap==null) {					
					sb.append(txt.substring(0,bodyMt.start()));
					sb.append(bodyMt.group(1));
					sb.append("<p align=\"center\"><h3>");
					sb.append(its[0]);
					sb.append("</h3></p>");
					sb.append(bodyTxt);
					sb.append(bodyMt.group(3));
					sb.append(txt.substring(bodyMt.end()));
					txt=sb.toString();
				}else {
					//txt已符合要求
				}    		
	    	}else {
	    		if(its[0]==null) {  //无标题
	    			if(cap==null) {
	    				if(tnPattern.matcher(shortFn).matches())return;  //无标题
	    				its[0]=getTitle(shortFn);
	    			}else {
	    				its[0]=cap;
	    			}	    			
	    		}
	    		if(its[1]==null) {  //无主图
	    			its[1]=getMainImg(shortFn,f);
	    			if(its[1]==null)return;  //无主图
	    		}
	    		sb.append(txt.substring(0,headMt.start()));
	    		sb.append(headMt.group(1));
	    		sb.append(headTxt);
                                    sb.append(buildKeywords(false));
	    		sb.append("<title>");
	    		sb.append(its[0]);
	    		sb.append("</title>");
	    		sb.append("<!-- mainImg>");
	    		sb.append(its[1]);
	    		sb.append("</mainImg -->");
	    		sb.append(headMt.group(3));
				if(cap==null) {
	    		  sb.append(txt.substring(headMt.end(),bodyMt.start()));
				  sb.append(bodyMt.group(1));
				  sb.append("<p align=\"center\"><h3>");
				  sb.append(its[0]);
				  sb.append("</h3></p>");
				  sb.append(bodyTxt);
				  sb.append(bodyMt.group(3));
				  sb.append(txt.substring(bodyMt.end()));
				}else {
				  sb.append(txt.substring(headMt.end()));
				}
				txt=sb.toString();
	    	}	    	
	    }else {
	    	if(cap==null) {
				if(tnPattern.matcher(shortFn).matches())return;  //无标题
				its[0]=getTitle(shortFn);
			}else {
				its[0]=cap;
			}
	    	its[1]=getMainImg(shortFn,f);
			if(its[1]==null)return;  //无主图
	    	sb.append(txt.substring(0,bodyMt.start()));
	    	sb.append("<head>");  //插入
                        sb.append(buildKeywords(false));
	    	sb.append("<title>");
    		sb.append(its[0]);
    		sb.append("</title>");
    		sb.append("<!-- mainImg>");
    		sb.append(its[1]);
    		sb.append("</mainImg -->");
	    	sb.append("</head>");
			if(cap==null) {
				  sb.append(bodyMt.group(1));
				  sb.append("<p align=\"center\"><h3>");
				  sb.append(its[0]);
				  sb.append("</h3></p>");
				  sb.append(bodyTxt);
				  sb.append(bodyMt.group(3));
				  sb.append(txt.substring(bodyMt.end()));
			}else {
				  sb.append(txt.substring(bodyMt.start()));
			}
			txt=sb.toString();
	    }
	    headMt=null;
	    bodyMt=null;
        sb=new StringBuilder();
        sb.append(getJspHead(getItemId(),its[0],its[1]));
        sb.append(txt);
        txt=sb.toString();
        sb=null;
    	try {
    		writeStringToUTF8File( f, txt);
            File nFile=new File(f.getParentFile(),shortFn+".jsp");
            if(nFile.exists())nFile.delete();
            f.renameTo(nFile);
    	 }catch(Exception e) {
    		e.printStackTrace();
    	 }
    	
	 } 
	 
  }
  //处理一个目录
  private static void handles(String dir) {
      System.out.println("handles "+dir);
	  if(dir==null) {  //处理当前目录
		  File currentDir=new File((String)null);
		  String fn=currentDir.getName();
		  if(filter(fn))return;  //不处理索引和图片目录
		  String[] fns=currentDir.list();
		  if(fns!=null && fns.length>0){
		    for(int i=0;i<fns.length;i++) {
			  handle(currentDir, fns[i]);
		    }
		  }
		  return;
	  }
	  if(filter(dir))return; //跳过图片文件目录和索引
	  File f=new File(dir);
	  if (f.exists()) {
	    if(f.isFile()) {
	      handle(dir, f);
	    }else {
		  String[] fns=f.list();
		  if(fns!=null && fns.length>0) {
			  for(int i=0;i<fns.length;i++)handle(f, fns[i]);
		  }
	    }
	  }
  }

  //执行生成
  public static void main(String[] args){
    if(args==null || args.length==0){
    	//handles(null);
    	handles("/work/dir");
    }else {
       for(int i=0;i<args.length;i++) {
    	   handles(args[i]);
       }
    }
  }
  
	/**
	 * keywords标签 <META name="keywords" content="Python,数据结构,算法,学Python,奥数,强基,数学,学思营,学Python,学Java,学编程,五行星,五行星学编程,5xstar,5xstar.com,5xstar.net,五行星软件"/> 
	 * @param hasHead 是否在文档中已有<head></head>标签
	 * @return
	 */
  private static String buildKeywords(boolean hasHead) {
	  StringBuilder sb=new StringBuilder();
	  if(hasHead)
	      sb.append("<head>\n<META name=\"keywords\" content=\"");
	  else
	      sb.append("\n<META name=\"keywords\" content=\"");
	  sb.append("五行星编程,");
	  sb.append("学思营编程,");
	  sb.append("蓝桥等考,");
	  sb.append("C++算法,");
	  sb.append("CSP-J/S,");
	  sb.append("CSP-J,");
	  sb.append("CSP-S,");
	  sb.append("CSP,");
	  sb.append("5xstar.com,");
	  sb.append("5xstar.net");
	  sb.append("\"/>");
	  return sb.toString();
  }

  /**
	 * 判断文件的编码格式
	 * @param file 文件
	 * @return 文件编码格式
	 */
  private static String codeString(File file){
	  return codeString(file, null);
  }
  private static String codeString(File file, final String defaultCode){
		BufferedInputStream bin=null;
		String code = defaultCode==null?"GBK":defaultCode;
		try {
		  bin = new BufferedInputStream(new FileInputStream(file));
		  int p = (bin.read() << 8) + bin.read();
		  switch (p) {
			case 0xefbb:
				code = "UTF-8";
				break;
			case 0xfffe:
				code = "Unicode";
				break;
			case 0xfeff:
				code = "UTF-16BE";
				break;
		  }
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(bin!=null)try {bin.close();}catch(Exception e) {}
		}
		return code;
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
       osw = new OutputStreamWriter(new FileOutputStream(f), "UTF-8"); 
       osw.write(str);
       osw.flush();   
    }finally{
       if(osw!=null)try{osw.close();}catch(Exception e){}
    }   
 }

 /**
   *读取文本
   *@param f 文本文件
   *@param code 编码
   *@throws IOException 读取错误
 **/
 private static String readStringFromFile(final File f, final String code)throws IOException{
     BufferedReader br =null;
     StringBuilder sb=new StringBuilder((int)f.length());
     try{
         br = new BufferedReader(new InputStreamReader(new FileInputStream(f), code));
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


