package com.mingjalee.server.demo1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * 创建服务器 启动
 * 请求
 * 响应
 * @author jerry
 * @create 16/9/23 19:23
 */
public class Server3 {
    private ServerSocket server = null;
    public static final String CRLF = "\r\n";
    public static final String BlANK = " ";

    public static void main(String[] args) {
        new Server3().start();
    }

    /**
     *  启动服务器
     */
    public void start() {
        try {
            server = new ServerSocket(8888);
            receive();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 服务器接受请求
     */
    public void receive() {
        try {
            Socket client = server.accept();
            byte[] data = new byte[20480];
            int len = client.getInputStream().read(data);

            //客户端请求信息
            System.out.println(client.getInetAddress().toString() + "客户请求=========");
            String requestInfo = new String(data, 0, len);
            System.out.println(requestInfo);

            //响应
            StringBuilder responseContext = new StringBuilder();
            responseContext.append("<html><head><title>响应测试</title></head>" +
                    "<body>Hello Html</body></html>");

            StringBuilder response = new StringBuilder();
            //http协议版本 状态代码 描述
            response.append("HTTP/1.1").append(BlANK).append("200").append(BlANK).append("ok").append(CRLF);
            //响应头
            response.append("Server:mingjalee Server/0.0.0.1").append(CRLF);

            response.append("Date:").append(new Date()).append(CRLF);
            response.append("Content-type:text/html;charset=UTF-8").append(CRLF);

            //正文字节长度
            response.append("Content-lenth:").append(responseContext.toString().getBytes().length).append(CRLF);
            //正文之前
            response.append(CRLF);
            //正文
            response.append(responseContext);


            System.out.println("服务端响应:");
            System.out.println(response);
            //输出流
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            bw.write(response.toString());
            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止服务器
     */
    public void stop() {

    }

}
