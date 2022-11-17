package com.example.playground.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.playground.R
import android.widget.TextView

class SysInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sys_info)

        supportActionBar!!.title = "System Information"

        val sysversion = findViewById<View>(R.id.sysversion) as TextView
        sysversion.text = System.getenv("os.version")

        val osname = findViewById<View>(R.id.osname) as TextView
        osname.text = System.getProperty("os.name")

        //relativeLayout.setBackgroundResource(R.color.cool);

        val osarch = findViewById<View>(R.id.arch) as TextView
        osarch.text = System.getProperty("os.arch")

        val manufacturer = findViewById<View>(R.id.manufacturer) as TextView
        manufacturer.text = Build.MANUFACTURER
    }
}