package com.example.springboottest.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9001);
        Socket socket;
        InputStream inputStream;
        InputStreamReader inputStreamReader;
        BufferedReader reader;
        while (true){
            socket = serverSocket.accept();
            inputStream = socket.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);
            String info = "";
            while ((info=reader.readLine()) != null){
                System.out.println(info);
            }
            socket.shutdownInput();
        }
    }
}
