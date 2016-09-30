package com.mingjalee.servlet;

import com.mingjalee.server.Request;
import com.mingjalee.server.Response;

/**
 * @author jerry
 * @create 16/9/27 10:04
 */
public class LoginServlet extends Servlet {

    @Override
    public void doGet(Request req, Response rep) throws Exception {
        String name = req.getParameter("uname");
        String pwd = req.getParameter("pwd");

        if (login(name, pwd)) {
            rep.println(name + "登录成功");
        } else {
            rep.println("登录失败");
        }
    }

    @Override
    public void doPost(Request req, Response rep) throws Exception {

    }


    public boolean login(String name, String pwd) {
        if (null == name || null == pwd)
            return false;
        return name.equals("jerry") && pwd.equals("123456");
    }
}
