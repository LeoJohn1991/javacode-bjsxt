package com.mingjalee.server.demo1;

import com.mingjalee.server.util.CloseUtil;

import java.io.IOException;
import java.net.Socket;

/**
 * 服务器处理客户线程
 * @author jerry
 * @create 16/9/27 01:12
 */
public class Dispatcher implements Runnable {
    private Socket client;
    private Request req;
    private Response rep;
    private int code = 200;

    public Dispatcher(Socket client) {
        this.client = client;
        try {
            req = new Request(client.getInputStream());
            rep = new Response(client.getOutputStream());
        } catch (IOException e) {
            //e.printStackTrace();
            code = 500;
        }
    }


    @Override
    public void run() {
        Servlet servlet = new Servlet();
        servlet.service(req, rep);

        try {
            rep.pushToClient(code);
        } catch (IOException e) {
            //e.printStackTrace();
            try {
                rep.pushToClient(500);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }



        //推送结束,关闭流
        CloseUtil.closeSocket(client);

    }
}
