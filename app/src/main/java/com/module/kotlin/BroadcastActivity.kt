package com.module.kotlin

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsMessage
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_broadcast.*

class BroadcastActivity : AppCompatActivity() {
    val br: BroadcastReceiver = ExampleBroadcast()
    private lateinit var downloadReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast)

        val filter = IntentFilter("ExampleBroadcast1")
        registerReceiver(br, filter)

        btnBroadcast_broadcastactivity.setOnClickListener {
            SendBroadcastExample()
        }

        downloadReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val bundle = intent.extras
                try {
                    if (bundle != null) {
                        val pdusObj = bundle.get("pdus") as Array<Any>
                        for (aPdusObj in pdusObj) {
                            val currentMessage = getIncomingMessage(aPdusObj, bundle)
                            val senderNum = currentMessage.displayOriginatingAddress
                            val message = currentMessage.displayMessageBody
                            Toast.makeText(context,"No : $senderNum / Message : $message", Toast.LENGTH_LONG).show()
//                            Log.d(TAG, "senderNum: $senderNum; message: $message")
                        }
                    }
                } catch (e: Exception) {
//                    Log.d(TAG, "Exception smsReceiver$e")
                }
            }
        }
//        val downloadIntentFilter = IntentFilter("android.provider.Telephony.SMS_RECEIVED")
//        registerReceiver(downloadReceiver, downloadIntentFilter)
    }


    private fun SendBroadcastExample(){
        sendBroadcast(Intent("ExampleBroadcast1"))
    }

    private fun getIncomingMessage(aObject: Any, bundle: Bundle): SmsMessage {
        val currentSMS: SmsMessage
        if (Build.VERSION.SDK_INT >= 23) {
            val format = bundle.getString("format")
            currentSMS = SmsMessage.createFromPdu(aObject as ByteArray, format)
        } else currentSMS = SmsMessage.createFromPdu(aObject as ByteArray)
        return currentSMS
    }

    override fun isDestroyed(): Boolean {
        return super.isDestroyed()
        unregisterReceiver(br)
    }
}
