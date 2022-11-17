package com.example.playground.activities

import java.util.ArrayList

class BeerExpert {
    fun getBrands(color: String): List<String> {
        val brands: MutableList<String> = ArrayList()

        /*if (color == "Amber") {
            brands.add("Jack Amber")
            brands.add("Red Moose")
        } else if(color=="Dark"){
            brands.add("Jail Pale Ale")
            brands.add("Gout Stout")
        }else if(color=="Light")
            brands.add("Carlsberg")
        else
            brands.add("Haywards")
        */

        when (color) {
            "Amber" -> {
                brands.add("Jack Amber")
                brands.add("Red Moose")
            }
            "Dark" -> {
                brands.add("Jail Pale Ale")
                brands.add("Gout Stout")
            }
            "Light" -> brands.add("Carlsberg")
            else -> brands.add("Haywards")
        }

        return brands
    }
}