package com.example.playground.activities

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.playground.R
import com.example.playground.adapters.SlideViewPagerAdapter
import kotlinx.android.synthetic.main.activity_view_pager.*

class ViewPager : AppCompatActivity() {

    private var titleList= mutableListOf<String>()
    private var descList= mutableListOf<String>()
    private var imageList= mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        // Customize the back button
        val actionBar: ActionBar? = supportActionBar

        // showing the back button on action bar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeButtonEnabled(true)
        }

        postToList()

        viewPager.adapter= SlideViewPagerAdapter(titleList,descList,imageList)
        viewPager.orientation= ViewPager2.ORIENTATION_HORIZONTAL

        indicator.setViewPager(viewPager)
    }

    private fun addToList(title:String,desc:String,img:Int){
        titleList.add(title)
        descList.add(desc)
        imageList.add(img)
    }

    private fun postToList(){
        for (i in 1..5){
            addToList("Title $i","Description $i",R.mipmap.ic_launcher_round)
        }
    }
}
