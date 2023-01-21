package com.example.playground.activities.flexiblefragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_main_flexible.*

class MainFlexible : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_flexible)

        val mFragmentManager=supportFragmentManager
        val mHomeFragment=HomeFragment()
        val fragment=mFragmentManager.findFragmentByTag(HomeFragment::class.java.simpleName)

        if(fragment !is HomeFragment){
            mFragmentManager.commit {
                add(frame_container.id,mHomeFragment,HomeFragment::class.java.simpleName)
            }
        }
    }
}
