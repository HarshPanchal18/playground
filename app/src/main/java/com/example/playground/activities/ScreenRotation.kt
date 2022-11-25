package com.example.playground.activities

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import com.example.playground.R

class ScreenRotation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rotation)

        val oswitch:Switch =findViewById(R.id.orientationswitch)

        oswitch.setOnCheckedChangeListener { _, on ->
            requestedOrientation =
                if (on) ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                else ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }
}
