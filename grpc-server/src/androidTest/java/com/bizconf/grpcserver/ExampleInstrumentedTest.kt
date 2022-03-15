package com.bizconf.grpcserver

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.grpc.stub.StreamObserver

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//        assertEquals("com.bizconf.grpcserver.test", appContext.packageName)
        val intent = Intent(appContext, GRPCService::class.java)
        val serviceConnection: ServiceConnection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder) {
                val binder: GRPCService.GRPCBinder = service as GRPCService.GRPCBinder
                binder.registerListener(object : GRPCListener {
                    override fun <T> sayHelloRequest(
                        helloReply: T,
                        streamObserver: StreamObserver<T>
                    ) {
                        streamObserver.onNext(helloReply)
                        streamObserver.onCompleted()
                    }


                })
                binder.linkToDeath({
                    appContext.bindService(intent, this, Service.BIND_AUTO_CREATE)
                }, 0)
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                TODO("Not yet implemented")
            }

        }
        appContext.bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE)
    }
}