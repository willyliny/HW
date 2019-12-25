package com.example.lab11_sqlite_kotlin

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    private val listView: ListView? = null
    private var adapter: ArrayAdapter<String>? = null
    private val items = ArrayList<String>()
    private var dbrw: SQLiteDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        listView?.adapter = adapter
        //取得資料庫實體
        dbrw = MyDBHelper(this).writableDatabase
        btn_query.setOnClickListener {
            var c: Cursor
            if (ed_book.length() < 1)
                c = dbrw!!.rawQuery("SELECT * FROM myTable", null)
            else
                c = dbrw!!.rawQuery("SELECT * FROM myTable WHERE book LIKE '\${ed_book.text}'", null)
            c.moveToFirst()
            items.clear()
            Toast.makeText(this, "共有" + c.count + "筆資料", Toast.LENGTH_SHORT).show()
            for (i in 0 until  c.count ) {
                items.add("書名" + c.getString(0) + "\t\t\t\t價格:" + c.getString(1))
                c.moveToNext()
            }
            adapter!!.notifyDataSetChanged()
            c.close()
        }

        btn_insert.setOnClickListener {
            if (ed_book.length()<1||ed_price.length()<1)
                Toast.makeText(this,"欄位請勿留空",Toast.LENGTH_SHORT).show()
            else
                try {
                    dbrw!!.execSQL("INSERT INTO myTable(book, price) VALUES(?,?)",
                        arrayOf<Any>(
                            ed_book.text.toString(),
                            ed_price.text.toString()
                        ))
                    Toast.makeText(this,"新增書名"+ed_book.text.toString()+
                    "價格"+ed_price.text.toString(),Toast.LENGTH_SHORT).show()
                    ed_book.setText("")
                    ed_price.setText("")
                }
                catch (e:Exception){
                    Toast.makeText(this, "新增失敗:" +e , Toast.LENGTH_LONG).show()
                }
        }
        btn_update.setOnClickListener {
            if (ed_book.length()<1||ed_price.length()<1)
                Toast.makeText(this,"欄位請勿留空",Toast.LENGTH_SHORT).show()
            else
                try {
                    dbrw!!.execSQL("UPDATE myTable SET price = ${ed_price.text} WHERE book LIKE '${ed_book.text}'")
                    Toast.makeText(this,"更新書名"+ed_book.text.toString()+"價格"+ed_price.text.toString(),Toast.LENGTH_SHORT).show()
                    ed_book.setText("")
                    ed_price.setText("")
                }
                catch (e:Exception){
                    Toast.makeText(this,"更新失敗:"+e ,Toast.LENGTH_LONG).show()
                }
        }
        btn_delete.setOnClickListener {
            if (ed_book.length()<1)
                Toast.makeText(this,"書名請勿留空",Toast.LENGTH_SHORT).show()
            else
                try {
                    dbrw!!.execSQL("DELETE FROM myTable WHERE book LIKE '${ed_book.text}'")
                    Toast.makeText(this,"刪除書名"+ed_book.text.toString(),Toast.LENGTH_SHORT).show()
                    ed_book.setText("")
                    ed_price.setText("")
                }
                catch (e:Exception){
                    Toast.makeText(this,"刪除失敗:"+e ,Toast.LENGTH_LONG).show()
                }
            }
        }

        override fun onDestroy() {
            super.onDestroy()
            dbrw?.close()
        }
}


