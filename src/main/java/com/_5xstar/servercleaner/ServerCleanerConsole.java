package com._5xstar.servercleaner;

import com._5xstar.servercleaner.test.ServerCleanerTest;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * 服务器清洁工控制台
 * 庞海文  2024-1-13
 */
public class ServerCleanerConsole {

    public static boolean runing = true;
    public static void main(String[] args){
        final Scanner scanner = new Scanner(System.in);
        while (runing) {
                try {
                     System.out.print(":>");
                     handle(scanner.nextLine().trim());
                }catch (Exception e){
                     e.printStackTrace();
                }
        }
        scanner.close();
    }
    private static void handle(String line)throws IOException {
        System.out.println(line);
        if("stop".equals(line)){
            runing=false;
        }else if("demo".equals(line)){
            ServerCleanerTest.demo(null);
        }else if(line.startsWith("clean ")){
            String d = line.substring("clean ".length());
            if(d==null || "".equals(d=d.trim())){
                System.out.println("Please input clean full-path  (so as: clean d:\\mydir\\test");
                return;
            }
            clean(d);
        }
    }
    private static void clean(String dirStr)throws IOException{
        ServerCleaner sc = ServerCleaner.serverCleaner;
        Preparer preparer = null;
        Judger judger = null;
        try {
            preparer = sc.getPreparer();
            File dir = new File(dirStr);
            preparer.prepare(dir);
            File data = preparer.finished();
            preparer.close();
            judger = sc.getJudger(preparer, data);
            Cleaner cleaner = sc.getCleaner(preparer, judger);
            preparer = null;
            cleaner.cleanDir(dir);
            judger.close();
            judger = null;
        }finally{
            if(preparer!=null)try{preparer.close();}catch (Exception e){}
            if(judger!=null)try{judger.close();}catch (Exception e){}
        }
    }
}
