package com.example.adapter_kotlin
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.BaseAdapter

class MainActivity : AppCompatActivity() {
    class Data{
        var photo: Int ?= null
        var name : String ?= null
    }

// class MyAdapter(private var data: Array<Data?> , private var view:Int) : BaseAdapter1() {

    inner class MyAdpter(private val data: Array<Data?>, private val view: Int) :
        BaseAdapter() {
        override fun getCount(): Int {
            return data.size
        }

        override fun getItem(position: Int):Data?{
            return data[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View ? {

            var convertViewe = layoutInflater.inflate(view, parent, false)
            val name = convertViewe.findViewById<TextView>(R.id.name)
            name.setText(data[position]?.name)
            val imageview = convertViewe.findViewById<ImageView>(R.id.img_photo)
            imageview.setImageResource(data[position]?.photo!!)
            return convertViewe
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val transNameArray = arrayOf("腳踏車", "機車", "汽車", "巴士", "飛機", "船")
        val trainsPhotoIdArray = intArrayOf(
            R.drawable.trans1,
            R.drawable.trans2,
            R.drawable.trans3,
            R.drawable.trans4,
            R.drawable.trans5,
            R.drawable.trans6
        )
        val transData: Array<Data?> = arrayOfNulls(transNameArray.size)
        for (i in transData.indices) {
            transData[i] = Data()
            transData[i]!!.name = transNameArray[i]
            transData[i]!!.photo = trainsPhotoIdArray[i]
        }

        val transAdapter = MyAdpter(transData, R.layout.trans_list )
        val spinner = findViewById<Spinner>(R.id.spinner)
        spinner.adapter = transAdapter




        val messageArray = arrayOf("訊息1", "訊息2", "訊息3", "訊息4", "訊息5", "訊息6")
        val messageAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, messageArray)

        val listview = findViewById<ListView>(R.id.listView)
        listview.adapter = messageAdapter


        val cubeeNameArray = arrayOf("哭哭", "發抖", "再見", "生氣", "昏倒", "竊笑")
        val cubeePhotoIdArray = intArrayOf(
            R.drawable.cubee1,
            R.drawable.cubee2,
            R.drawable.cubee3,
            R.drawable.cubee4,
            R.drawable.cubee5,
            R.drawable.cubee6,
            R.drawable.cubee7,
            R.drawable.cubee8,
            R.drawable.cubee9,
            R.drawable.cubee10
        )


        val cubeeData: Array<Data?> = arrayOfNulls(cubeeNameArray.size)
        for (i in cubeeData.indices) {
            cubeeData[i] = Data()
            cubeeData[i]!!.name = cubeeNameArray[i]
            cubeeData[i]!!.photo = cubeePhotoIdArray[i]
        }
        val cubeeAdapter = MyAdpter(cubeeData, R.layout.cubee_list )
        val gridview = findViewById<GridView>(R.id.gridView)
        gridview.adapter = cubeeAdapter
        gridview.numColumns = 3


    }

}
