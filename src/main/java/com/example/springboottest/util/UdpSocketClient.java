package com.example.springboottest.util;

import java.io.IOException;
import java.net.*;

public class UdpSocketClient {
    public static void main(String[] args) throws IOException {
        String str = "UDP socket发送";
        InetAddress localHost = InetAddress.getLocalHost();
        DatagramPacket datagramPacket = new DatagramPacket(str.getBytes(),str.getBytes().length,localHost,8088);
        DatagramSocket socket = new DatagramSocket();
        socket.send(datagramPacket);
        socket.close();
    }
}
