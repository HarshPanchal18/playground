package com.example.playground.activities

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.playground.R

class ScreenRotation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rotation)

        val Land: Button =findViewById(R.id.landscapebtn)
        val Port: Button =findViewById(R.id.portbtn)

        Land!!.setOnClickListener {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        Port!!.setOnClickListener {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

    }
}