package com.mingjalee.server.demo1;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 请求服务器
 * @author jerry
 * @create 16/9/24 10:46
 */
public class Request {
    //请求方式, 请求资源url, 请求参数

    //请求方式
    private String method;
    //请求资源
    private String url;
    //请求参数
    private Map<String, List<String>> parameterMapValue;

    public static final String CRLF = "\r\n";
    private InputStream is;
    private String requestInfo;

    public Request() {
        method = "";
        url = "";
        parameterMapValue = new HashMap<String, List<String>>();

    }

    public Request(InputStream is) {
        this();
        try {
            this.is = is;
            byte[] data = new byte[20480];
            int len = 0;
            len = is.read(data);
            requestInfo = new String(data, 0, len);
            System.out.println("\n=======请求信息========\n" + requestInfo.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        //解析请求头信息
        parseRequestInfo();
    }

    /**
     * 分析头信息
     */
    public void parseRequestInfo() {
        if (requestInfo == null || requestInfo.trim().equals("")) {
            return;
        }

        //接受请求参数
        String paramString = "";

        //获取请求方式
        int idx = requestInfo.indexOf("/");
        String firstLine = requestInfo.substring(0, requestInfo.indexOf(CRLF));
        this.method = firstLine.substring(0, idx).trim();

        //获取资源、参数
        String urlStr = firstLine.substring(idx, firstLine.indexOf("HTTP/")).trim();
        if (this.method.equalsIgnoreCase("post")) {
            this.url = urlStr;
            paramString = requestInfo.substring(requestInfo.lastIndexOf(CRLF)).trim();
        }
        else if(this.method.equalsIgnoreCase("get")) {
            if (urlStr.contains("?")) { //是否存在参数
                String[] urlArrays = urlStr.split("\\?");
                this.url = urlArrays[0];
                paramString = urlArrays[1];
            }
            else {
                this.url = urlStr;
            }
        }

        if (paramString.equals("")) {
            return;
        }
        //将请求参数封装在Map中
        parseParams(paramString);
    }

    /**
     * 将请求参数封装到Map中
     * @param paramString
     */
    public void parseParams(String paramString) {
        if (null == paramString || paramString.equals("")) {
            return;
        }

        //分割字符串组成数组
        StringTokenizer token = new StringTokenizer(paramString, "&");
        while (token.hasMoreTokens()) {
            String keyvalue = token.nextToken();
            String[] keyvalues = keyvalue.split("=");
            if (keyvalues.length == 1) {
                keyvalues = Arrays.copyOf(keyvalues, 2);
                keyvalues[1] = null;
            }

            String key = keyvalues[0].trim();
            String value = (keyvalues[1] == null) ? null : decode(keyvalues[1].trim(), "utf-8");

            //转化为map,分练思想
            if (!parameterMapValue.containsKey(key)) {
                parameterMapValue.put(key, new ArrayList<>());
            }

            List<String> values = parameterMapValue.get(key);
            values.add(value);
        }
    }

    /**
     * 值编码
     * @param value
     * @param decode
     * @return
     */
    public String decode(String value, String decode) {
        try {
            return java.net.URLDecoder.decode(value, decode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据页面活的name对应的多个值
     * @param name
     * @return
     */
    public String[] getParameterValues(String name) {
        List<String> values = null;
        if ((values = parameterMapValue.get(name)) == null) {
            return null;
        } else {
            return values.toArray(new String[0]);
        }
    }


    /**
     * 根据页面name获取单个值
     * @param name
     * @return
     */
    public String getParameter(String name) {
        String[] values = getParameterValues(name);
        if (null == values) {
            return null;
        }

        return values[0];
    }
}
