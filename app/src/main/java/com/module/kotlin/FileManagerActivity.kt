package com.module.kotlin

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import com.module.kotlin.R
import kotlinx.android.synthetic.main.activity_file_manager.*
import java.io.*

class FileManagerActivity : AppCompatActivity() {
    private val filename = "SampleFile.txt"
    private var myExternalFile: File? = null
    private var myData = ""

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_manager)
        btnSaveFileManager.setOnClickListener {
            try {
                val fos = FileOutputStream(myExternalFile)
                fos.write(edtFileManager.text.toString().toByteArray())
                fos.close()
                edtFileManager.setText("")
                lblFileManager.text = "SampleFile.txt saved to External Storage..."
            } catch (e: IOException) {
                e.printStackTrace()
                toasttest(e.toString(), this)
            }

        }

        btnReadFileManager.setOnClickListener {
            try {
                val fis = FileInputStream(myExternalFile)
                val `in` = DataInputStream(fis)
                val br = BufferedReader(InputStreamReader(`in`))
                var strLine = ""
                for (line in br.lines()) {
                    strLine = line
                    myData += strLine
                }
                `in`.close()
                edtFileManager.setText(myData)
                lblFileManager.text = "SampleFile.txt data retrieved from Internal Storage..."
            } catch (e: IOException) {
                e.printStackTrace()
                toasttest(e.toString(), this)
            }

        }
        ChecPermission()
    }

    private fun ChecPermission(){
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            btnSaveFileManager.isEnabled = false
        } else {
            val root = Environment.getExternalStorageDirectory().toString()
            val myDir = File("$root/ModuleKotlin")
            myDir.mkdirs()
            myExternalFile = File(myDir, filename)//File(getExternalFilesDir(filepath), filename)
        }
    }

    private fun isExternalStorageReadOnly(): Boolean {
        val extStorageState = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)
    }

    private fun isExternalStorageAvailable(): Boolean {
        val extStorageState = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED.equals(extStorageState)
    }
}
