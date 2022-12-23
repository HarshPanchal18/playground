package com.example.playground.activities.bitspizza

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.ListFragment

class StoreFragment : ListFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val adapter= ArrayAdapter(
            inflater.context,
            R.layout.simple_list_item_1,
            resources.getStringArray(com.example.playground.R.array.store)
        )
        listAdapter=adapter
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}
