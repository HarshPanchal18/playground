package com.example.playground.activities

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.playground.R
import com.example.playground.utils.Constants
import kotlinx.android.synthetic.main.activity_implicit_intent.*

class Implicit_intent : AppCompatActivity() {

    override fun onSaveInstanceState(outState: Bundle) {
        val msgtxt=usrmsg.text.toString()
        outState.putString("savedText",msgtxt)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        val msgText=savedInstanceState.getString("savedText")
        usrmsg.setText(msgText)
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit_intent)

        val usrmsg = findViewById<EditText>(R.id.usrmsg)

        sendToOtherAct.setOnClickListener {
            val message = usrmsg.text.toString()
            val intent = Intent(this, Explicit_intent::class.java)
            intent.putExtra(Constants.USR_MSG_KEY, message)
            startActivity(intent)
        }

        sendToOtherApp.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, usrmsg.text.toString())
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Select Your application:"))
        }
    }
}
