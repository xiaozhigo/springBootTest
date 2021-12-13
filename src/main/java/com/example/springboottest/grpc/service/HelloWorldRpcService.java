package com.example.springboottest.grpc.service;

import com.example.springboottest.grpc.HelloWorldGrpc;
import com.example.springboottest.proto.Greeting;
import com.example.springboottest.proto.HelloResp;
import io.grpc.stub.StreamObserver;

public class HelloWorldRpcService extends HelloWorldGrpc.HelloWorldImplBase{
    @Override
    public void sayHello(Greeting request, StreamObserver<HelloResp> responseObserver) {
        String name = request.getName();
        HelloResp resp = HelloResp.newBuilder()
                .setReply("Hello " + name + "!")
                .build();
        responseObserver.onNext(resp);
        responseObserver.onCompleted();
    }
}
