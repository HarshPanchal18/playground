package com.example.playground.activities.bitspizza

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.playground.R

class PizzaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        /*val adapter=ArrayAdapter(
            inflater.context,
            android.R.layout.simple_list_item_1,
            resources.getStringArray(R.array.pizza)
        )
        listAdapter=adapter
        return super.onCreateView(inflater, container, savedInstanceState)*/

        val pizzaRecyclerView=
            inflater.inflate(R.layout.fragment_pizza,container,false) as RecyclerView

        val pizzaNames = ArrayList<String>(Pizza.pizzas.size)
        for (i in 0..pizzaNames.size-1)
            pizzaNames[i] = Pizza.pizzas[i].getName()

        val pizzaImages = IntArray(Pizza.pizzas.size)
        for (i in pizzaImages.indices)
            pizzaImages[i] = Pizza.pizzas[i].getImageResourceId()

        val adapter = CaptionedImageAdapter(pizzaNames, pizzaImages)
        pizzaRecyclerView.adapter = adapter

        val gridLayoutManager=GridLayoutManager(activity,2)
        pizzaRecyclerView.layoutManager=gridLayoutManager

        return pizzaRecyclerView
    }
}
