package com.example.playground.activities.sqlitedb

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_insert.*

class Insert : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        val myDb = db_helper(this)

        insbtn.setOnClickListener {
            val res:Boolean = myDb.insertData(dbname.text.toString(),dbsname.text.toString(),dbmarks.text.toString())
            if(res) Toast.makeText(this,"Inserted",Toast.LENGTH_SHORT).show()
            else Toast.makeText(this,"Not Inserted",Toast.LENGTH_SHORT).show()
        }
    }
}
