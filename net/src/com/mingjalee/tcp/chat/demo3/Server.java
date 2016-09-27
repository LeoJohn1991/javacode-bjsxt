package com.mingjalee.tcp.chat.demo3;

import com.sun.xml.internal.bind.v2.model.annotation.RuntimeAnnotationReader;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jerry
 * @create 16/9/22 13:06
 */
public class Server {
    //客户端线程序列
    private List<Mychannel> all = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new Server().start();
    }

    public void start() throws IOException {
        //创建服务器 指定端口
        ServerSocket server = new ServerSocket(9999);
        while (true) {
            //接受客户端
            Socket client = server.accept();
            Mychannel mychannel = new Mychannel(client);
            all.add(mychannel);
            new Thread(mychannel).start();
        }
    }

    /**
     * 一个客户端 一个线程
     */
    private class Mychannel implements Runnable {
        //输入输出管道
        private DataInputStream dis;
        private DataOutputStream dos;
        //线程运行标识符
        private boolean isRunning = true;

        public Mychannel(Socket client) {
            try {
                dis = new DataInputStream(client.getInputStream());
                dos = new DataOutputStream(client.getOutputStream());
            } catch (IOException e) {
                //e.printStackTrace();
                CloseUtil.closeALL(dis, dos);
                isRunning = false;
                all.remove(this);
            }
        }



        /**
         * 接受数据
         * @return
         */
        public String receive() {
            String msg = "";
            try {
                msg = dis.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
                isRunning = false;
                CloseUtil.closeALL(dis, dos);
                all.remove(this);
            }

            return msg;
        }

        /**
         * 发送数据
         * @param msg
         */
        public void send(String msg) {
            if (null == msg || "" == msg) {
                return;
            }
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
                isRunning = false;
                CloseUtil.closeALL(dis, dos);
                all.remove(this);
            }
        }

        /**
         * 发送给其他客户端
         */
        public void sendOthers() {
            String msg = receive();
            for (Mychannel other : all) {
                if (other == this)
                    continue;
                //发送其他客户
                other.send(msg);
            }
        }

        @Override
        public void run() {
            while(isRunning) {
                //TODO
                sendOthers();
            }
        }
    }
}
