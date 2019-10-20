package com.example.my_menu_kotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.main1.*

class aty1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main1)
        val intent = Intent(this,aty2::class.java)
        btn_choice.setOnClickListener {
            startActivityForResult(intent,0)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val b = data?.extras?:return
        if(requestCode == 0){
            if(resultCode == 101){
                val str1 = b.getString("drink_level")
                val str2 = b.getString("sugar_level")
                val str3 = b.getString("ice_level")

                val t_drink = findViewById<TextView>(R.id.tv_drink_null)
                val t_sugar = findViewById<TextView>(R.id.tv_sugar_null)
                val t_ice = findViewById<TextView>(R.id.tv_ice_null)

                t_drink.text=str1
                t_sugar.text=str2
                t_ice.text=str3

            }
        }
    }
}
