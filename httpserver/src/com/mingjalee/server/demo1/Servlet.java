package com.mingjalee.server.demo1;

/**
 * Servlet小服务程序
 * @author jerry
 * @create 16/9/27 00:59
 */
public class Servlet {
    public void service(Request req, Response rep) {
        rep.println("<html><head><title>响应测试</title></head>");
        rep.println("<body>");
        rep.println("欢迎").println(req.getParameter("uname")).println("回来");
        rep.println("</body></html>");
    }
}
