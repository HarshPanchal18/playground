package com.example.playground.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.playground.R

class PullRefresh : AppCompatActivity() {
    lateinit var swipeLayout:SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_refresh)

        val txt: TextView =findViewById(R.id.txtrefresh)
        swipeLayout=findViewById(R.id.swipelayout)
        swipeLayout.setOnRefreshListener(object: SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                txt.text="REFRESHED !!"
                swipeLayout.isRefreshing=false // turn down refreshing after finishing the process
            }
        })
    }
}
