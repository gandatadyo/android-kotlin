package com.module.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_listview.*
import android.widget.ArrayAdapter
import android.widget.AdapterView.OnItemClickListener
import com.module.kotlin.R

class ListviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listview)

        // inisialisasi data awal ketika muncul
        val dataarray = listOf("Informatika","Manajemen","Hukum","Desain Grafis","Akutansi")
        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, dataarray)
        listview.adapter = adapter


        // inisialisasi listview onlclick
        val mMessageClickedHandler = OnItemClickListener { parent, v, position, id ->
            toasttest(dataarray[position], this)
        }
        listview.onItemClickListener = mMessageClickedHandler

        // inisialisasi reload data
        btnReset.setOnClickListener {
            ResetData()
        }
    }

    private fun ResetData(){
        // reload data (reset)
        val dataarray = listOf("Tata Boga","Sastra Inggris","Teknik Industri","Elektro")
        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, dataarray)
        listview.adapter = adapter
    }
}
