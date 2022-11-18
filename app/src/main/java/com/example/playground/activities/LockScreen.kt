package com.example.playground.activities

import android.content.Intent
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.playground.MainActivity
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_lock_screen.*
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text
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
            } else
                Toast.makeText(this, "Try Again :(", Toast.LENGTH_SHORT).show()
        }
    }

    fun rand(from: Int, to: Int): Int {
        return random.nextInt(to - from) + from
    }
}
