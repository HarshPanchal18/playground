package com.example.playground.activities.sqlitedb

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_database_main.*

class DatabaseMainActivity : AppCompatActivity() {
    val id=ArrayList<String>()
    val name=ArrayList<String>()
    val sname=ArrayList<String>()
    val marks=ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        insbtn.setOnClickListener { startActivity(Intent(this,Insert::class.java)) }
        delbtn.setOnClickListener { startActivity(Intent(this,Delete::class.java)) }
        updbtn.setOnClickListener { startActivity(Intent(this,Update::class.java)) }

        val dataAdapter= SQLiteCompoundAdapter(this,this,id,name,sname,marks)
        readlist.adapter=dataAdapter
        readlist.layoutManager=LinearLayoutManager(this)

        refreshsqlite.setOnRefreshListener {
            readlist.adapter=dataAdapter
            readlist.layoutManager=LinearLayoutManager(this)
            storeInArr()
            refreshsqlite.isRefreshing=false // turn down refreshing after finishing the process
        }
    }

    fun storeInArr() {
        val mydb_helper=db_helper(this)
        val cursor: Cursor =mydb_helper.readAllData()

        if(cursor.count==0)
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show()
        else {
            while(cursor.moveToNext()){
                id.add(cursor.getString(0))
                name.add(cursor.getString(1))
                sname.add(cursor.getString(2))
                marks.add(cursor.getString(3))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater= menuInflater
        menuInflater.inflate(R.menu.sqlite_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.deleteall)
            confirmDialog()
        return super.onOptionsItemSelected(item)
    }

    private fun confirmDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Delete All?")
        builder.setMessage("Are you sure you want to delete all Data?")
        builder.setPositiveButton("Yes"
        ) { _, _ ->
            val myDB = db_helper(this)
            myDB.deleteAll()
            //Refresh Activity
            val intent = Intent(this, DatabaseMainActivity::class.java)
            startActivity(intent)
            finish()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.create().show()
    }
}
