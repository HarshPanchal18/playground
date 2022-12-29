package com.example.playground.activities.sqlitedb

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class db_helper(context: Context?) : SQLiteOpenHelper(context, "studManager", null, 1) {

    val dbname:String="Student.db"
    val tblname:String="stud_tbl"
    val col1:String="ID"
    val col2:String="NAME"
    val col3:String="SURNAME"
    val col4:String="MARKS"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table $tblname (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SURNAME TEXT,MARKS INTEGER)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVer: Int, newVer: Int) {
        db?.execSQL("drop table if exists $tblname")
    }

    fun insertData(name:String,Sname:String,marks:String): Boolean {
        val contentVal = ContentValues()
        val db: SQLiteDatabase = this.writableDatabase
        contentVal.put(col2,name)
        contentVal.put(col3,Sname)
        contentVal.put(col4,marks)

        val res:Long=db.insert(tblname,null,contentVal)
        db.close()
        return res.toInt() != -1
    }

    fun getAllData(): Cursor {
        val db: SQLiteDatabase = this.writableDatabase
        return db.rawQuery("Select * from $tblname", null)
    }

    fun updateData(id:String,name:String,sname:String,marks:String): Boolean {
        val contentVal=ContentValues()
        val db: SQLiteDatabase = this.writableDatabase
        contentVal.put(col2,name)
        contentVal.put(col3,sname)
        contentVal.put(col4,marks)
        val res:Int=db.update(tblname,contentVal,"ID=?", arrayOf(id))
        return res>0
    }

    fun deleteData(id: String): Int {
        val db: SQLiteDatabase = this.writableDatabase
        return db.delete(tblname, "ID=?", arrayOf(id))
    }

    fun deleteAll() {
        val db: SQLiteDatabase = this.writableDatabase
        db.execSQL("delete from $tblname")
    }

    fun readAllData(): Cursor {
        val query = "select * from $tblname;"
        val db: SQLiteDatabase = this.writableDatabase
        return db.rawQuery(query, null)
    }
}
