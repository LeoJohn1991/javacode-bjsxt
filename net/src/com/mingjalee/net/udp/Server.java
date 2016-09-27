package com.mingjalee.net.udp;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * udp 传输数据类型 byte[] -> double
 * @author jerry
 * @create 16/9/22 11:06
 */
public class Server {
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
        double num = convert(packet.getData());
        System.out.println(num);
        //释放资源
        server.close();
    }

    public static double convert(byte[] data) throws IOException {
        double num;
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));

        num = dis.readDouble();

        return num;
    }
}
