package com.mingjalee.tcp.chat.demo4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 实现聊天 客户端
 * @author jerry
 * @create 16/9/22 13:06
 */
public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("请输入客户端名称:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();

        if (name == "") {
            return;
        }

        //创建客户端,指定服务器地址及端口,此时即连接
        Socket client = new Socket("localhost", 9999);

        //控制台输入
        new Thread(new Send(client, name)).start(); //创建一条发送线程
        new Thread(new Receive(client)).start(); //创建一条接受线程
    }
}
