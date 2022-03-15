package com.bizconf.grpcclient

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.examples.helloworld.GreeterGrpc
import io.grpc.examples.helloworld.HelloRequest
import java.util.concurrent.Executors

/**
 *
 *@author hezd
 *Create on 2022/3/15 11:30
 */
object RequestManager {
    private val threadPool = Executors.newSingleThreadExecutor()
    private var host: String? = null
    private var port: Int? = null
    private var channel: ManagedChannel? = null

    fun init(host: String, port: Int) {
        this.host = host
        this.port = port
    }

    fun sayHelloRequest(msg: String,callBack:CallBack) {
        require(host!=null){
            "host is null,please call init method first"
        }

        require(port != null) {
            "port is null,please call init method first"
        }
        threadPool.execute {
            channel = ManagedChannelBuilder.forAddress(host, port!!).usePlaintext().build()
            val stub = GreeterGrpc.newBlockingStub(channel)
            val request = HelloRequest.newBuilder().setName(msg).build()
            val reply = stub.sayHello(request)
            callBack.onResponse(reply.message)
        }
    }

    interface CallBack{
        fun onResponse(result:String)
    }

}