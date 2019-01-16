package com.example.kotlin

import android.app.AlertDialog
import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var pgdialog: ProgressDialog? = null
    val toasttest = {msg:String -> Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnMenuProgressDialog.setOnClickListener { ProgressDialog() }
        btnMenuSwipreRefresh.setOnClickListener { SwipreRefreshActive() }
        btnMenuAlertDialog.setOnClickListener { AlertDialogFunc() }

        // this is event when component swipe refresh on swipe down but now can't to use
        swipeRefreshLayout.setOnRefreshListener { SwipreRefreshActive() }
    }

    private fun ProgressDialog(){
        pgdialog = ProgressDialog(this)
        pgdialog?.setMessage("Please wait")
        pgdialog?.setTitle("Loading")
        pgdialog?.setCancelable(false)
        pgdialog?.isIndeterminate=true
        pgdialog?.show()
        Run.after(1000) {pgdialog?.dismiss()}
    }

    private fun SwipreRefreshActive(){
        swipeRefreshLayout.isRefreshing = true
        toasttest("hallo swipe")
        Run.after(1000) {swipeRefreshLayout.isRefreshing = false}
    }

    private fun AlertDialogFunc(){
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Alert ?")
        builder.setCancelable(false)
        builder.setPositiveButton("Yes") { dialog, which ->
            dialog.dismiss()
            toasttest("hallo alert")
        }
        builder.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}

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
