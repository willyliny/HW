package com.example.api_java;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private Button btn_quary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_quary = findViewById(R.id.btn_query);
        registerReceiver(receiver,new IntentFilter("Mymessage"));
        btn_quary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Request req = new Request.Builder()
                        .url("https://data.taipei/opendata/datalist/apiAccess?" +
                                "scope=resourceAquire&rid=55ec6d6e-dc5c-4268-a725-d04cc262172b").build();

                new OkHttpClient().newCall(req).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Log.e("查詢失敗",e.toString());
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        sendBroadcast(new Intent("Mymessage")
                                .putExtra("json",response.body().string()));
                    }
                });
            }
        });
    }


    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Data data = new Gson().fromJson(intent.getExtras().getString("json"),Data.class);
            final String[] items = new String[data.result.results.length];
            for(int i=0;i<items.length;i++){
                items[i] = "\n列車即將進入 :"+
                        data.result.results[i].Station+
                        "\n列車行駛目的地 :"+
                        data.result.results[i].Destination;

            }
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("台北捷運列車到站站名")
                            .setItems(items,null)
                            .show();
                }
            });
        }
    };

}

class Data {
    Result result;
    class Result{
        Results[] results;
        class  Results{
            String Station;
            String Destination;
        }
    }
}
