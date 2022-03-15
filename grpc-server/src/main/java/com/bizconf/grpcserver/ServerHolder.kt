package com.bizconf.grpcserver

import io.grpc.Server
import io.grpc.netty.NettyServerBuilder

/**
 *
 *@author hezd
 *Create on 2022/3/14 16:31
 */
object ServerHolder {
    private var server: Server? = null

    fun startServer() {
        server = NettyServerBuilder.forPort(PORT)
            .addService(GreeterImpl)
            .build()
            .start()
        Runtime.getRuntime().addShutdownHook(object : Thread() {
            override fun run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down")
                ServerHolder.stop()
                System.err.println("*** server shut down")
            }
        })
    }

    fun stop() {
        server?.shutdown()
    }

    fun setGrpcListener(grpcListener: GRPCListener){
        GreeterImpl.setGrpcListener(grpcListener)
    }

    fun sayHelloRequestComplete(msg:String){
        GreeterImpl.sayHelloComplete(msg);
    }
}