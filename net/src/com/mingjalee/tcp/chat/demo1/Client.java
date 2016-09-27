package com.mingjalee.tcp.chat.demo1;

import java.io.*;
import java.net.Socket;

/**
 * @author jerry
 * @create 16/9/22 13:06
 */
public class Client {
    public static void main(String[] args) throws IOException {
        //创建客户端,指定服务器地址及端口,此时即连接
        Socket client = new Socket("localhost", 9999);

        //控制台输入
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        DataInputStream dis = new DataInputStream(client.getInputStream());
        while(true) {
            String info = br.readLine();
            //写入数据
            //输出数据
            dos.writeUTF(info);
            dos.flush();

            //接受数据
            String msg = dis.readUTF();
            System.out.println(msg);
        }
    }
}
