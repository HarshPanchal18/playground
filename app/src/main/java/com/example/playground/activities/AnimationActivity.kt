package com.example.playground.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_animation.*

class AnimationActivity : AppCompatActivity() {

    lateinit var animation:Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)

        val image: ImageView = findViewById(R.id.imgviewrotation)
        val startbtn: Button =findViewById(R.id.btntogether)
        startbtn.setOnClickListener {
            animation=AnimationUtils.loadAnimation(applicationContext, R.anim.animations)
            image.startAnimation(animation)
        }
        btnblink.setOnClickListener {
            animation = AnimationUtils.loadAnimation(applicationContext, R.anim.blink)
            image.startAnimation(animation)
        }
    }
}
