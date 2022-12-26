package com.example.playground.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_shimmer_loading.*

class ShimmerLoading : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shimmer_loading)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        shimmerView.startShimmer() // disable this view on process done...
    }
}
