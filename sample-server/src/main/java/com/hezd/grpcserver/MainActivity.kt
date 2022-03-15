package com.hezd.grpcserver

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.appcompat.app.AppCompatActivity

/**
 * @author hezd
 * Create on 2022-3-15 19:54:14
 */
class MainActivity : AppCompatActivity() {
    private val handler = object :Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            when(msg.what){
                100->{
                    ServerAgent.sayHelloRequestComplete("request complete")
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ServerAgent.init()
        handler.sendEmptyMessageDelayed(100,3000)
    }

    override fun onDestroy() {
        super.onDestroy()
        ServerAgent.release()
    }
}