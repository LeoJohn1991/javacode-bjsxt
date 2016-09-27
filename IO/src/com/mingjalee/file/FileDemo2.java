package com.mingjalee.file;

import java.io.File;

/**
 * 绝对路径与相对路径
 * @author jerry
 * @create 16/9/11 14:21
 */
public class FileDemo2 {
    public static void main(String[] args) {
        String str = "/Users/jerry/Developer/IdeaProjects/javacode-sxt/srcfile/sub.txt";

        //relative path
        System.out.println("======== relative path ======");

        String parentPath = "/Users/jerry/Developer/IdeaProjects/javacode-sxt/srcfile";
        String name = "sub.txt";
        File file = new File(parentPath, name);
        //file = new File(new File((parentPath)), name);
        System.out.println(file.getName());
        System.out.println(file.getPath());


        //Absolute Path
        System.out.println("======== absolute path ======");
        file = new File(str);
        System.out.println(file.getName());
        System.out.println(file.getPath());

        System.out.println("======== just the file name ======");
        file = new File("sub.txt");
        System.out.println(file.getName());
        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());
    }
}
