package com.example.playground.activities.bitspizza

import com.example.playground.R

class Pizza(name:String,resId:Int) {
    private lateinit var name:String
    private var resId:Int=0

    val pizzas= arrayOf(
        Pizza("Diavolo", R.drawable.diavolo),
        Pizza("Funghi", R.drawable.funghi),
    )

    fun getName(): String = name
    fun getId(): Int =resId

}
