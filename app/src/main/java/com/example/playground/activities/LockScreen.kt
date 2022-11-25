package com.example.playground.activities

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.Gravity
import android.widget.Toast
import com.example.playground.MainActivity
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_lock_screen.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class LockScreen : AppCompatActivity() {
    private val random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lock_screen)

        val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator

        val r: Int = rand(0, 10)
        gobtn.setOnClickListener {
            if (passwdbox.text.toString() == r.toString()) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                passwdbox.backgroundTintList=ColorStateList.valueOf(Color.GREEN)
                finish()
            } else {
                vibrator.vibrate(
                    VibrationEffect
                        .createOneShot(200,VibrationEffect.DEFAULT_AMPLITUDE)
                )
                passwdbox.setText("")
                passwdbox.backgroundTintList=ColorStateList.valueOf(Color.RED)
            }
        }

        hintbtn.setOnClickListener {
            val toast = Toast.makeText(this, "Try :--> $r", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }
    }

    private fun rand(from: Int, to: Int): Int {
        return random.nextInt(to - from) + from
    }
}
