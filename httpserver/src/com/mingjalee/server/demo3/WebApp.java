package com.mingjalee.server.demo3;

import java.util.Map;

/**
 * @author jerry
 * @create 16/9/27 10:33
 */
public class WebApp {
    private static ServletContext context;
    static {
        context = new ServletContext();

        Map<String, String> mapping = context.getMapping();
        mapping.put("/login", "login");
        mapping.put("/log", "login");
        mapping.put("/reg", "register");

        Map<String, String> servlet = context.getServlet();
        servlet.put("login", "com.mingjalee.server.demo3.LoginServlet");
        servlet.put("register", "com.mingjalee.server.demo3.RegisterServlet");

    }

    public static Servlet getServlet(String url) {
        if (null == url || (url.trim().equals(""))) {
            return null;
        }
        //return context.getServlet().get( (context.getMapping().get(url)) );
        //反射机制,动态获取对象
        String name = context.getServlet().get( (context.getMapping().get(url)) );
        try {
            return (Servlet) Class.forName(name).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
