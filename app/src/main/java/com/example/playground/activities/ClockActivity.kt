package com.example.playground.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextClock
import android.widget.TextView
import com.example.playground.R

class ClockActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clock)
    }
}
