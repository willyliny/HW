package com.example.service_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_start.setOnClickListener {
            startService(Intent(this, MyService::class.java))
            Toast.makeText(
                this, "啟動Service",
                Toast.LENGTH_LONG
            ).show()
            finish()
        }
    }
}
