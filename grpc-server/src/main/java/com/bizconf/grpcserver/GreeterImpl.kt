package com.bizconf.grpcserver

import android.util.Log
import io.grpc.examples.helloworld.GreeterGrpc
import io.grpc.examples.helloworld.HelloReply
import io.grpc.examples.helloworld.HelloRequest
import io.grpc.stub.StreamObserver

/**
 *
 *@author hezd
 *Create on 2022/3/14 15:47
 */
object GreeterImpl : GreeterGrpc.AbstractGreeter() {
    private var listener: GRPCListener?=null
    private val Tag = GreeterImpl::class.java.simpleName
    private var helloRequest:HelloRequest?=null
    private var helloObserver:StreamObserver<HelloReply>?=null

    override fun sayHello(request: HelloRequest, responseObserver: StreamObserver<HelloReply>) {
        Log.d(Tag,"$Tag ${request.name}")
        val replyMsg = "Hello ${request.name}"
        val reply = HelloReply.newBuilder().setMessage(replyMsg).build()
        helloObserver = responseObserver
        helloRequest = request
        listener?.sayHelloRequest()
    }

    fun sayHelloComplete(replyMsg:String){
        val msg = " ${helloRequest?.name} : ${replyMsg}"
        val helloReply = HelloReply.newBuilder().setMessage(msg).build()
        helloObserver?.onNext(helloReply)
        helloObserver?.onCompleted()
        helloRequest = null
        helloObserver = null
    }

    fun setGrpcListener(grpcListener: GRPCListener){
        this.listener = grpcListener
    }
}