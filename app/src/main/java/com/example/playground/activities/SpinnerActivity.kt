package com.example.playground.activities

import android.os.Bundle
import android.view.View
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.playground.R
import com.example.playground.adapters.BeerExpert

class SpinnerActivity : AppCompatActivity() {

    private val expert = BeerExpert()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun onBeerClick(view: View) {
        val brands = findViewById<TextView>(R.id.brands)
        val color=findViewById<Spinner>(R.id.spincolor)

        // get the selected item in the spinner
        val beerType:String= color.selectedItem.toString()

        // custom class
        val brandList=expert.getBrands(beerType)
        val brandsFormatted:StringBuilder=StringBuilder()
        for (brand in brandList){
            brandsFormatted.append(brand).append("\n")
        }//

        //display the selected item
        brands.text=brandsFormatted
        brandsFormatted.clear()
    }
}
