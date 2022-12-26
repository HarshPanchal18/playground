package com.example.playground.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_dynamic_list.*

class DynamicList : AppCompatActivity() {
    var list: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic_list)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,list)
        btnAddlist.setOnClickListener{
            list.add(nameList.text.toString())
            list.removeAll(listOf("",null)) // remove empty entities
            nameList.text=null
            adapter.notifyDataSetChanged()
            listView.adapter=adapter
        }
    }
}
