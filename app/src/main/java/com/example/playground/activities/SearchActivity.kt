package com.example.playground.activities

// Solving for SearchView

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import com.example.playground.R

class SearchActivity : AppCompatActivity() {

    lateinit var adapter: ArrayAdapter<String>
    val cities=listOf("Ahmedabad", "Amreli", "Banaskantha", "Bharuch", "Bhavnagar",
                    "Dang","Dahod","Gandhinagar", "Jamnagar", "Junagadh", "Kheda", "Kachchh", "Mehsana","Navsari",
                    "Panchmahal","Patan", "Rajkot", "Sabarkantha", "Surat", "Surendranagar", "Tapi","Vadodara","Valsad")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val toolbar:Toolbar=findViewById(R.id.toolbar)
        toolbar.title="Search City of Gujarat"
        setSupportActionBar(toolbar)

        val lists: ListView =findViewById(R.id.citylist)
        val searchview: SearchView = findViewById(R.id.idsearch)

        adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,cities)
        lists.adapter = adapter

        searchview.setOnQueryTextListener(object: SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(query: String?): Boolean {
                adapter.filter.filter(query)
                return false
            }
        })
    }
}
