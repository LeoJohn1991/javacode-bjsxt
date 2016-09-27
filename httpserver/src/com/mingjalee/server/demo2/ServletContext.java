package com.mingjalee.server.demo2;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jerry
 * @create 16/9/27 09:56
 */
public class ServletContext {
    //为每个servlet取别名
    // url(/login) --map--> (String) login --map--> servlet

    private Map<String, Servlet> servlet;
    private Map<String, String> mapping;

    public ServletContext() {
        servlet = new HashMap<String, Servlet>();
        mapping = new HashMap<String, String>();

    }

    public Map<String, Servlet> getServlet() {
        return servlet;
    }

    public void setServlet(Map<String, Servlet> servlet) {
        this.servlet = servlet;
    }

    public Map<String, String> getMapping() {
        return mapping;
    }

    public void setMapping(Map<String, String> mapping) {
        this.mapping = mapping;
    }
}
