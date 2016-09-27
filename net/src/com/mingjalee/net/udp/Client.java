package com.mingjalee.net.udp;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * udp 传输数据类型 byte[] -> double
 * @author jerry
 * @create 16/9/22 11:06
 */
public class Client {
    public static void main(String[] args) throws IOException {
        //创建socket
        DatagramSocket client = new DatagramSocket(6666);
        //准备数据
        double num = 3.14;
        byte[] data = convert(num);
        //数据打包,指定地址,端口
        DatagramPacket packet =
                new DatagramPacket(data, data.length, new InetSocketAddress("localhost", 8888));
        //发送数据
        client.send(packet);
        //释放资源
        client.close();
    }

    public static byte[] convert(double num) throws IOException {
        byte[] data;

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);

        dos.writeDouble(num);
        dos.flush();


        //获取数据
        data = bos.toByteArray();

        dos.flush();

        return data;
    }
}
