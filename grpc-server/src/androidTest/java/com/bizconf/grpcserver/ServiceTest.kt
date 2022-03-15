package com.bizconf.grpcserver

import android.content.ServiceConnection
import android.content.ComponentName
import android.os.IBinder

/**
 * @author hezd
 * Create on 2022/3/14 12:29
 */
class ServiceTest {
    fun test() {
        val serviceConnection: ServiceConnection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName, service: IBinder) {}
            override fun onServiceDisconnected(name: ComponentName) {}
        }
    }
}