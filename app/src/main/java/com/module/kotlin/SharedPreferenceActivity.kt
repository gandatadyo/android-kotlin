package com.module.kotlin

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.module.kotlin.R
import kotlinx.android.synthetic.main.activity_shared_preference.*

class SharedPreferenceActivity : AppCompatActivity() {
    var prefs : SharedPreferences?= null
    var field_sharedpreference:String = "datashared1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preference)
        prefs = this.getSharedPreferences(" com.module.kotlin",0)
        btnSetShared.setOnClickListener { SetSharedPreferece() }
        btnShowShared.setOnClickListener { ShowSharedPreferece() }
    }

    private fun SetSharedPreferece(){
        val editor = prefs?.edit()
        editor?.putString(field_sharedpreference,edtShared.text.toString())
        editor?.apply()
    }

    private fun ShowSharedPreferece(){
        val sdata = prefs?.getString(field_sharedpreference,"")
        lblShared.text = sdata
    }
}
