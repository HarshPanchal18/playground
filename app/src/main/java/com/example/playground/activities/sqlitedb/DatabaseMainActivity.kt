package com.example.playground.activities.sqlitedb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_database_main.*

class DatabaseMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database_main)

        val adapter=sqliteViewPager(supportFragmentManager)
        adapter.addFragmentWindow(Insert(),"INSERT")
        //adapter.addFragmentWindow(Insert(),"READ")

        db_viewpager.adapter=adapter
        db_tabs.setupWithViewPager(db_viewpager)
    }
}
