package com.mingjalee.tcp.chat.demo4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 实现聊天 服务端
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
        //用户名
        private String name;

        public Mychannel(Socket client) {
            try {
                dis = new DataInputStream(client.getInputStream());
                dos = new DataOutputStream(client.getOutputStream());
                this.name = dis.readUTF();
                this.send("欢迎你来到聊天室");
                this.sendOthers(this.name + "进入聊天室", true);
                System.out.println(this.name + "进入聊天室");
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
         * @param msg 发送的数据
         * @param sys 系统信息标志
         */
        public void sendOthers(String msg, boolean sys) {
            if (msg.startsWith("@") && msg.indexOf(":") > -1) {
                String name = msg.substring(1, msg.indexOf(":"));
                String str = msg.substring(msg.indexOf(":") + 1);

                System.out.println("[" + this.name + " -> " + name + "] " + str);
                //System.out.println(name + "=========");
                //System.out.println(str + "==========");
                for (Mychannel other : all) {
                    if (name.equals(other.name)) {
                        //System.out.println(name + "=========" + str);
                        other.send(this.name + "悄悄对你说" + str);
                    }
                }

            }
            else {
                if (!sys) {
                    System.out.println("[" + this.name + " -> all] " + msg );
                }
                for (Mychannel other : all) {
                    if (other == this)
                        continue;
                    //发送其他客户
                    if (sys) {
                        other.send("系统信息: " + msg);
                    }
                    else {
                        other.send(this.name + " say to All: " + msg);
                    }
                }
            }
        }

        @Override
        public void run() {
            while(isRunning) {
                //TODO
                sendOthers(receive(), false);
            }
        }
    }
}
