package com.example.playground.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.playground.R

class Bgcolor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bgcolor)

        val blk: Button =findViewById(R.id.blackbtn)
        val ylo: Button =findViewById(R.id.yellowbtn)
        val grn: Button =findViewById(R.id.greenbtn)
        val layout:ConstraintLayout=findViewById(R.id.constlayout)

        blk.setOnClickListener {
            layout.setBackgroundColor(Color.BLACK)
        }

        ylo.setOnClickListener {
            layout.setBackgroundColor(Color.YELLOW)
        }

        grn.setOnClickListener {
            layout.setBackgroundColor(Color.GREEN)
        }
    }
}