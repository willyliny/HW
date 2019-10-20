package com.example.my_menu_kotlin

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.main2.*

class aty2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main2)

        btn_send.setOnClickListener{
            if (ed_drink.length()>1){
                val b = Bundle()
                b.putString("drink_level", ed_drink.text.toString())
                b.putString("sugar_level",
                    radioGroup1.findViewById<RadioButton>(radioGroup1.checkedRadioButtonId).text.toString())
                b.putString("ice_level",
                    radioGroup.findViewById<RadioButton>(radioGroup.checkedRadioButtonId).text.toString())
                setResult(101, Intent().putExtras(b))
                finish()
            }
        }
    }
}