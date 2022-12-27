package com.example.playground.activities.sqlitedb

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_insert.*

class Insert : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        //super.onCreateView(savedInstanceState)
        //setContentView(R.layout.activity_insert)

        val myDb = db_helper(context)

        insbtn.setOnClickListener {
            val res:Boolean = myDb.insertData(dbname.text.toString(),dbsname.text.toString(),dbmarks.text.toString())
            if(res) Toast.makeText(context,"Inserted",Toast.LENGTH_SHORT).show()
            else Toast.makeText(context,"Not Inserted",Toast.LENGTH_SHORT).show()
        }
        return inflater.inflate(R.layout.activity_insert,container,false)!!
    }
}
