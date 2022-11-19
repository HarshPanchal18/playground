package com.example.playground

//implementation com.google.android.material:material:1.4.0

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ScrollingView
import com.example.playground.activities.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_alert.view.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // R is a special class in java
        // that enables you to retrieve the references to resource in your app.

        // Appbar Configs
        supportActionBar?.title = "Playground"
        val actionBar: ActionBar? = getSupportActionBar()
        val colorDrawable = ColorDrawable(Color.parseColor("#f7ac34"))
        actionBar?.setBackgroundDrawable(colorDrawable)

        val scrollTxt: TextView = findViewById(R.id.scrollingtxt)
        scrollTxt.isSelected = true

        val alertbtn: Button = findViewById(R.id.alertbtn)
        val cardbtn: Button = findViewById(R.id.cardbtn)
        val tabbtn: Button = findViewById(R.id.tabbtn)
        val spinbtn: Button = findViewById(R.id.spinner)
        val intentbtn: Button = findViewById(R.id.intentbtn)
        val sysbtn: Button = findViewById(R.id.sysinfobtn)
        val scrRotatebtn: Button = findViewById(R.id.orientation)
        val bgcolorbtn: Button = findViewById(R.id.bgbtn)
        val fltbtn: FloatingActionButton = findViewById(R.id.floating)
        val layout: ConstraintLayout = findViewById(R.id.homelayout)
        val netbtn: Button = findViewById(R.id.networkbtn)
        val scrollbtn: Button = findViewById(R.id.scrollbtn)
        val txtclockbtn: Button = findViewById(R.id.clockbtn)
        val progressbtn: Button = findViewById(R.id.progressbtn)

        registerForContextMenu(scrollbtn) // Register For Hold Action

        alertbtn.setOnClickListener {
            startActivity(Intent(this, AlertActivity::class.java))
        }

        cardbtn.setOnClickListener {
            startActivity(Intent(this, CardView::class.java))
        }

        tabbtn.setOnClickListener {
            startActivity(Intent(this, TabActivity::class.java))
        }

        spinbtn.setOnClickListener {
            startActivity(Intent(this, SpinnerActivity::class.java))
        }

        intentbtn.setOnClickListener {
            startActivity(Intent(this, Implicit_intent::class.java))
        }

        sysbtn.setOnClickListener {
            startActivity(Intent(this, SysInfo::class.java))
        }

        scrRotatebtn.setOnClickListener {
            startActivity(Intent(this, ScreenRotation::class.java))
        }

        bgcolorbtn.setOnClickListener {
            startActivity(Intent(this,Bgcolor::class.java))
            //Settings.System.putInt(this.contentResolver,"show_touches",0)
        }

        //--------Flat button
        fltbtn.setOnClickListener {
            val builder=AlertDialog.Builder(this)
            builder.setTitle("Exit")
            builder.setMessage(R.string.dialogMessage2)
            builder.setIcon(android.R.drawable.ic_dialog_alert)

            //positive action
            builder.setPositiveButton("Yes"){ _, which-> finish() }

            //cancel action
            builder.setNegativeButton("No")
            { _, which->Toast.makeText(applicationContext,"Welcome Back :)",Toast.LENGTH_SHORT).show() }

            //create the alert dialog
            val alertDialog:AlertDialog=builder.create()

            //other properties
            alertDialog.setCancelable(false)
            alertDialog.show()
        }

        netbtn.setOnClickListener {
            checkConnection()
        }

        scrollbtn.setOnClickListener {
            /*val toast:Toast=Toast.makeText(this,"Hold Me",Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER,0,0)
            //toast.setGravity(Gravity.CENTER,-50,0)
            val view=toast.view
            view?.background?.setColorFilter(Color.BLACK,PorterDuff.Mode.SRC_IN)
            toast.show()*/
            CustomToast(this, "Hold Me", Toast.LENGTH_SHORT, Color.BLACK, Color.WHITE)
        }

        txtclockbtn.setOnClickListener {
            startActivity(Intent(this, ClockActivity::class.java))
        }

        progressbtn.setOnClickListener {
            startActivity(Intent(this, ProgressbarActivity::class.java))
        }
    }

    fun CustomToast(
        context: Context?, text: String?, duration: Int,
        @Nullable backgroundColor: Int?,
        @Nullable textColor: Int?
    ) {
        val t = Toast.makeText(context, text, duration)
        if (backgroundColor != null) t.view?.backgroundTintList =
            ColorStateList.valueOf(backgroundColor)
        if (textColor != null) (t.view!!.findViewById<View>(android.R.id.message) as TextView)
            .setTextColor(textColor)
        t.show()
    }

    // -----------Context for Scroll View
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu?.setHeaderTitle("Select Orientation:")

        if (v != null) { menu?.add(0,v.id,0,"Horizontal") }
        if (v != null) { menu?.add(0,v.id,0,"Vertical") }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        if(item.title=="Vertical")
            startActivity(Intent(this, ScrollVActivity::class.java))
        else
            startActivity(Intent(this,ScrollHActivity::class.java))
        return true
    }

    // Online button
    protected fun isOnline(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo

        return netInfo != null && netInfo.isConnectedOrConnecting
    }

    fun checkConnection() {
        if (isOnline())
            Toast.makeText(this, "Yes", Toast.LENGTH_SHORT).show()
         else
            Toast.makeText(this,"No",Toast.LENGTH_SHORT).show()
    }

    // Ask again when exit
    private var backPressedTime:Long=0
    lateinit var backToast: Toast

    override fun onBackPressed() {
        /*
        this@MainActivity.finish()
        exitProcess(0)
        */

        backToast = Toast.makeText(this, "Press again to Exit", Toast.LENGTH_SHORT)
        if(backPressedTime+2000>System.currentTimeMillis()) {
            backToast.cancel()
            super.onBackPressed()
            //return
            exitProcess(0)
        }
        else
            backToast.show()

        backPressedTime= System.currentTimeMillis()
    }
}
