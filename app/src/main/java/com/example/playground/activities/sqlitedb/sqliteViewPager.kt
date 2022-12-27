package com.example.playground.activities.sqlitedb

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class sqliteViewPager(supportFragment: FragmentManager) : FragmentStatePagerAdapter(supportFragment) {

    // declare arrayList to contain fragments and its title
    private val mFragmentList = ArrayList<Fragment>()
    private val mFragmentTitleList = ArrayList<String>()

    override fun getCount(): Int = mFragmentList.size

    override fun getItem(position: Int): Fragment = mFragmentList[position]

    fun addFragmentWindow(frag:Fragment,title:String){
        mFragmentList.add(frag)
        mFragmentTitleList.add(title)
    }
}
