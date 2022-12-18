package com.example.playground.activities

import android.app.Activity
import android.os.Bundle
import com.example.playground.R
import com.example.playground.fragments.WorkoutDetailFragment


class DetailActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val workoutDetailFragment =
            fragmentManager.findFragmentById(R.id.detail_frag) as WorkoutDetailFragment
        val workoutId = intent.extras!![EXTRA_WORKOUT_ID] as Int
        workoutDetailFragment.setWorkout(workoutId.toLong())
    }

    companion object {
        const val EXTRA_WORKOUT_ID = "id"
    }
}
