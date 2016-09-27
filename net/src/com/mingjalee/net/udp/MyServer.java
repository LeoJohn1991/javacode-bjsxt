package com.mingjalee.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author jerry
 * @create 16/9/22 10:46
 */
public class MyServer {
    public static void main(String[] args) throws IOException {
        //创建服务端socket
        DatagramSocket server = new DatagramSocket(8888);
        //准备容器
        byte[] container = new byte[1024];
        //封装成包
        DatagramPacket packet = new DatagramPacket(container, container.length);
        //接受数据
        server.receive(packet);
        //分析数据
        byte[] data = packet.getData();
        int len = packet.getLength();
        System.out.println(new String(data, 0, len));
        //释放资源
        server.close();
    }
}
