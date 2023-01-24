package com.example.playground.activities

import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_sound_pool.*

class SoundPoolActivity : AppCompatActivity() {

    private lateinit var soundPool: SoundPool
    private var soundId: Int = 0
    private var soundPoolLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sound_pool)

        soundPool=SoundPool.Builder()
            .setMaxStreams(10)
            .build()

        soundPool.setOnLoadCompleteListener { soundPool, sampleId, status ->
            if(status==0)
                soundPoolLoaded=true
            else
                Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT).show()
        }

        soundId=soundPool.load(this,R.raw.clicking_glasses,1)
        btn_soundpool.setOnClickListener {
            if(soundPoolLoaded)
                soundPool.play(soundId,1f,1f,0,0,1f)
        }
    }
}
