package com.mingjalee.server.demo1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * 创建服务器 启动
 * 请求
 * 响应
 * @author jerry
 * @create 16/9/23 19:23
 */
public class Server4 {
    private ServerSocket server = null;
    public static final String CRLF = "\r\n";
    public static final String BlANK = " ";

    public static void main(String[] args) {
        new Server4().start();
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
            System.out.println(client.getInetAddress().toString() + "客户请求=========");
            String requestInfo = new String(data, 0, len);
            System.out.println(requestInfo);

            //响应
            Response rep = new Response(client.getOutputStream());
            rep.println("<html><head><title>响应测试</title></head>");
            rep.println("<body>Hello Server</body></html>");
            rep.pushToClient(200);

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
