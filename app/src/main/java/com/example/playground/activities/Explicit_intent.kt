package com.example.playground.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.playground.utils.Constants
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_explicit_intent.*

class Explicit_intent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicit_intent)

        val bundle = intent.extras

        bundle?.let { // in case of bundle is null
            txvUserMessage.text = intent.getStringExtra(Constants.USR_MSG_KEY).toString()
        }
    }
}
