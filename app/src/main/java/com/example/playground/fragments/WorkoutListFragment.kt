package com.example.playground.fragments

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment
import com.example.playground.adapters.Workout


class WorkoutListFragment : ListFragment() {
    internal interface WorkoutListListener {
        fun itemClicked(id: Long)
    }

    private var listener: WorkoutListListener? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val names = arrayOfNulls<String>(Workout.workouts.size)
        for (i in names.indices) {
            names[i] = Workout.workouts[i].name
        }
        val adapter = ArrayAdapter(
            inflater.context,
            android.R.layout.simple_list_item_1,
            names)
        listAdapter = adapter
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        listener = activity as WorkoutListListener?
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        listener!!.itemClicked(id)
    }
}
