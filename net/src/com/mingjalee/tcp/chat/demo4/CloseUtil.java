package com.mingjalee.tcp.chat.demo4;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author jerry
 * @create 16/9/22 15:21
 */
public class CloseUtil {
    public static void closeALL(Closeable... io) {
        for (Closeable temp : io) {
            if (null != temp) {
                try {
                    temp.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
