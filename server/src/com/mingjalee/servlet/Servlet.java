package com.mingjalee.servlet;

import com.mingjalee.server.Request;
import com.mingjalee.server.Response;

/**
 * 抽象父类
 * @author jerry
 * @create 16/9/27 00:59
 */
public abstract class Servlet {

    public Servlet(){}
    public void service(Request req, Response rep) throws Exception {
        this.doGet(req, rep);
        this.doPost(req, rep);
    }


    public abstract void doGet(Request req, Response rep) throws Exception;
    public abstract void doPost(Request req, Response rep) throws Exception;
}
