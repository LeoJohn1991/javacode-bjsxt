package com.mingjalee.tcp.chat.demo4;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author jerry
 * @create 16/9/22 15:42
 */
public class Receive implements Runnable {
    //输入流
    private DataInputStream dis;
    //线程标志
    private boolean isRunning = true;

    public Receive(Socket client) {
        try {
            dis = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            //e.printStackTrace();
            isRunning = false;
            CloseUtil.closeALL(dis);
        }
    }

    /**
     * 接受数据
     * @return
     */
    public String recevie() {
        String msg = "";
        try {
            msg = dis.readUTF();
        } catch (IOException e) {
            //e.printStackTrace();
            isRunning = false;
            CloseUtil.closeALL(dis);
        }

        return msg;
    }


    @Override
    public void run() {
        while (isRunning) {
            System.out.println(recevie());
        }
    }
}
