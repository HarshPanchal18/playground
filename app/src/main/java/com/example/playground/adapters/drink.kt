package com.example.playground.adapters
import com.example.playground.R

class Drink(val name: String, val desc: String, val imgID: Int) {
//Each com.example.playground.adapters.Drink has a name, description, and an image resource

    override fun toString(): String {
        return name
    }

    companion object {
        //drinks is an array of Drinks
        val drinks = arrayOf(
            Drink("Latte", "A couple of espresso shots with steamed milk",
                R.drawable.latte),
            Drink("Cappuccino", "Espresso, hot milk, and a steamed milk foam",
                R.drawable.cappuccino),
            Drink("Filter", "Highest quality beans roasted and brewed fresh",
                R.drawable.filter)
        )
    }
}

/*
companion - In Kotlin, if you want to write a function or any member of the class that can be called
without having the instance of the class then you can write the same as a member of a companion object inside the class.
So, by declaring the companion object, you can access the members of the class by class name only(which is without explicitly creating the instance of the class).
*/
