package com.example.playground.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_img.*

class ImgActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_img)

        supportActionBar!!.hide()

        imageview.visibility = View.INVISIBLE
        var counter: Int = 0
        imagebtn.setOnClickListener {
            if (counter.mod(2) == 0)
                imageview.visibility = View.VISIBLE
            else
                imageview.visibility = View.INVISIBLE
            counter++
        }
        counter = 0
    }
}
