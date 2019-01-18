package com.example.kotlin

import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_database.*
import kotlinx.android.synthetic.main.activity_listview.*

class DatabaseActivity : AppCompatActivity() {
    private val db = DataBaseHandler(this)
    var dataname = ArrayList<String>()
    var dataid = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)
        btnAdd.setOnClickListener { AddData() }

        val mMessageClickedHandler = AdapterView.OnItemClickListener { parent, v, position, id ->
            DeleteData(dataid[position])
            toasttest("\""+dataname[position]+"\" deleted", this)
        }
        listviewDatabase.onItemClickListener = mMessageClickedHandler
    }

    private fun ShowData(){
        val cursor:Cursor = db.selectDataCursor("select * from dbmdata")
        dataname.clear()
        dataid.clear()
        if(cursor.moveToFirst()){
            do {
                dataname.add(cursor.getString(cursor.getColumnIndex("Nama")))
                dataid.add(cursor.getString(cursor.getColumnIndex("ID")))
            }while (cursor.moveToNext())
        }
        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, dataname)
        listviewDatabase.adapter = adapter
    }

    private fun AddData(){
        val nama = edtNama.text
        val nomor = edtNomer.text
        val datatemp = DataSchemDBCache(nama.toString(),nomor.toString().toInt())
        val bol = db.insertData(datatemp)
        if (bol){
            edtNama.setText("")
            edtNomer.setText("")
            toasttest("Succesfully",this)
            ShowData()
        }else{
            toasttest("Not Succesfully",this)
        }
    }

    private fun DeleteData(iddata:String){
        val selectionArgs = arrayOf(iddata)
        db.deleteData("ID=?", selectionArgs)
        ShowData()
    }

    override fun onResume() {
        super.onResume()
        ShowData()
    }
}