package com.hezd.grpcclient

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bizconf.grpcclient.PORT
import com.bizconf.grpcclient.RequestManager

/**
 * @author hezd
 * Create on 2022-3-15 19:53:43
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        RequestManager.init("localhost", PORT)

        window.decorView.postDelayed({
                sayHelloRequest()
            }
            , 2000
        )
    }

    private fun sayHelloRequest() {
        RequestManager.sayHelloRequest("Hezd", object : RequestManager.CallBack {
            override fun onResponse(result: String) {
                Log.d("MainActivity", "currentThread :${Thread.currentThread().name}")
                runOnUiThread {
                    Toast.makeText(this@MainActivity, result, Toast.LENGTH_SHORT).show()
                }
            }

        })
    }
}