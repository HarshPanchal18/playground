package com.example.playground.activities.bitspizza

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.playground.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_order.*

class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        setSupportActionBar(toolbar_order)
        val actionBar=supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        fab_book.setOnClickListener {
            val text="Your order has been updated"
            val duration=Snackbar.LENGTH_LONG
            val snackbar=Snackbar.make(order_coordinator,text,duration)
            snackbar.setAction("Undo"){
                Toast.makeText(this,"Undone!",Toast.LENGTH_SHORT).show()
            }.setActionTextColor(Color.BLACK)
                .setBackgroundTint(Color.GRAY)
                .show()
        }
    }
}
