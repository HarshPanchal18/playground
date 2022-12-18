package com.example.playground.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.playground.R
import com.example.playground.adapters.Workout

class WorkoutDetailFragment : Fragment() {
    private var workoutId: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            workoutId = savedInstanceState.getLong("workoutId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_workout_detail, container, false)
    }

    override fun onStart() {
        super.onStart()
        val view = view
        if (view != null) {
            val title = view.findViewById<View>(R.id.txtTitle) as TextView
            val workout = Workout.workouts[workoutId.toInt()]
            title.text = workout.name
            val description = view.findViewById<View>(R.id.txtDescription) as TextView
            description.text = workout.description
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putLong("workoutId", workoutId)
    }

    fun setWorkout(id: Long) {
        workoutId = id
    }
}
