package com.example.playground.activities.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_tab1.*

class tab1 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    )=inflater.inflate(R.layout.activity_tab1,container,false)!!
}
