package com.example.playground.activities

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.ViewSwitcher
import androidx.appcompat.app.AppCompatActivity
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_switcher.*

class Switcher : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_switcher)

        var mPos=-1
        val TEXT= arrayOf(
            "The miniature pet elephant became the envy of the neighborhood.",
            "When confronted with a rotary dial phone the teenager was perplexed.",
            "She tilted her head back and let whip cream stream into her mouth while taking a bath.",
            "The pet shop stocks everything you need to keep your anaconda happy.",
            "The beach was crowded with snow leopards.")
        val imgs=arrayOf(
            R.mipmap.earth,
            R.mipmap.owl,
            R.mipmap.metalboy,
            R.mipmap.splashimg,
            R.mipmap.ic_launcher_round)

        txtswitcher.setFactory (object : ViewSwitcher.ViewFactory {
            override fun makeView(): View {
                val tView = TextView(applicationContext)
                tView.textSize = 18F
                return tView
            }
        })

        imgswitcher.setFactory(object :ViewSwitcher.ViewFactory{
            override fun makeView(): View {
                val iView= ImageView(applicationContext)
                iView.scaleType=ImageView.ScaleType.FIT_CENTER
                return iView
            }
        })

        prevbtn.setOnClickListener {
            if(mPos>0){
                mPos--
                txtswitcher.setText(TEXT[mPos])
                imgswitcher.setBackgroundResource(imgs[mPos])
            }
            if(mPos==0)
                mPos=TEXT.size
        }

        nextbtn.setOnClickListener {
            if(mPos<TEXT.size-1){
                mPos++
                txtswitcher.setText(TEXT[mPos])
                imgswitcher.setBackgroundResource(imgs[mPos])
            }
            if(mPos+1==TEXT.size)
                mPos=-1
        }
    }
}
