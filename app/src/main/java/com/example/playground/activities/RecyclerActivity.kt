package com.example.playground.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_recycler.*

class RecyclerActivity : AppCompatActivity() {
    // ArrayList for person names
    var personNames = ArrayList(listOf(
        "Person 1", "Person 2",
        "Person 3", "Person 4",
        "Person 5", "Person 6",
        "Person 7", "Person 8" ))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        val linearLayoutManager=LinearLayoutManager(applicationContext)
        personRecyclerView.layoutManager=linearLayoutManager

        //val custAdapter=AdapterView(this,personNames)
    }
}
