package com.example.playground.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.playground.fragments.AddFragment
import com.example.playground.fragments.HomeFragment
import com.example.playground.fragments.ProfileFragment

internal class PagerViewAdapter(fragmentManager: FragmentManager?):FragmentPagerAdapter(fragmentManager!!) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> ProfileFragment()
            else -> AddFragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }
}
