package com.example.springboottest.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",9001);
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream);
        printWriter.write("socket客户端发送消息!");
        printWriter.flush();
        socket.shutdownOutput();

        printWriter.close();
        outputStream.close();
    }
}
