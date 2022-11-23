package com.example.playground.activities

import android.os.Bundle
import android.os.SystemClock
import android.os.Handler
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import android.widget.Chronometer.OnChronometerTickListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.playground.R
import java.util.*

class Stopwatch : AppCompatActivity() {

    private var seconds: Int = 0
    private var running: Boolean = false
    private var wasRunning:Boolean = false // a new variable, records whether the stopwatch was running before the onStop() was called.
    // to record whether the stopwatch was called
    // so that we know whether to set it running again when the activity becomes visible

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stopwatch)
        supportActionBar!!.hide()

        if(savedInstanceState!=null) {
            seconds=savedInstanceState.getInt("seconds")
            running=savedInstanceState.getBoolean("running")
            wasRunning=savedInstanceState.getBoolean("wasRunning") // save the state
        }

        runTimer()
    }

     fun onClickStart(v:View) {
        if(wasRunning)
            running=true
    }

    fun onClickStop(v:View) {
        wasRunning=running // record whether the stopwatch was running when the onStop() is called
        running=false
    }

    fun onReset(view:View) {
        running=false
        seconds=0
    }

    private fun runTimer(){
        val timeView: TextView =findViewById(R.id.timeview)
        val handler= Handler()

        handler.post (object: Runnable{
            override fun run(){

                // formats the second into hours, minutes & seconds
                val h:Int=seconds/3600
                val m:Int=(seconds%3600)/60
                val s:Int=seconds%60
                val time:String=String.format(Locale.getDefault(),"%d:%02d:%02d",h,m,s)

                timeView.text=time
                if(running)
                    seconds++

                handler.postDelayed(this,1000)
                /*
                 Post the code in the runnable to be run again after a delay of 1000 ms.
                 As this code is included in the runnable run(), it will keep getting called.

                * Using the post() and postDelayed() in this way means that the code will run ASAP after the required delay,

                * which in practise immediately.While this means the code will lag slightly over time, it's accurate enough for
                the purpose of exploring the lifecycle methods
                * */
            }
        })
    }

    // added for save the instance rotating the device
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.run {
            putInt("seconds",seconds)
            putBoolean("running",running)
            putBoolean("wasRunning",wasRunning)
        }
    }
}
