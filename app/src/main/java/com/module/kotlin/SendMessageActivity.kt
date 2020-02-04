package com.module.kotlin

import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_send_message.*


class SendMessageActivity : AppCompatActivity() {
    lateinit var phone:String
    lateinit var content:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_message)

        btnSendMessage.setOnClickListener {
            phone = edtNo_SendMessage.text.toString()
            content = edtNo_SendMessage.text.toString()
            SendMessage()
        }
    }

    private fun SendMessage(){
        try {
            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phone, null, content, null, null)
            Toast.makeText(this, "Message Sent",Toast.LENGTH_LONG
            ).show()
        } catch (ex: Exception) {
            Toast.makeText(this, ex.message.toString(),Toast.LENGTH_LONG).show()
            ex.printStackTrace()
        }
    }
}
