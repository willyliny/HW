package com.example.my_menu_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup

class aty2 : AppCompatActivity() {

    val sent_btn = findViewById<Button>(R.id.btn_send)
    val set_drink = findViewById<EditText>(R.id.ed_drink)
    val rg1 = findViewById<RadioGroup>(R.id.radioGroup1)
    val rg =  findViewById<RadioGroup>(R.id.radioGroup)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main2)
        sent_btn.setOnClickListener{
            if (set_drink.length()>1){
                val b = Bundle()
                b.putString("sugar_level",rg1.checkedRadioButtonId.toString())
                b.putString("ice_level",rg.checkedRadioButtonId.toString())
                b.putString("drink_level",set_drink.text.toString())
                setResult(101, Intent().putExtras(b))
                finish()
            }
        }
    }
}
