package com.example.playground.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.playground.R
import com.example.playground.adapters.CustRecycleAdapter
import kotlinx.android.synthetic.main.activity_recycler.*

class RecyclerActivity : AppCompatActivity() {

    private val personNames=fetchData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        val linearLayoutManager=LinearLayoutManager(this)
        personRecyclerView.layoutManager=linearLayoutManager

        val adapter=CustRecycleAdapter(this,personNames)
        personRecyclerView.adapter=adapter
    }

    private fun fetchData():ArrayList<String>{
        val list=ArrayList<String>()
        for( i in 0 until 20)
            list.add("Item $i")
        return list
    }
}
