package com.mingjalee.server.demo3;

/**
 * @author jerry
 * @create 16/9/27 10:36
 */
public class RegisterServlet extends Servlet {

    @Override
    public void doGet(Request req, Response rep) throws Exception {

    }

    @Override
    public void doPost(Request req, Response rep) throws Exception {
        rep.println("<html><head><title>返回注册</title></head>");
        rep.println("<body>");
        rep.println("你的用户名为").println(req.getParameter("uname"));
        rep.println("</body></html>");
    }
}
