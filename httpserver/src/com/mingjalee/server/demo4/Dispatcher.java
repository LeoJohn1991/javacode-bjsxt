package com.mingjalee.server.demo4;

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
        Servlet serv = null;
        try {
            serv = WebApp.getServlet(req.getUrl());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        if (null == serv) {
            this.code = 404;
        } else {
            try {
                serv.service(req, rep);
                rep.pushToClient(code);
            } catch (Exception e) {
                e.printStackTrace();
                this.code = 500;
                try {
                    rep.pushToClient(code);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

        //推送结束,关闭流
        CloseUtil.closeSocket(client);

    }
}
