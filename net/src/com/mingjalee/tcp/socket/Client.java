package com.mingjalee.tcp.socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 客户端
 * @author jerry
 * @create 16/9/22 12:14
 */
public class Client {
    public static void main(String[] args) throws IOException {
        //创建客户端,指定服务器地址及端口,此时即连接
        Socket client = new Socket("localhost", 8888);

        //接受数据
        DataInputStream dis = new DataInputStream(client.getInputStream());
        String msg = dis.readUTF();
        System.out.println(msg);
    }
}
