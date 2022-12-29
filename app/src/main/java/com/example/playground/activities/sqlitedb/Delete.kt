package com.example.playground.activities.sqlitedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_delete.*

class Delete : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val mydb_helper=db_helper(this)
        deletebtn.setOnClickListener {
            val res=mydb_helper.deleteData(dbid.text.toString())
            Toast.makeText(this,"$res rows affected",Toast.LENGTH_SHORT).show()
        }
    }
}
