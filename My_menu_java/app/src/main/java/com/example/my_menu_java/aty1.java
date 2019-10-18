package com.example.my_menu_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class aty1 extends AppCompatActivity {
    Button btn;
    TextView t_drink;
    TextView t_sugar;
    TextView t_ice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main1);
        btn = (Button) findViewById(R.id.btn_choice);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(aty1.this,aty2.class);
                startActivityForResult(i,0);
            }
        });
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        if(requestCode == 0){
            if(resultCode == 101){
                Bundle b = data.getExtras();
                String str1 = b.getString("drink_level");
                String str2 = b.getString("sugar_level");
                CharSequence str3 = b.getString("ice_level");

                t_drink = findViewById(R.id.tv_drink_null);
                t_sugar = findViewById(R.id.tv_sugar_null);
                t_ice = findViewById(R.id.tv_ice_null);

                t_drink.setText(str1);
                t_sugar.setText(str2);
                t_ice.setText(str3);
            }
        }
    }
}

