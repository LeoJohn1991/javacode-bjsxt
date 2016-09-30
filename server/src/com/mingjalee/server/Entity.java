package com.mingjalee.server;

/**
 * servlet name -> servlet class(clz)
 * @author jerry
 * @create 16/9/29 10:54
 */
public class Entity {
    private String servletName;
    private String servletClz;

    public String getServletName() {
        return servletName;
    }

    public void setServletName(String servletName) {
        this.servletName = servletName;
    }

    public String getServletClz() {
        return servletClz;
    }

    public void setServletClz(String servletClz) {
        this.servletClz = servletClz;
    }
}
