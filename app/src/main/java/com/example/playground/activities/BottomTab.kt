package com.example.playground.activities

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.playground.R
import com.example.playground.adapters.PagerViewAdapter

class BottomTab : AppCompatActivity() {

    // https://github.com/Spikeysanju/Custom-TabBar-Layout-Android/
    // Bottom tab
    private lateinit var homebtn: ImageButton
    private lateinit var addbtn: ImageButton
    private lateinit var profilebtn: ImageButton

    private lateinit var mViewPager: ViewPager
    private lateinit var mPagerViewAdapter: PagerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_tab)

        mViewPager = findViewById(R.id.mViewPager)
        homebtn = findViewById(R.id.homeBtn)
        addbtn = findViewById(R.id.addBtn)
        profilebtn = findViewById(R.id.profileBtn)

        homebtn.setOnClickListener{
            mViewPager.currentItem=0
        }

        profilebtn.setOnClickListener{
            mViewPager.currentItem=1
        }

        addbtn.setOnClickListener{
            mViewPager.currentItem=2
        }

        mPagerViewAdapter=PagerViewAdapter(supportFragmentManager)
        mViewPager.adapter=mPagerViewAdapter
        mViewPager.offscreenPageLimit=3

        mViewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) { }

            override fun onPageSelected(position: Int) {
                changeTabs(position)
            }
            override fun onPageScrollStateChanged(state: Int) {}
        })

        mViewPager.currentItem=0
        homebtn.setImageResource(R.drawable.ic_home_black)
    }

    private fun changeTabs(position:Int){
        when (position) {
            0 -> {
                homebtn.setImageResource(R.drawable.ic_home_current)
                profilebtn.setImageResource(R.drawable.ic_profile_black)
                addbtn.setImageResource(R.drawable.ic_add_black)
            }
            1 -> {
                homebtn.setImageResource(R.drawable.ic_home_black)
                profilebtn.setImageResource(R.drawable.ic_profile_current)
                addbtn.setImageResource(R.drawable.ic_add_black)
            }
            2 -> {
                homebtn.setImageResource(R.drawable.ic_home_black)
                profilebtn.setImageResource(R.drawable.ic_profile_black)
                addbtn.setImageResource(R.drawable.ic_add_current)
            }
        }
    }
}
