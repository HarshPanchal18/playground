package com.example.playground.activities

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_camera_view.*

class CameraView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_view)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        capturebtn.setOnClickListener {
            var intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent,123) // if the result has been called back from the camera
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==123){
            var bmp=data?.extras?.get("data") as Bitmap
            capturedImgView.setImageBitmap(bmp)
        }
    }
}
