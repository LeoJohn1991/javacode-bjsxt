package com.mingjalee.tcp.socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端
 * @author jerry
 * @create 16/9/22 12:14
 */
public class Server {
    public static void main(String[] args) throws IOException {
        // 创建服务端
        ServerSocket server = new ServerSocket(8888);
        //while(true) {
            //接受连接 阻塞式
            Socket socket = server.accept();

            System.out.println("一个客户端建立连接");

        //}


        //发送数据
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        String msg = "欢迎使用";
        dos.writeUTF(msg);
        dos.flush();
    }
}
