package com.example.playground.activities

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.playground.MainActivity
import com.example.playground.R
import com.example.playground.utils.PrefManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_lock_screen.*
import java.util.*
import kotlin.system.exitProcess

class LockScreen : AppCompatActivity() {
    private val random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lock_screen)

        supportActionBar!!.hide()

        val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator

        val li=layoutInflater
        val view=li.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast))
        val ctoast=Toast(applicationContext)
        ctoast.view = view
        ctoast.duration=Toast.LENGTH_LONG
        ctoast.show()


        val randDigit: Int = rand(0, 10)
        gobtn.setOnClickListener {
            if (passwdbox.text.toString() == randDigit.toString()) {
                passwdbox.backgroundTintList=ColorStateList.valueOf(Color.GREEN)
                startActivity(Intent(this, MainActivity::class.java))
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
            val toast = Toast.makeText(this, "Apply: $randDigit", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
        }

        appInfoCard.setOnClickListener {
            PrefManager(applicationContext).setLaunched(true)
            startActivity(Intent(this,IntroSlider::class.java))
        }
    }

    // Ask again when exit
    private var backPressedTime:Long=0
    lateinit var layout: ConstraintLayout
    override fun onBackPressed() {
        /*this@MainActivity.finish()
        exitProcess(0)*/

        if(backPressedTime+2000>System.currentTimeMillis()) {
            super.onBackPressed()
            exitProcess(0)
        }
        else{
            layout=findViewById(R.id.locklayout)
            val snackBar=
                Snackbar.make(layout,"Press back again to exit", Snackbar.LENGTH_SHORT)
                    /*.setAction("BACK"){
                        Snackbar.make(layout,"Welcome", Snackbar.LENGTH_SHORT).show()
                    }
            snackBar.setActionTextColor(Color.YELLOW)*/
            val snackBarView=snackBar.view
            snackBarView.setBackgroundColor(Color.BLACK)
            snackBar.show()
        }
        backPressedTime= System.currentTimeMillis()
    }


    private fun rand(from: Int, to: Int): Int {
        return random.nextInt(to - from) + from
    }
}
