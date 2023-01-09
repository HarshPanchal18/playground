package com.example.playground.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.playground.R
import kotlinx.android.synthetic.main.fragment_blue.*

class BlueFragment() : Fragment() {

    private lateinit var response:TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        // Inflate the layout for this fragment
        val v=inflater.inflate(R.layout.fragment_blue,container,false)
        response=v.findViewById(R.id.bluefrag)
        return v
    }

    fun youveGotMail(txt:String){
        response.text=txt
    }
}
