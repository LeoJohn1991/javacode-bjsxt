package com.mingjalee.server.demo1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author jerry
 * @create 16/9/23 15:57
 */
public class Server2 {
    ServerSocket server = null;
    public static void main(String[] args) {
        new Server2().start();
    }

    /**
     *  启动服务器
     */
    public void start() {
        try {
            server = new ServerSocket(8888);
            receive();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 服务器接受请求
     */
    public void receive() {
        try {
            Socket client = server.accept();
            byte[] data = new byte[20480];
            int len = client.getInputStream().read(data);

            //客户端请求信息
            String requestInfo = new String(data, 0, len);
            System.out.println(requestInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止服务器
     */
    public void stop() {

    }

}
