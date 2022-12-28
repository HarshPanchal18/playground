package com.example.playground.activities.sqlitedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_update.*

class Update : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val mydb_helper=db_helper(this)
        updatebtn.setOnClickListener {
            val res:Boolean=mydb_helper.updateData(
                dbid.text.toString(),
                dbname.text.toString(),
                dbsname.text.toString(),
                dbmarks.text.toString()
            )
            if(res) Toast.makeText(this,"Update",Toast.LENGTH_SHORT).show()
            else Toast.makeText(this,"No Rows Affected",Toast.LENGTH_SHORT).show()
        }
    }
}
