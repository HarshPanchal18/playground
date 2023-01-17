package com.example.playground.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_animation.*

class AnimationActivity : AppCompatActivity() {

    lateinit var animation:Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)

        btntogether.setOnClickListener {
            animation=AnimationUtils.loadAnimation(applicationContext, R.anim.animations)
            imgviewrotation.startAnimation(animation)
        }
        btnblink.setOnClickListener {
            animation = AnimationUtils.loadAnimation(applicationContext, R.anim.blink)
            imgviewrotation.startAnimation(animation)
        }
    }
}
