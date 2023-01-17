package com.example.playground.activities

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_screen_shot.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class ScreenShot : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_shot)
    }

    fun screenShot(view: View) {
        val bitmap=getBitMapRootView(view)
        imageView.setImageBitmap(bitmap)
        createImage(bitmap)
    }

    private fun createImage(bitmap: Bitmap) {
        val bytes=ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG,40,bytes)
        val file= File(Environment.getExternalStorageDirectory().toString().plus("/screenshot.jpg"))
        try{
            file.createNewFile()
            val outputStream=FileOutputStream(file)
            outputStream.write(bytes.toByteArray())
            outputStream.close()
        } catch (e:Exception){ e.printStackTrace() }
    }

    private fun getBitMapRootView(view: View): Bitmap {
        val rootView=view.rootView
        rootView.isDrawingCacheEnabled=true
        return rootView.drawingCache
    }
}
