package com.example.playground.activities.starbuzz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.playground.R
import com.example.playground.adapters.Drink

class DrinkMainActivity : AppCompatActivity() {
    private var EXTRA_DRINKID:String="drinkId"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink_main)

        // get the drink from the intent
        val drinkId:Int= intent.extras?.get(EXTRA_DRINKID) as Int
        val drink:Drink= Drink.drinks[drinkId]

        //populate properties
        val name:TextView=findViewById(R.id.name)
        name.text=drink.name

        val desc:TextView=findViewById(R.id.desc)
        desc.text=drink.desc

        val photo: ImageView =findViewById(R.id.drinkphoto)
        photo.setImageResource(drink.imgID)
        photo.contentDescription=drink.name
    }
}
