package com.mingjalee.file;

import java.io.File;

/**
 * File 类
 * @author jerry
 * @create 16/9/11 14:04
 */
public class FileDemo1 {

    public static void main(String[] args) {
        System.out.print("File.pathSeparator  = ");
        System.out.println(File.pathSeparator);

        System.out.print("File.separator = ");
        System.out.println(File.separator);

        String path = "/Users/jerry/Developer/IdeaProjects/javacode-sxt/srcfile";

        System.out.println("/Users/jerry/Developer/IdeaProjects/javacode-sxt" + File.separator + "srcfile");
    }
}
