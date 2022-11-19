package com.example.playground.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        val r: Int = rand(0, 10)
        gobtn.setOnClickListener {
            if (passwdbox.text.toString() == r.toString()) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val toast = Toast.makeText(this, "Try Again :)", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
            }
        }
    }

    private fun rand(from: Int, to: Int): Int {
        return random.nextInt(to - from) + from
    }
}
