package com.example.playground.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_image_slider.*

class ImageSlider : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_slider)

        val slideModel=ArrayList<SlideModel>()
        slideModel.add(SlideModel(R.drawable.latte,ScaleTypes.FIT))
        slideModel.add(SlideModel(R.drawable.filter,ScaleTypes.FIT))
        slideModel.add(SlideModel(R.drawable.diavolo,ScaleTypes.FIT))
        slideModel.add(SlideModel(R.drawable.cappuccino,ScaleTypes.FIT))
        slideModel.add(SlideModel(R.drawable.funghi,ScaleTypes.FIT))
        slideModel.add(SlideModel(R.drawable.developer,ScaleTypes.FIT))

        imageSlider.setImageList(slideModel,ScaleTypes.FIT)
    }
}
