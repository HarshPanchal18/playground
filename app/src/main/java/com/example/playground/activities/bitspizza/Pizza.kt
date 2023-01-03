package com.example.playground.activities.bitspizza

import com.example.playground.R

class Pizza private constructor(name: String, imageResourceId: Int) {
    private val name: String
    private val imageResourceId: Int

    fun getName(): String = name

    fun getImageResourceId(): Int = imageResourceId

    companion object {
        val pizzas = arrayOf(
            Pizza("Diavolo", R.drawable.diavolo),
            Pizza("Funghi", R.drawable.funghi)
        )
    }

    init {
        this.name = name
        this.imageResourceId = imageResourceId
    }
}
