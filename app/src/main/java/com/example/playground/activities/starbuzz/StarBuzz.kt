package com.example.playground.activities.starbuzz

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.playground.R

class StarBuzz : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_star_buzz)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val itemClickListener: AdapterView.OnItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, pos, l ->
                /*where,
                 adapterView is listview
                 view is what clicked (Text,Image)
                 pos is position in list
                 l is resourceID of underlying data
                 */
            if (pos == 0)
                startActivity(Intent(this, DrinkCategory::class.java))
        }
        val listView:ListView=findViewById(R.id.list_option)
        listView.onItemClickListener = itemClickListener
    }
}
