package com.mingjalee.server.demo1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * 创建服务器 启动
 * 请求
 * 响应
 * Servlet服务
 * @author jerry
 * @create 16/9/23 19:23
 */
public class Server6 {
    private ServerSocket server = null;
    public static final String CRLF = "\r\n";
    public static final String BlANK = " ";

    public static void main(String[] args) {
        new Server6().start();
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

            //请求信息
            Request req = new Request(client.getInputStream());
            //响应
            Response rep = new Response(client.getOutputStream());

            Servlet servlet = new Servlet();
            servlet.service(req, rep);

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
