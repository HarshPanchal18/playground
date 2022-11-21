package com.example.playground.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import com.example.playground.R

class ImgActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_img)

        supportActionBar!!.hide()
        val imgbtn: ImageButton = findViewById(R.id.imagebtn)
        val imgview: ImageView = findViewById(R.id.imageview)

        imgview.visibility = View.INVISIBLE
        var counter: Int = 0
        imgbtn.setOnClickListener {
            if (counter.mod(2) == 0)
                imgview.visibility = View.VISIBLE
            else
                imgview.visibility = View.INVISIBLE
            counter++
        }
        counter = 0
    }
}
