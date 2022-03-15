package com.bizconf.grpcserver

import io.grpc.stub.StreamObserver

/**
 *
 *@author hezd
 *Create on 2022/3/14 11:18
 */
interface GRPCListener{
    fun  sayHelloRequest()
}