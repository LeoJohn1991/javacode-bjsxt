package com.mingjalee.server.demo2;

import com.mingjalee.server.util.CloseUtil;

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
public class Server {
    private ServerSocket server = null;
    public static final String CRLF = "\r\n";
    public static final String BlANK = " ";

    private boolean isShutDown = false;

    public static void main(String[] args) {
        new Server().start();
    }

    /**
     * 默认端口创建服务socket
     * 启动服务器
     */
    public void start() {
        start(8888);
    }

    /**
     *  指定某个端口,启动服务器
     */
    public void start(int port) {
        try {
            server = new ServerSocket(port);
            receive();
        } catch (IOException e) {
            //e.printStackTrace();
            stop();
        }

    }

    /**
     * 服务器接受请求
     */
    public void receive() {
        try {
            while(!isShutDown) {
                Socket client = server.accept();
                new Thread(new Dispatcher(client)).start();
            }

        } catch (IOException e) {
            //e.printStackTrace();
            stop();
        }
    }

    /**
     * 停止服务器
     */
    public void stop() {
        isShutDown = true;
        CloseUtil.closeSocket(server);
    }

}
