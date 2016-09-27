package com.mingjalee.server.demo1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

/**
 * 服务器响应处理
 * @author jerry
 * @create 16/9/23 21:12
 */
public class Response {
    //回车
    public static final String CRLF = "\r\n";
    //空格
    public static final String BLANK = " ";

    //存储头信息
    private StringBuilder headInfo;
    //正文
    private StringBuilder content;
    //输出流
    private BufferedWriter bw;

    public Response() {
        headInfo = new StringBuilder();
        content = new StringBuilder();
    }

    public Response(OutputStream os) {
        this();
        bw = new BufferedWriter(new OutputStreamWriter(os));
    }

    public Response(Socket client){
        this();
        try {
            bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        } catch (IOException e) {
            headInfo = null;
        }
    }

    /**
     * 构建头信息
     * @param code
     */
    private void createHeadInfo (int code) {
        //http协议 状态码 描述
        headInfo.append("HTTP/1.1").append(BLANK).append(code).append(BLANK);
        switch (code) {
            case 200:
                headInfo.append("OK");
                break;
            case 404:
                headInfo.append("NOT FOUND");
                break;
            case 500:
                headInfo.append("SERVER ERROR");
        }
        headInfo.append(CRLF);

        //响应头
        headInfo.append("Server:mingjalee Server/0.0.1").append(CRLF);
        headInfo.append("Data:").append(new Date()).append(CRLF);
        headInfo.append("Content-type:text/html;charset=UTF-8").append(CRLF);
        //正文字节长度
        int len = content.toString().getBytes().length;
        headInfo.append("Content-length:").append(len).append(CRLF);
        //正文之前 换行
        headInfo.append(CRLF);
    }

    /**
     * 构建正文
     * @param info
     * @return
     */
    public Response print(String info) {
        content.append(info);
        return this;
    }

    /**
     * 构建正文 + 回车
     * @param info
     * @return
     */
    public Response println(String info) {
        content.append(info);
        content.append(CRLF);
        return this;
    }

    /**
     * 推送到客户端
     * @param code
     */
    public void pushToClient(int code) throws IOException {
        //创建头信息(异常处理), 正文已赋值完毕, 头信息与正义一起推送到客户端
        if (null == headInfo) {
            code = 500;
        }

        //头信息构建
        createHeadInfo(code);

        //发送 头信息+分隔符
        bw.append(headInfo.toString());
        //发送 正文
        bw.append(content.toString());

        System.out.println("服务响应头信息" + CRLF + headInfo.toString());
        System.out.println("服务响应正文" + CRLF + content.toString());

        bw.close();
    }


    /**
     * 输出结束, 关闭输出流
     */
    public void close() {
        try {
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
