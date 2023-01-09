package com.example.playground.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.playground.R
import com.example.playground.fragments.BlueFragment
import com.example.playground.fragments.GreenFragment
import com.example.playground.fragments.MyInterface


class InterFragment : AppCompatActivity(),MyInterface{//GreenFragment.OnGreenFragmentListener {

    private val BLUE_TAG = "blue"
    private val GREEN_TAG = "green"
    var mBlueFragment: BlueFragment? = null
    var mGreenFragment: GreenFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inter_fragment)

        val fragManager: FragmentManager = supportFragmentManager

        mBlueFragment=fragManager.findFragmentByTag(BLUE_TAG) as BlueFragment?
        if(mBlueFragment==null){
            mBlueFragment= BlueFragment()
            fragManager.beginTransaction()
                .add(R.id.blueFragContainer, mBlueFragment!!,BLUE_TAG).commit()
        }

        mGreenFragment=fragManager.findFragmentByTag(GREEN_TAG) as GreenFragment?
        if(mGreenFragment==null){
            mGreenFragment= GreenFragment()
            fragManager.beginTransaction()
                .add(R.id.greenFragContainer, mGreenFragment!!,GREEN_TAG).commit()
        }
    }

    /*override fun messageFromGreenFragment(text: String) {
        mBlueFragment!!.youveGotMail(text)
    }*/

    override fun myResponse(text: String) {
        val manager=supportFragmentManager
        val blueFragment=manager.findFragmentByTag(BLUE_TAG) as BlueFragment
        blueFragment.youveGotMail(text)
    }
}
