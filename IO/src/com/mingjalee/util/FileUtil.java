package com.mingjalee.util;

import java.io.*;

/**
 * @author jerry
 * @create 16/9/12 09:24
 */
public class FileUtil {

    /**
     * 文件夹拷贝
     * @param src   源文件file对象
     * @param dest  目标文件file对象
     */
    public static void copyDir(File src, File dest) throws IOException {
        if (src.isDirectory()) {
            dest = new File(dest, src.getName());
            dest.mkdirs();
        }
        copyDirDetail(src, dest);
    }


    /**
     * 文件拷贝详情
     * @param src 源文件的file对象
     * @param dest 目标文件file对象
     */
    public static void copyDirDetail(File src, File dest) throws IOException {
        if (src.isFile()) { //如果是文件
            try {
                FileUtil.copyFile(src,dest);

            } catch (IOException e) {
                e.printStackTrace();
                throw e;
            }
        } else if (src.isDirectory()) { //如果是文件夹
//            确保文件夹存在
            dest.mkdirs();
//            获取下一级文件和目录
            for (File temp : src.listFiles()) {
                copyDirDetail(temp, new File(dest, temp.getName()));

            }
        }
    }




    /**
     * 文件夹的拷贝
     * @param srcPath
     * @param destPath
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void copyFile(String srcPath, String destPath) throws FileNotFoundException,IOException {
//        建立联系
        copyFile(new File(srcPath), new File(destPath));

    }

    public static void copyFile(File src, File dest) throws FileNotFoundException,IOException {
        //        判断是否为文件，不是返回错误
        if (!src.isFile()) {
            throw new IOException("只能拷贝文件");
        }
//        如果已经存在
        if (dest.isDirectory()) {
            throw new IOException("不能创建同名文件");
        }
//        输入输出流
        byte[] flush = new byte[1024];
        InputStream is = new FileInputStream(src);
        OutputStream os = new FileOutputStream(dest);
        int len = 0;
//        循环读取写入
        while (-1!= (len=is.read(flush))) {
            os.write(flush,0,len);
        }
//        强制输出
        os.flush();
//       关闭输入输出流
        os.close();
        os.close();
    }
}
