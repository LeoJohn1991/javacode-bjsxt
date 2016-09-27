package com.mingjalee.file;

import java.io.File;
import java.util.Arrays;

/**
 * 输出子孙级目录
 * @author jerry
 * @create 16/9/11 16:11
 */
public class FileDemo5 {
    public static void main(String[] args) {
        String path = "/Users/jerry/Developer/IdeaProjects/javacode-sxt/srcfile";
        File src = new File(path);
        printName(src);
    }

    public static void printName(File file) {
        if (!file.exists()) {
            return;
        }

        System.out.println(file.getAbsolutePath());
        if (file.isDirectory()) {
            for (File temp : file.listFiles()) {
                printName(temp);
            }
        }
    }
}
