package com.example.kotlin

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        //take the value from  previous  activity
        lblData1.text = "Data Integer : "+intent.getIntExtra("dataInteger",0).toString()
        lblData2.text = "Data String : "+intent.getStringExtra("dataString")
    }
}
