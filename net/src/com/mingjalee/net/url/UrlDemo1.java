package com.mingjalee.net.url;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author jerry
 * @create 16/9/22 10:27
 */
public class UrlDemo1 {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://www.baidu.com");

        /*
        InputStream is = url.openStream();
        byte[] flush = new byte[1024];
        int len = 0;
        while(-1 != (len = is.read(flush))) {
            System.out.println(new String(flush, 0, len));
        }
        is.close();
        */

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("baidu.html")));

        String msg = null;
        while ((msg = br.readLine()) != null) {
            bw.append(msg);
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
