package com.module.kotlin

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

val DATABASE_NAME ="databaselocal"
val TABLE_NAME="dbmdata"

class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_NAME (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Nama TEXT,"+
                "Nomer INTEGER)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?,oldVersion: Int,newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun insertData(sdata : DataSchemDBCache): Boolean {
        var bol:Boolean=false
        val db = this.writableDatabase
        var dtemp = ContentValues()
        dtemp.put("Nama",sdata.Nama)
        dtemp.put("Nomer",sdata.Nomer)

        var result = db.insert(TABLE_NAME,null,dtemp)
        bol = result != (-1).toLong()
        return bol
    }

    fun readData(swhere:String) : MutableList<DataSchemDBCache>{
        var list : MutableList<DataSchemDBCache> = ArrayList()
        val db = this.readableDatabase
        val query = "select * from $TABLE_NAME swhere "// example : where Name="Ganda"
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()){
            do {
                var user = DataSchemDBCache()
                user.ID = result.getString(result.getColumnIndex("ID")).toInt()
                user.Nama = result.getString(result.getColumnIndex("Nama"))
                user.Nomer = result.getInt(result.getColumnIndex("Nomer").toInt())
                list.add(user)
            }while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }

    fun updateData(swhere:String,fieldData:String,valueData:String) {
        val db = this.writableDatabase
        val query = "Select * from $TABLE_NAME where $swhere"
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()){
            do {
                var cv = ContentValues()
                cv.put(fieldData,valueData)
                db.update(TABLE_NAME,cv,swhere,null)
            }while (result.moveToNext())
        }
        result.close()
        db.close()
    }

    fun deleteData(selection: String, selectionArgs: Array<String>){
        val db = this.writableDatabase
        db.delete(TABLE_NAME,selection,selectionArgs)
        db.close()
    }

    fun selectDataCursor(ssql:String):Cursor{
        val db = this.readableDatabase
        return db.rawQuery(ssql,null)
    }

//    fun selectData(swhere:String) : MutableList<DataSchemDBCache>{
//        var list : MutableList<DataSchemDBCache> = ArrayList()
//        val db = this.readableDatabase
//        val query = "Select * from $TABLE_NAME$swhere"
//        val result = db.rawQuery(query,null)
//        if(result.moveToFirst()){
//            do {
//                var user = DataSchemDBCache()
//                user.ID = result.getString(result.getColumnIndex(COL_ID)).toInt()
//                user.DocNumber = result.getString(result.getColumnIndex("DocNumber"))
//                user.IDData = result.getInt(result.getColumnIndex("IDData").toInt())
//                user.UrlFoto = result.getString(result.getColumnIndex("UrlFoto"))
//                user.UrlKTP = result.getString(result.getColumnIndex("UrlKTP"))
//                user.UrlSignature = result.getString(result.getColumnIndex("UrlSignature"))
//                user.UrlFingerPrint = result.getString(result.getColumnIndex("UrlFingerPrint"))
//                list.add(user)
//            }while (result.moveToNext())
//        }
//        result.close()
//        db.close()
//        return list
//    }

//    fun selectDataCustom(swhere:String): Cursor {
//        var list : MutableList<DataSchemDBCache> = ArrayList()
//        val db = this.readableDatabase
//        val query = "Select * from " + TABLE_NAME + swhere
//        val result = db.rawQuery(query,null)
//        return result
//    }
}

class DataSchemDBCache{
    var ID : Int = 0
    var Nama : String = ""
    var Nomer :Int = 0
    constructor(Nama:String,Nomer:Int){
        this.Nama = Nama
        this.Nomer = Nomer
    }
    constructor(){}
}
