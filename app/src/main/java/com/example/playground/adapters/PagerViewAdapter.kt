package com.example.playground.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.playground.fragments.AddFragrment
import com.example.playground.fragments.HomeFragment
import com.example.playground.fragments.ProfileFragment

internal class PagerViewAdapter(fragmentManager: FragmentManager?):FragmentPagerAdapter(fragmentManager!!) {
    override fun getItem(position: Int): Fragment {
        return if(position==0) HomeFragment()
            else if(position==1) ProfileFragment()
            else AddFragrment()

            /*0-> HomeFragment()
            1-> ProfileFragment()
            2-> AddFragrment()
            else->null
        }*/
    }
    override fun getCount(): Int {
        return 3
    }
}
