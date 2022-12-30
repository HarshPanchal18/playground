package com.example.playground.activities.storage

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_shared_preference.*

class SharedPreference : AppCompatActivity() {

    private val fileName:String="mySharedFile"
    var name:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preference)
        supportActionBar!!.hide()

        sharedReadbtn.setOnClickListener { readFile() }
        sharedSavebtn.setOnClickListener { saveFile() }

    }

    private fun readFile() {
        val sharedPref=getSharedPreferences(fileName, Context.MODE_PRIVATE)
        val defaultValue="DefaultName"
        val name=sharedPref.getString("name",defaultValue)
        sharedReadText.text=name.toString()
        Toast.makeText(this,"Data : $name",Toast.LENGTH_SHORT).show()
    }

    private fun saveFile() {
        val strName=sharedName.text.toString()
        val sharedPref=getSharedPreferences(fileName, Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = sharedPref.edit()
        editor.putString("name",strName)
        editor.commit()
        Toast.makeText(this,"Saved : $name",Toast.LENGTH_SHORT).show()
    }
}
