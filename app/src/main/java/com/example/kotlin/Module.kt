package com.example.kotlin

import android.content.Context
import android.os.Handler
import android.widget.Toast


val toasttest = {msg:String,context:Context -> Toast.makeText(context,msg, Toast.LENGTH_SHORT).show()}
class Run {
    companion object {
        // this is function use style lambda
        fun after(delay: Long, process: () -> Unit) {
            Handler().postDelayed({
                process()
            }, delay)
        }
    }
}