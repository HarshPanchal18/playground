package com.example.playground.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.playground.R
import android.widget.ListView
import android.widget.Toast

class ListViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        val clist: ListView = findViewById(R.id.countrylist)
        val characters= arrayOf("Flash","Arrow","Kenya","Kyrgyzstan","Kuwait" ,
                                "Kazakhstan" ,"Kiribati","South Korea","Pakistan",
                                "Nepal","Syria","Portugal","Russia","Moscow","Hong Kong")
        val adapter: ArrayAdapter<*>
        adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,characters)
        clist.adapter=adapter

        clist.setOnItemClickListener(object: AdapterView.OnItemClickListener{
            override fun onItemClick(adp: AdapterView<*>?, v: View?, position: Int, l: Long) {
                val item=clist.getItemAtPosition(position) as String
                Toast.makeText(applicationContext,"Position: $position\tValue: $item",Toast.LENGTH_SHORT).show()
            }
        })
    }
}
