package com.example.kotlin

import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var pgdialog: ProgressDialog? = null
    val idresult_secondactivity = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnMenuProgressDialog.setOnClickListener { ProgressDialog() }
        btnMenuSwipreRefresh.setOnClickListener { SwipreRefreshActive() }
        btnMenuAlertDialog.setOnClickListener { AlertDialogFunc() }
        btnMenuIntent.setOnClickListener { IntentFunc() }
        btnMenuPicasso.setOnClickListener { startActivity(Intent(this,PicassoActivity::class.java)) }
        btnMenuListview.setOnClickListener { startActivity(Intent(this,ListviewActivity::class.java)) }
        btnMenuDatabase.setOnClickListener { startActivity(Intent(this,DatabaseActivity::class.java)) }
        btnMenuImageCropper.setOnClickListener { startActivity(Intent(this,ImageCropperActivity::class.java)) }
        btnMenuRecylerviewr.setOnClickListener { startActivity(Intent(this,RecylerViewActivity::class.java)) }
        btnMenuVolley.setOnClickListener { startActivity(Intent(this,VolleyActivity::class.java)) }
        btnMenuSharedPreference.setOnClickListener { startActivity(Intent(this,SharedPreferenceActivity::class.java)) }

        // this is event when component swipe refresh on swipe down but now can't to use
        swipeRefreshLayout.setOnRefreshListener { SwipreRefreshActive() }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menucustom, menu) //your file name
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.btnMenuAction1 -> {
                toasttest("Action 1",this)
                true
            }

            R.id.btnMenuAction2 ->{
                toasttest("Action 2",this)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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
        toasttest("hallo swipe",this)
        Run.after(1000) {swipeRefreshLayout.isRefreshing = false}
    }

    private fun AlertDialogFunc(){
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Alert ?")
        builder.setCancelable(false)
        builder.setPositiveButton("Yes") { dialog, which ->
            dialog.dismiss()
            toasttest("hallo alert",this)
        }
        builder.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun IntentFunc(){
        //this is function for open to secondactivity with sending parameters
        val intent = Intent(this,SecondActivity::class.java)
        intent.putExtra("dataInteger",1)
        intent.putExtra("dataString","ganda")
        startActivityForResult(intent,idresult_secondactivity)

        //this is function for lunch to secondactivity without sending parameters
        /*val intent = Intent(this,SecondActivity::class.java)
        startActivity(intent)*/

        //this is function for lunch to secondactivity without sending parameters easily
        /*startActivity(Intent(this,SecondActivity::class.java))*/
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==idresult_secondactivity){
            if(resultCode==Activity.RESULT_OK) {
                if (data != null) {
                    val data_id = data.getIntExtra("id", 0).toString()
                    val data_nama = data.getStringExtra("nama")
                    toasttest("ID : $data_id ,Nama : $data_nama", this)
                }
            }
        }else{
            toasttest("No result",this)
        }
    }
}


