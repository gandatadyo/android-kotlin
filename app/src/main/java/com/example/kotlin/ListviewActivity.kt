package com.example.kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_listview.*
import android.widget.ArrayAdapter
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener





class ListviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listview)

        val dataarray = listOf("Informatika","Manajemen","Hukum","Desain Grafis","Akutansi")
        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, dataarray)
        listview.adapter = adapter

        val mMessageClickedHandler = OnItemClickListener { parent, v, position, id ->
            toasttest(dataarray[position],this)
        }
        listview.onItemClickListener = mMessageClickedHandler
    }
}
