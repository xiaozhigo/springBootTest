package com.example.springboottest.test;

import com.example.springboottest.grpc.client.HelloWorldClient;
import com.example.springboottest.grpc.server.GrpcServer;

import java.io.IOException;

public class GrpcTest {

    public static void main(String[] args) throws IOException {
        int port = 8000;
        GrpcServer server = new GrpcServer(port);
        server.start();
        HelloWorldClient client = new HelloWorldClient("localhost", port);
        String reply = client.sayHello("HanMeiMei");
        System.out.println(reply);
        server.shutdown();
    }
}
