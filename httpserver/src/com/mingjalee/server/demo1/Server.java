package com.mingjalee.server.demo1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 创建服务器
 * @author jerry
 * @create 16/9/23 01:21
 */
public class Server {
    private ServerSocket server = null;

    public static void main(String[] args) {
        new Server().start();
    }

    /**
     * 服务端启动
     */
    public void start() {
        try {
            server = new ServerSocket(8888);
            recevie();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 服务器接受请求
     */
    public void recevie() {
        try {
            Socket client = server.accept();
            String msg = null;
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));

            while ( (msg = br.readLine()).length() > 0) {
                sb.append(msg);
                sb.append("\r\n");
            }

            String requsetInfo = sb.toString().trim();
            System.out.println(requsetInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 服务器停止
     */
    public void stop() {

    }

}
