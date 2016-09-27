package com.mingjalee.file;

import java.io.File;
import java.io.IOException;

/**
 * @author jerry
 * @create 16/9/11 15:00
 */
public class FileDemo3 {
    public static void main(String[] args) {
        String path = "/Users/jerry/Developer/IdeaProjects/javacode-sxt/IO/src/a.txt";

        try {
            test3();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 创建临时文件
     */
    public static void test3() throws IOException, InterruptedException {
        String path = "/Users/jerry/Developer/IdeaProjects/javacode-sxt/IO/res/a.txt";
        File file = new File(path);

        //创建不存在文件
        if (!file.exists()) {
            boolean is = file.createNewFile();
            System.out.println("创建: " + (is ? "成功" : "失败") );
        }

        //Delete File
        boolean deleteFlag = file.delete();
        System.out.println("Delete: " + (deleteFlag ? "Success" : "Fail"));

        //Create temporary file
        File tempFile = File.createTempFile("aaa", ".txt", new File("/Users/jerry/Developer/IdeaProjects/javacode-sxt/IO/res/"));
        Thread.sleep(10000);
        tempFile.delete();
    }

}
