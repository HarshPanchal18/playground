package com.example.playground.activities

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.playground.R
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlinx.android.synthetic.main.activity_alarm.*
import java.util.*


class AlarmActivity : AppCompatActivity() {

    private lateinit var timePicker: MaterialTimePicker
    private lateinit var calendar: Calendar
    private var alarmManager: AlarmManager? = null
    private lateinit var pendingIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

        createNotificationChannel()

        selectTime.setOnClickListener {
            timePicker=MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Select Alarm Time")
                .build()

            timePicker.show(supportFragmentManager,"Playground")
            timePicker.addOnPositiveButtonClickListener {

                selectTime.text = if(timePicker.hour>12)
                    //selectTime.text=
                        String.format("%02d",(timePicker.hour -12)) +":"+ String.format("%02d", timePicker.minute)+"PM"
                else
                    //selectTime.text=
                        String.format("%02d",(timePicker.hour)) +":"+ String.format("%02d",(timePicker.minute))+"AM"

                calendar=Calendar.getInstance()
                calendar.set(Calendar.HOUR_OF_DAY,timePicker.hour)
                calendar.set(Calendar.MINUTE,timePicker.minute)
                calendar.set(Calendar.SECOND,0)
                calendar.set(Calendar.MILLISECOND,0)
            }
        }

        setAlarmbtn.setOnClickListener {
            alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

            val intent= Intent(this,AlarmReciever::class.java)
            pendingIntent=PendingIntent.getBroadcast(this,0,intent,0)

            alarmManager?.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,AlarmManager.INTERVAL_DAY,pendingIntent)
            Toast.makeText(this,"Alarm Set",Toast.LENGTH_SHORT).show()
        }

        cancelbtn.setOnClickListener {
            val intent= Intent(this,AlarmReciever::class.java)
            pendingIntent=PendingIntent.getBroadcast(this,0,intent,0)

            if(alarmManager==null)
                alarmManager=getSystemService(Context.ALARM_SERVICE) as AlarmManager

            alarmManager?.cancel(pendingIntent)
            Toast.makeText(this,"Alarm Cancelled",Toast.LENGTH_SHORT).show()
        }
    }

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val name:CharSequence="Playground"
            val desc="Alarm Activity"
            val imp=NotificationManager.IMPORTANCE_HIGH
            val channel=NotificationChannel("Playground",name,imp)
            channel.description=desc

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
}
