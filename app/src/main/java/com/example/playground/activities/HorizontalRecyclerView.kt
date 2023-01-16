package com.example.playground.activities

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.playground.R
import com.example.playground.adapters.MyHorizontalAdapter
import com.example.playground.adapters.Version
import kotlinx.android.synthetic.main.activity_horizontal_recycler_view.*

class HorizontalRecyclerView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horizontal_recycler_view)

        if(isOnline(this))
            displayList()
        else
            Toast.makeText(this,R.string.no_internet,Toast.LENGTH_SHORT).show()
    }

    private fun displayList() {
        val version=ArrayList<Version>()
        version.addAll(Version.getList())
        horizontalRecyclerView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        horizontalRecyclerView.adapter=MyHorizontalAdapter(version)
    }

    private fun isOnline(context: Context): Boolean{
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnectedOrConnecting
    }
}
