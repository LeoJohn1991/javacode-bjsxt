package com.mingjalee.file;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 文件夹操作
 * @author jerry
 * @create 16/9/11 15:37
 */
public class FileDemo4 {
    public static void main(String[] args) {



        String path = "/Users/jerry/Developer/IdeaProjects/javacode-sxt/srcfile";
        File src = new File(path);
        System.out.println("========文件名=======");
        if (src.exists() && src.isDirectory()) { //exist directory
            String[] s = src.list();
            for (String temp : s) {
                System.out.println(temp);
            }
        }

        System.out.println("=========文件名 File形式========");
        if (src.exists() && src.isDirectory()) {
            File[] s = src.listFiles();
            for(File temp : s) {
                System.out.println(temp.getAbsolutePath());
            }
        }

        System.out.println("===========.java==========");
        //命令设计模式
        File[] s = src.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                //System.out.println(dir.getName());
                return new File(dir, name).isDirectory() || name.endsWith(".java");
            }
        });
        for (File temp : s) {
            System.out.println(temp.getAbsolutePath());
        }



        test1();
    }

    public static void test1() {
        String path = "/Users/jerry/Developer/IdeaProjects/javacode-sxt/srcfile/test/m";
        File src = new File(path);

        if (!src.exists()) {
            System.out.println("创建文件|目录: " + path);
            src.mkdirs();
        }
        else {
            System.out.println("存在路径: " + path);
        }
    }
}
