package com.mingjalee.server.demo1;

import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

/**
 * @author jerry
 * @create 16/9/24 14:55
 */
public class demo {
    public static void main(String[] args) {
        try {
            String code = "utf-8";
            String text1 = java.net.URLEncoder.encode("中国", code);
            String text2 = java.net.URLDecoder.decode(text1, code);
            System.out.println("text1 = " + text1);
            System.out.println("text2 = " + text2);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
