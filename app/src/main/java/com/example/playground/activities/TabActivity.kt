package com.example.playground.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.playground.R
import com.example.playground.activities.tabs.tab1
import com.example.playground.activities.tabs.tab2
import com.example.playground.activities.tabs.tab3
import com.example.playground.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout

class TabActivity : AppCompatActivity() {

    private lateinit var pager: ViewPager // creating object of ViewPager
    private lateinit var tab: TabLayout // creating object of TabLayout
    private lateinit var bar: Toolbar // creating object of ToolBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // set the references of the declared objects above
        pager = findViewById(R.id.viewPager)
        tab = findViewById(R.id.tabs)
        bar = findViewById(R.id.toolbar)

        // To make our toolbar show the application
        // we need to give it to the ActionBar
        //setSupportActionBar(bar)

        // Initializing the com.example.playground.adapters.ViewPagerAdapter
        val adapter = ViewPagerAdapter(supportFragmentManager)

        // add fragment to the list
        adapter.addFragment(tab1(), "Tab #1")
        adapter.addFragment(tab2(), "Tab #2")
        adapter.addFragment(tab3(), "Tab #3")

        pager.adapter = adapter // Adding the Adapter to the ViewPager

        tab.setupWithViewPager(pager) // bind the viewPager with the TabLayout.
    }
}
