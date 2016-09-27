package com.mingjalee.tcp.chat.demo3;

import java.io.IOException;
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
        new Thread(new Send(client)).start(); //创建一条发送线程
        new Thread(new Receive(client)).start(); //创建一条接受线程
    }
}
