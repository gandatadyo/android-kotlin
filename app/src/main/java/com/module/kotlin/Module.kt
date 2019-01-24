package com.module.kotlin

import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import android.os.Handler
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream
import java.util.*


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

class dataschemarecycler{
    var nama =""
    var telp =""
    constructor(nama:String,telp:String){
        this.nama=nama
        this.telp=telp
    }
}

fun saveImageToInternalMemory(finalBitmap: Bitmap):String {
    var imagePath: String? = ""
    val root = Environment.getExternalStorageDirectory().toString()
    val myDir = File("$root/ModuleKotlin")
    myDir.mkdirs()
    val generator = Random()
    var n = 10000
    n = generator.nextInt(n)
    val OutletFname = "Image-$n.jpg"
    val file = File(myDir, OutletFname)
    if (file.exists()) file.delete()
    try {
        val out = FileOutputStream(file)
        finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
        imagePath = file.absolutePath
        out.flush()
        out.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return imagePath.toString()
}