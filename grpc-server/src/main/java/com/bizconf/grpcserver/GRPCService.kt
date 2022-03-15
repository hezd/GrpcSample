package com.bizconf.grpcserver

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

/**
 *
 *@author hezd
 *Create on 2022/3/14 10:13
 */
class GRPCService :Service() {
    private val TAG = GRPCService::class.java.simpleName
    private val grpcListener:GRPCListener?=null
    private val grpcBinder = GRPCBinder()

    override fun onCreate() {
        super.onCreate()
        startServer()
    }

    private fun startServer() {
        ServerHolder.startServer()
    }


    override fun onBind(intent: Intent?): IBinder {
        return grpcBinder
    }

    class GRPCBinder :Binder(){
        private var listener: GRPCListener?=null

        fun registerListener(grpcListener: GRPCListener){
            this.listener = grpcListener
        }

        fun unregisterListener(grpcListener: GRPCListener){
            listener = null
        }
    }

}