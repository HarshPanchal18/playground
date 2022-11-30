package com.example.playground.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import com.example.playground.R

class AnimationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)

        val startbtn: Button =findViewById(R.id.btntogether)
        startbtn.setOnClickListener {
            val image: ImageView = findViewById(R.id.imgviewrotation)
            val animation: Animation =
                AnimationUtils.loadAnimation(applicationContext, R.anim.animations)
            image.startAnimation(animation)
        }
    }
}
