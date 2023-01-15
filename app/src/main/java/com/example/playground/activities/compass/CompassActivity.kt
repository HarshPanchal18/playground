package com.example.playground.activities.compass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_compass.*

class CompassActivity : AppCompatActivity() {

    private var compass: Compass? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compass)

        try{
            compass= Compass(this)
        } catch (e:IllegalStateException) {
            e.printStackTrace()
            Toast.makeText(this, "Either accelerometer or magnetic sensor not found" , Toast.LENGTH_LONG).show()
        }
        compass?.arrowView=image_hands
    }

    override fun onResume() {
        super.onResume()
        compass?.start()
    }

    override fun onPause() {
        super.onPause()
        compass?.stop()
    }

    companion object {
        private val TAG = "CompassActivity"
    }
}
