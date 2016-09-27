package com.mingjalee.others;

import java.io.*;
import java.util.Arrays;

/**
 * 序列化与反序列化
 * @author jerry
 * @create 16/9/12 09:00
 */
public class ObjectDemo1 {
    public static void main(String[] args) {
        try {
            write("/Users/jerry/Desktop/srcfile/ab.txt");
            read("/Users/jerry/Desktop/srcfile/ab.txt");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read(String srcPath) throws IOException, ClassNotFoundException {
        //建立联系
        File src = new File(srcPath);

        //选择流
        ObjectInputStream ois = new ObjectInputStream( new BufferedInputStream(new FileInputStream(src)) );

        //读取数据
        Object obj = ois.readObject();
        if (obj instanceof Employ) {
            Employ emp = (Employ) obj;
            System.out.println(emp.getName());
            System.out.println(emp.getSalary());
        }

        int[] arr = (int[]) ois.readObject();
        System.out.println(Arrays.toString(arr));

    }

    /**
     * 序列化数据
     * @param destPath
     * @throws IOException
     */
    public static void write(String destPath) throws IOException {
        //建立联系
        File dest = new File(destPath);
        //创建雇员
        Employ emp = new Employ("Jerry", 10000);

        int[] arr = {1,2,3,45,6};

        //选择流
        ObjectOutputStream objos =  new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(dest)
                )
        );

        //数据写入,注意顺序
        objos.writeObject(emp);
        objos.writeObject(arr);

        //输出流需要flush,close
        objos.flush();
        objos.close();
    }
}
