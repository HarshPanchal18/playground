package com.example.playground.activities.starbuzz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.playground.R
import com.example.playground.adapters.Drink
import com.example.playground.utils.Constants

class DrinkCategory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink_category)

        val listAdapter:ArrayAdapter<Drink> =
            ArrayAdapter(this,
                android.R.layout.simple_list_item_1, // it tells the array adapter to display each item in the array in a single textView
                Drink.drinks)

        val listDrink: ListView =findViewById(R.id.list_drinks)
        listDrink.adapter=listAdapter // attach the adapter

        // Create the listener
        val itemClickListener: AdapterView.OnItemClickListener=AdapterView.OnItemClickListener{ adapterView, view, i, l ->
            val intent =Intent(applicationContext,DrinkMainActivity::class.java)
            //intent.putExtra(DrinkMainActivity.EXTRA_DRINKID, l.toInt())
            intent.putExtra(Constants.EXTRA_DRINK_ID, l.toInt())
            startActivity(intent)
        }

        listDrink.onItemClickListener = itemClickListener
    }
}
