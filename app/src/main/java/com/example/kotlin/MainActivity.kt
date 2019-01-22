package com.example.kotlin

import android.app.*
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import android.app.NotificationManager
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.app.PendingIntent
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private var pgdialog: ProgressDialog? = null
    private val idresult_secondactivity = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnMenuProgressDialog.setOnClickListener { ProgressDialog() }
        btnMenuAlertDialog.setOnClickListener { AlertDialogFunc() }
        btnMenuIntent.setOnClickListener { IntentFunc() }
        btnMenuPicasso.setOnClickListener { startActivity(Intent(this,PicassoActivity::class.java)) }
        btnMenuListview.setOnClickListener { startActivity(Intent(this,ListviewActivity::class.java)) }
        btnMenuDatabase.setOnClickListener { startActivity(Intent(this,DatabaseActivity::class.java)) }
        btnMenuImageCropper.setOnClickListener { startActivity(Intent(this,ImageCropperActivity::class.java)) }
        btnMenuRecylerviewr.setOnClickListener { startActivity(Intent(this,RecylerViewActivity::class.java)) }
        btnMenuVolley.setOnClickListener { startActivity(Intent(this,VolleyActivity::class.java)) }
        btnMenuSharedPreference.setOnClickListener { startActivity(Intent(this,SharedPreferenceActivity::class.java)) }
        btnMenuAnko.setOnClickListener { startActivity(Intent(this,AnkoActivity::class.java)) }
        btnMenuTab.setOnClickListener { startActivity(Intent(this,TabActivity::class.java)) }
        btnMenuNotification.setOnClickListener { NotificationFunc() }
        btnMenuFileManager.setOnClickListener { startActivity(Intent(this,FileManagerActivity::class.java)) }
        btnMenuAlertDialogList.setOnClickListener { AlertDialogListFunc() }

        // this is event when component swipe refresh on swipe down but now can't to use
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
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

    private fun NotificationFunc(){
        // reference "https://developer.android.com/training/notify-user/build-notification"
        val CHANNEL_ID = "NewNews"
        var mBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_icon_background)
            .setContentTitle("My notification")
            .setContentText("Much longer text that cannot fit one line...")
            .setStyle(NotificationCompat.BigTextStyle().bigText("Much longer text that cannot fit one line..."))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)


        // Creates an explicit intent for an Activity in your app
        val resultIntent = Intent(this, MainActivity::class.java)

        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        val stackBuilder = TaskStackBuilder.create(this)
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity::class.java)
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent)
        val resultPendingIntent = stackBuilder.getPendingIntent(
            0,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        mBuilder.setContentIntent(resultPendingIntent)

        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "\"com.example.kotlin\""
            val descriptionText = "Hallo this is message from menu notification"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            val notificationId = 1234
            notify(notificationId, mBuilder.build())
        }
    }

    private fun AlertDialogListFunc(){
        val items = arrayOf("Red", "Orange", "Yellow", "Blue")
        val builder = AlertDialog.Builder(this)
        with(builder)
        {
            setTitle("List of Items")
            setItems(items) { dialog, which ->
                Toast.makeText(applicationContext, items[which] + " is clicked", Toast.LENGTH_SHORT).show()
            }
            show()
        }
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


