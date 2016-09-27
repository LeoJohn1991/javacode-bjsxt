package com.mingjalee.tcp.chat.demo2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author jerry
 * @create 16/9/22 13:06
 */
public class Server {
    public static void main(String[] args) throws IOException {
        //        创建服务器指定端口
        ServerSocket server = new ServerSocket(9999);
        while (true) {
//        接受客户端
            Socket socket = server.accept();
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            while (true) {
                //接受数据
                String msg = dis.readUTF();
                System.out.println(msg);
                 //输出数据
                dos.writeUTF("服务器--->" + msg);
                dos.flush();
            }
        }
    }
}
