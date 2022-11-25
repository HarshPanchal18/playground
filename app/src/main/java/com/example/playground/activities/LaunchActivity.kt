package com.example.playground.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.playground.R

class LaunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        val fcbkbtn: Button =findViewById(R.id.facebook)
        val whtsbtn: Button =findViewById(R.id.whatsapp)
        val instabtn: Button =findViewById(R.id.instagram)
        val twtbtn: Button =findViewById(R.id.twitter)
        val mapbtn: Button =findViewById(R.id.map)

        fcbkbtn.setOnClickListener {
            try {
                val intent: Intent=Intent(Intent.ACTION_SEND)
                intent.type="text/plain"
                intent.putExtra(Intent.EXTRA_TEXT,"Hello, I'm Harsh! Nice to see you here :)")
                intent.`package`="com.facebook.katana"
                startActivity(intent)
            } catch (e:Exception) { Toast.makeText(this,"Install Facebook",Toast.LENGTH_SHORT).show() }
        }

        whtsbtn.setOnClickListener {
            try {
                val intent: Intent=Intent(Intent.ACTION_SEND)
                intent.type="text/plain"
                intent.putExtra(Intent.EXTRA_TEXT,"Hello, I'm Harsh! Nice to see you here :)")
                intent.`package`="com.whatsapp"
                startActivity(intent)
            } catch(e:Exception) { Toast.makeText(this,"Install Whatsapp",Toast.LENGTH_SHORT).show() }
        }

        instabtn.setOnClickListener {
            try{
                val intent: Intent=Intent(Intent.ACTION_SEND)
                intent.type="text/plain"
                intent.putExtra(Intent.EXTRA_TEXT,"Hello, I'm Harsh! Nice to see you here :)")
                intent.`package`="com.instagram.android"
                startActivity(intent)
            } catch (e:Exception) { Toast.makeText(this,"Install Instagram",Toast.LENGTH_SHORT).show() }
        }

        twtbtn.setOnClickListener {
            try {
                val intent: Intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, "Hello, I'm Harsh! Nice to see you here :)")
                intent.`package` = "advanced.twitter.android"
                startActivity(intent)
            } catch(e:Exception) { Toast.makeText(this,"Install Twitter",Toast.LENGTH_SHORT).show() }
        }

        mapbtn.setOnClickListener {
            val intent=Intent(Intent.ACTION_VIEW)
            //intent.data = Uri.parse("geo:47.4925,19.0513")
            intent.data = Uri.parse("geo:21.171020,72.854210")
            val chooser:Intent=Intent.createChooser(intent,"Launch Maps")
            startActivity(chooser)
        }
    }
}
