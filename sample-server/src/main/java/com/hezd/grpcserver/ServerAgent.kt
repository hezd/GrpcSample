package com.hezd.grpcserver

import android.util.Log
import com.bizconf.grpcserver.GRPCListener
import com.bizconf.grpcserver.ServerHolder

/**
 *
 *@author hezd
 *Create on 2022/3/15 14:40
 */
object ServerAgent : GRPCListener {
    var TAG:String = ServerAgent::class.java.simpleName

    fun init(){
        ServerHolder.startServer()
        ServerHolder.setGrpcListener(this)
    }

    fun release(){
        ServerHolder.stop()
    }

    override fun sayHelloRequest() {
        Log.d(TAG,"grpc request:sayHelloRequest,do something")

    }

    fun sayHelloRequestComplete(msg:String){
        ServerHolder.sayHelloRequestComplete(msg)
    }
}