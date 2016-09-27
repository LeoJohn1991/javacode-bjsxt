package com.mingjalee.filedemo;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author jerry
 * @create 16/9/9 20:42
 */
public class main {
    public static void main(String[] args) {

        test1();

    }

    public static void test1() {
        String Path = "/Users/jerry/Desktop/network.txt";
        File file = new File(Path);

        try {
            System.out.println("path: " + file.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(File.listRoots());

        if (file.exists()) {
            System.out.println("file exists");
        }
        else {
            System.out.println("file NO exists");
        }

        File[] roots = File.listRoots();
        System.out.println(Arrays.toString(roots));
    }

}
