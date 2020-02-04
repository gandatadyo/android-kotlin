package com.module.kotlin

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.module.kotlin.R
import kotlinx.android.synthetic.main.activity_database.*

class DatabaseActivity : AppCompatActivity() {
    private val db = DataBaseHandler(this)
    var dataname = ArrayList<String>()
    var dataid = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)
        btnAdd.setOnClickListener {
            if((edtNama.text.toString() != "")&&(edtNomer.text.toString() != ""))
                AddData() else toasttest("Data must be filled", this)
        }

        val mMessageClickedHandler = AdapterView.OnItemClickListener { parent, v, position, id ->
                toasttest("\"" + dataname[position] + "\" deleted", this)
                DeleteData(dataid[position])
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
        val datatemp = DataSchemDBCache(nama.toString(), nomor.toString().toInt())
        val bol = db.insertData(datatemp)
        if (bol){
            edtNama.setText("")
            edtNomer.setText("")
            toasttest("Succesfully", this)
            ShowData()
        }else{
            toasttest("Not Succesfully", this)
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
