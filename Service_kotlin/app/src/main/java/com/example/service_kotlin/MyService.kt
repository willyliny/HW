package com.example.service_kotlin

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyService : Service() {

    override fun onCreate() {
        super.onCreate()
        Thread(Runnable {
            try {
                Thread.sleep(5000)

                val intent = Intent(
                    this,
                    Main2Activity::class.java
                )
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                this.startActivity(intent)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }).start()
        stopSelf()
    }
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}
