package com.example.lab7_3_kotlin

import androidx.appcompat.app.AppCompatActivity

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_calculate.setOnClickListener {
            if (ed_height.length()<1)
                Toast.makeText(this, "請輸入身高"
                    , Toast.LENGTH_SHORT).show()
            else if (ed_weight.length()<1)
                Toast.makeText(this, "請輸入體重"
                    , Toast.LENGTH_SHORT).show()
            else
                runAsyncTask()
        }
    }
    @SuppressLint("StaticFieldLeak")
    private fun runAsyncTask(){
        object :AsyncTask<Void,Int,Boolean>(){
            override fun onPreExecute() {
                super.onPreExecute()
                ll_progress.visibility = View.VISIBLE
                tv_weight.text = "標準體重\n無"
                tv_bmi.text = "體脂肪\n無"
                progressBar2.progress = 0
                tv_progress.text = "0%"
            }
            override fun doInBackground(vararg params: Void?): Boolean {
                var progress = 0
                while (progress <= 100) {
                    try {
                        Thread.sleep(50)
                        publishProgress(progress)
                        progress++
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
                return true
            }

            override fun onProgressUpdate(vararg values: Int?) {
                super.onProgressUpdate(*values)
                values[0]?.let {
                    progressBar2.progress=it
                    tv_progress.text= String.format("%d%%",it)
                }
            }
            override fun onPostExecute(status: Boolean?) {
                ll_progress.visibility = View.GONE
                val h = ed_height.text.toString().toDouble()
                val w = ed_weight.text.toString().toDouble()
                val standWeight : Double
                val bodyFat : Double
                if (btn_boy.isChecked) {
                    standWeight = (h - 80) * 0.7
                    bodyFat= (w - 0.88 * standWeight) / w * 100
                } else {
                    standWeight = (h - 70) * 0.6
                    bodyFat = (w - 0.82 * standWeight) / w * 100
                }
                tv_weight.text = String.format("標準體重 \n%.2f", standWeight)
                tv_bmi.text = String.format("體脂肪 \n%.2f", bodyFat)
            }
        }.execute()
    }
}