package com.example.playground.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.playground.R
import kotlinx.android.synthetic.main.fragment_green.*

class GreenFragment() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        // Inflate the layout for this fragment
        val v=inflater.inflate(R.layout.fragment_green, container, false)

        val editTexts:EditText=v.findViewById(R.id.editfrag)

        editTexts.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val s=editTexts.text.toString()
                val my_interface:MyInterface = activity as MyInterface
                my_interface.myResponse(s)
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
        return v
    }
}
