package com.example.playground.activities.sqlitedb

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.playground.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_database_main.*

class DatabaseMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database_main)

        insbtn.setOnClickListener { startActivity(Intent(this,Insert::class.java)) }
        delbtn.setOnClickListener { startActivity(Intent(this,Delete::class.java)) }
        updbtn.setOnClickListener { startActivity(Intent(this,Update::class.java)) }

        val mydb_helper=db_helper(this)
        val res: Cursor =mydb_helper.getAllData()
        val stringBuffer=StringBuffer()
        if(res.count>0){
            while(res.moveToNext()){
                stringBuffer.append("ID: ${res.getString(0)}\n")
                stringBuffer.append("Name: ${res.getString(1)}\n")
                stringBuffer.append("Surname: ${res.getString(2)}\n")
                stringBuffer.append("Marks: ${res.getString(3)}\n\n")
            }
            //readlist.text=stringBuffer.toString()
            Toast.makeText(this,"Retrieved",Toast.LENGTH_SHORT).show()
        } else Toast.makeText(this,"Retrieved",Toast.LENGTH_SHORT).show()

        readlist.layoutManager=LinearLayoutManager(this)
    }
}
