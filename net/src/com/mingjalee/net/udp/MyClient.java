package com.mingjalee.net.udp;


import java.io.IOException;
import java.net.*;

/**
 * @author jerry
 * @create 16/9/22 10:46
 */
public class MyClient {
    public static void main(String[] args) throws IOException {
        //创建socket
        DatagramSocket client = new DatagramSocket(6666);
        //准备数据
        String msg = "udp编程";
        byte[] data = msg.getBytes();
        //数据打包,指定地址,端口
        DatagramPacket packet =
                new DatagramPacket(data, data.length, new InetSocketAddress("localhost", 8888));
        //发送数据
        client.send(packet);
        //释放资源
        client.close();
    }
}
