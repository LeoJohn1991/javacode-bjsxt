package com.mingjalee.server.demo4;

import java.util.ArrayList;
import java.util.List;

/**
 * urlpattern->servlet name
 * @author jerry
 * @create 16/9/29 10:51
 */
public class Mapping {
    private String servletName;
    private List<String> urlPatterns;

    public Mapping() {
        urlPatterns = new ArrayList<String>();
    }

    public String getServletName() {
        return servletName;
    }

    public void setServletName(String servletName) {
        this.servletName = servletName;
    }

    public List<String> getUrlPatterns() {
        return urlPatterns;
    }

    public void setUrlPatterns(List<String> urlPatterns) {
        this.urlPatterns = urlPatterns;
    }
}
