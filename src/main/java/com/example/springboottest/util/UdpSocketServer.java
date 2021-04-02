package com.example.springboottest.util;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpSocketServer {
    public static void main(String[] args) throws IOException {
        byte[] bytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(bytes,bytes.length);
        DatagramSocket datagramSocket = new DatagramSocket(8088);
        datagramSocket.receive(datagramPacket);

        String s = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
        System.out.println(s);
        datagramSocket.close();
    }
}
