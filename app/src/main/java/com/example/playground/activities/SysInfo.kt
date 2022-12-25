package com.example.playground.activities

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.BatteryManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_sys_info.*

class SysInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sys_info)

        supportActionBar!!.title = "System Information"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val sysversion = findViewById<View>(R.id.sysversion) as TextView
        sysversion.text = System.getenv("os.version")

        val osname = findViewById<View>(R.id.osname) as TextView
        osname.text = System.getProperty("os.name")

        //relativeLayout.setBackgroundResource(R.color.cool);

        val osarch = findViewById<View>(R.id.arch) as TextView
        osarch.text = System.getProperty("os.arch")

        val manufacturer = findViewById<View>(R.id.manufacturer) as TextView
        manufacturer.text = Build.MANUFACTURER + "\n"

        registerReceiver(this.batteryInfoReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))

        val sensors= getSystemService(SENSOR_SERVICE) as SensorManager
        val sensortext:TextView=findViewById(R.id.sensortxt)

        val sList: List<Sensor> = sensors.getSensorList(Sensor.TYPE_ALL)

        for (i in 1 until sList.size)
            sensortext.append( "\n" + sList[i].name .trimIndent())
    }

    var batteryInfoReceiver: BroadcastReceiver? = object : BroadcastReceiver() {
        @SuppressLint("SetTextI18n")
        override fun onReceive(context: Context?, intent: Intent) {
            val health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0)
            val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)
            val plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0)
            val present = intent.extras!!.getBoolean(BatteryManager.EXTRA_PRESENT)
            val status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, 0)
            val technology = intent.extras!!.getString(BatteryManager.EXTRA_TECHNOLOGY)
            val temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0)
            val voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0)
            battery.text = """
Health: $health
Level: $level
Plugged: $plugged
Present: $present
Status: $status
Technology: $technology
Temperature: $temperature
Voltage: $voltage
"""+"\n".trimIndent()
        }
    }
}
