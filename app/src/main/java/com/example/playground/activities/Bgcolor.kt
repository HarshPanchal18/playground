package com.example.playground.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_bgcolor.*

class Bgcolor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bgcolor)

        val layout:ConstraintLayout=findViewById(R.id.constlayout)

        blackbtn.setOnClickListener { layout.setBackgroundColor(Color.BLACK) }

        yellowbtn.setOnClickListener { layout.setBackgroundColor(Color.YELLOW) }

        greenbtn.setOnClickListener { layout.setBackgroundColor(Color.GREEN) }
    }
}
