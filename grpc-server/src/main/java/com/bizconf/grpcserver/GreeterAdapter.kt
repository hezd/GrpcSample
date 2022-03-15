package com.bizconf.grpcserver

import io.grpc.examples.helloworld.GreeterGrpc
import io.grpc.examples.helloworld.HelloReply
import io.grpc.examples.helloworld.HelloRequest
import io.grpc.stub.StreamObserver

/**
 *
 *@author hezd
 *Create on 2022/3/15 14:44
 */
abstract class GreeterAdapter :GreeterGrpc.Greeter {
    override fun sayHello(request: HelloRequest?, responseObserver: StreamObserver<HelloReply>?) {

    }
}