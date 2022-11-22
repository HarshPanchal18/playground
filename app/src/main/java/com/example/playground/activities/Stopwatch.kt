package com.example.playground.activities

import android.os.Bundle
import android.os.SystemClock
import android.os.Handler
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

    private var start:Boolean = false
    private var chrono:Chronometer?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stopwatch)
        supportActionBar!!.hide()

        chrono=findViewById(R.id.chronotime)
        /*chrono?.onChronometerTickListener = object:Chronometer.OnChronometerTickListener{
            override fun onChronometerTick(chronoChanged: Chronometer?) {
                chrono= chronoChanged!!
            }
        }*/

        // lambda format of above
        chrono?.onChronometerTickListener =
          OnChronometerTickListener { chronoChanged -> chrono = chronoChanged }

        runTimer()
    }

        fun onStart(view: View) { running = true }

        fun onStop(view: View) { running = false }

        fun onReset(view: View) {
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

    fun StartStopChronometer(view: View) {
        if(start){
            chrono?.stop()
            start=false
            (view as Button).text = "Start"
        } else {
            chrono?.base=SystemClock.elapsedRealtime()
            chrono?.stop()
            start=true
            (view as Button).text ="Stop"
        }
    }
}
