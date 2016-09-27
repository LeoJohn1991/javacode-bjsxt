package com.mingjalee.util;

import java.io.*;

/**
 * 拷贝文件
 * @author jerry
 * @create 16/9/11 18:05
 */
public class CopyFile {

    public static void main(String[] args) {
        String src = "/Users/jerry/Developer/IdeaProjects/javacode-sxt/srcfile/aa.txt";
        String dest = "/Users/jerry/Developer/IdeaProjects/javacode-sxt/srcfile/c.txt";

        copyFile(src, dest);
    }

    public static void copyFile(String srcPath, String destPath) {
        //建立联系
        File src = new File(srcPath);
        File dest = new File(destPath);

        //输入输出流
        InputStream is = null;
        OutputStream os = null;

        try {
            is = new FileInputStream(src);
            os = new FileOutputStream(dest);

            byte[] flush = new byte[1024];
            int len = 0;
            //循环写入
            while (-1 != (len = is.read(flush))) {
                os.write(flush, 0, len);
            }

            //强制输出
            os.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("文件未找到");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            //关闭输入输出流
            try {
                if (null != is) {
                    is.close();
                }
                if (null != os) {
                    os.close();
                    System.out.println("copy file: " + srcPath + " --> " + destPath);
                }
            } catch (IOException e) {
                //e.printStackTrace();
                System.out.println("文件关闭失败");
            }

        }

    }
}
