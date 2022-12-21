package com.example.playground.activities.storage

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_storage_main.*

class StorageMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage_main)

        val itemClickListener: AdapterView.OnItemClickListener=AdapterView.OnItemClickListener { adapterView, view, pos, l ->
            when(pos){
                0 -> { startActivity(Intent(this,CacheActivity::class.java)) }
                1 -> { startActivity(Intent(this,SharedPreference::class.java)) }
            }
        }

        storagelist.onItemClickListener=itemClickListener
    }
}
