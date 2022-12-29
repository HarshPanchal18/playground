package com.example.playground.activities.sqlitedb

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_insert.*

class Insert : AppCompatActivity() {
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val myDb = db_helper(applicationContext)

        insrtbtn.setOnClickListener {
            val res:Boolean = myDb.insertData(dbname.text.toString(),dbsname.text.toString(),dbmarks.text.toString())
            if(res) Toast.makeText(applicationContext,"Inserted",Toast.LENGTH_SHORT).show()
            else Toast.makeText(applicationContext,"Not Inserted",Toast.LENGTH_SHORT).show()
        }
    }
}
