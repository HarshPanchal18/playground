package com.example.playground.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_drop_down.*
import kotlinx.android.synthetic.main.dropdown_item.*

class DropDownActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drop_down)

        autocomplete.onItemClickListener=AdapterView.OnItemClickListener { adapterView, view, i, l ->
            selectedDropText.text=adapterView.getItemAtPosition(i).toString()
        }
    }

    override fun onResume() {
        super.onResume()
        val languages=resources.getStringArray(R.array.languages)
        val adapter=ArrayAdapter(applicationContext,R.layout.dropdown_item,languages)
        autocomplete.setAdapter(adapter)
    }
}
