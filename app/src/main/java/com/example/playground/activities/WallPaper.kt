package com.example.playground.activities

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.DisplayMetrics
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_wall_paper.*
import java.io.IOException
import java.io.InputStream
import java.net.MalformedURLException
import java.net.URL

class WallPaper : AppCompatActivity() {
    lateinit var wallPaper: WallpaperManager
    lateinit var displayMetrics: DisplayMetrics
    var height: Int = 0
    var width: Int = 0
    lateinit var bit1:Bitmap
    lateinit var bit2:Bitmap
    lateinit var bitmapDrawable: BitmapDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wall_paper)
        supportActionBar!!.hide()

        // Allow strict mode (required in case of NetworkOnMainThreadException)
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val img:ImageView=findViewById(R.id.urlimg)
        val wallbtn: Button =findViewById(R.id.setbtn)

        wallPaper=WallpaperManager.getInstance(applicationContext)

        try {
            val url=URL("https://images.unsplash.com/photo-1541411438265-4cb4687110f2?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8NXx8fGVufDB8fHx8&w=1000&q=80")
            val bitmap = BitmapFactory.decodeStream(URL(url.toString()).content as InputStream)
            img.setImageBitmap(bitmap)
        }
        catch (e: MalformedURLException) { e.printStackTrace() }
        catch (e: IOException) { e.printStackTrace() }

        bitmapDrawable=img.drawable as BitmapDrawable
        bit1=bitmapDrawable.bitmap

        wallbtn.setOnClickListener {
            getScreenSizes()
            setBitMapSize()
            wallPaper= WallpaperManager.getInstance(this)
            try {
                wallPaper.setBitmap(bit2)
                wallPaper.suggestDesiredDimensions(width,height)
            } catch(e:Exception){ e.printStackTrace() }
        }
    }

    private fun setBitMapSize() {
        bit2=Bitmap.createScaledBitmap(bit1,width,height,false)
    }

    private fun getScreenSizes() {
        displayMetrics= DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        width=displayMetrics.widthPixels
        height=displayMetrics.heightPixels
    }
}
