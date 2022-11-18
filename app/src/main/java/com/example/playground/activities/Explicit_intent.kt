package com.example.playground.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.playground.utils.Constants
import com.example.playground.R

class Explicit_intent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicit_intent)

        val msg = findViewById<TextView>(R.id.txvUserMessage)
        val bundle = intent.extras

        bundle?.let { // in case of bundle is null
            msg.text = intent.getStringExtra(Constants.USR_MSG_KEY).toString()
        }
    }
}
