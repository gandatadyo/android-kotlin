package com.module.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.module.kotlin.R
import kotlinx.android.synthetic.main.activity_volley.*

class VolleyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volley)
        btnVolley.setOnClickListener {
            GetDataVolleySimple()
        }
        btnVolleyWithData.setOnClickListener {
            GetDataVolleyWithPassingData()
        }
    }

    // Send a simple request
    private fun GetDataVolleySimple() {
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.1.4:8080/print"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                toasttest("Response is: $response", this)
            },
            Response.ErrorListener { toasttest("That didn't work!", this) })
        queue.add(stringRequest)
    }

    private fun GetDataVolleyWithPassingData() {
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.1.4:8080/get"
        val postRequest = object :  StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response ->
                Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
            }, Response.ErrorListener {
                Toast.makeText(this, "Something wrong", Toast.LENGTH_SHORT).show()
            }) {
                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                    params.put("data", "Hello Android")
                    return params
                }
            }
        queue.add(postRequest)
    }
}
