package com.ruide.robot

import android.app.Application
import com.ruide.aidl.IMConnectListener
import com.ruide.lib.IMLibClient
import java.util.concurrent.atomic.AtomicBoolean

class DemoApp :Application(){
    val  isConnect=AtomicBoolean(false)
    override fun onCreate() {
        super.onCreate()
        IMLibClient.getInstance().registerClient("F9C20F5F4667B033355EEED7E14B57DC")
        IMLibClient.getInstance().connect(this,object :IMConnectListener{
            override fun connected() {
                //连接上
                isConnect.set(true)

            }
            override fun disConnected() {
                isConnect.set(false)
            }
        })
    }
}