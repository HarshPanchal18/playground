package com.example.playground.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.playground.Constants
import com.example.playground.R

class implicit_intent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit_intent)

        val usrmsg = findViewById<EditText>(R.id.usrmsg)
        val shareActbtn = findViewById<Button>(R.id.sendToOtherAct)
        val sharebtn = findViewById<Button>(R.id.sendToOtherApp)

        shareActbtn.setOnClickListener {
            val message = findViewById<EditText>(R.id.usrmsg).text.toString()
            var intent = Intent(this, explicit_intent::class.java)

            intent.putExtra(Constants.USR_MSG_KEY, message)
            startActivity(intent)
        }

        sharebtn.setOnClickListener {
            var intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, usrmsg.text.toString())
            intent.type = "text/plain"

            startActivity(Intent.createChooser(intent, "Select Your application:"))
        }
    }
}