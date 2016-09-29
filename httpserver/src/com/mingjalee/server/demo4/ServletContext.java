package com.mingjalee.server.demo4;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jerry
 * @create 16/9/27 09:56
 */
public class ServletContext {
    //为每个servlet取别名
    // url(/login)--map-->(String) login--map-->String servlet("com.mingjalee.server.demo1.loginservlet"

    //serv-name -> serv-class
    private Map<String, String> servlet;
    //url -> servlet name
    private Map<String, String> mapping;

    public ServletContext() {
        servlet = new HashMap<String, String>();
        mapping = new HashMap<String, String>();

    }

    public Map<String, String> getServlet() {
        return servlet;
    }

    public void setServlet(Map<String, String> servlet) {
        this.servlet = servlet;
    }

    public Map<String, String> getMapping() {
        return mapping;
    }

    public void setMapping(Map<String, String> mapping) {
        this.mapping = mapping;
    }
}
