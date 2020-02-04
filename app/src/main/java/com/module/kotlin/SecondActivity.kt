package com.module.kotlin

import android.annotation.SuppressLint
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*
import android.content.Intent
import com.module.kotlin.R


class SecondActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        //take the value from  previous  activity
        lblData1.text = "Data Integer : "+intent.getIntExtra("dataInteger",0).toString()
        lblData2.text = "Data String : "+intent.getStringExtra("dataString")
        btnResult.setOnClickListener { PasingResult() }

    }

    private fun PasingResult() {
        val intent = Intent()
        intent.putExtra("id", 24)
        intent.putExtra("nama", "Ganda")
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
