package com.example.playground.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.playground.R
import com.example.playground.adapters.Movie
import com.example.playground.adapters.SwipeToDeleteCallBack
import kotlinx.android.synthetic.main.activity_swipe_delete.*

class SwipeDelete : AppCompatActivity() {

    // https://github.com/musabagab/RecyclerViewSwipeToDeleteStarter
    // https://www.youtube.com/watch?v=wxoUkqXyi94&list=PLdG8S8J9LP6mZtVBURNiZ4jPBnKUHIfkW&index=82

    private val moviesList=mutableListOf(
        Movie("Raising Arizona",1987),
        Movie("Vampire's Kiss",1988),
        Movie("Con Air",1997),
        Movie("Face/Off",1997),
        Movie("National Treasure",2004),
        Movie("The Weaker Man",2006),
        Movie("Bad Lieutenant",2009),
        Movie("Kick - ass",2010),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe_delete)

        swipe_delete_recycle.apply {
            layoutManager=LinearLayoutManager(this@SwipeDelete)
            adapter=com.example.playground.adapters.ListAdapter(moviesList)
        }

        val swipeToDeleteCallBack=object:SwipeToDeleteCallBack(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition // found the position of the item that should be deleted.
                moviesList.removeAt(position)
                swipe_delete_recycle.adapter?.notifyItemRemoved(position)
            }
        }

        val itemTouchHelper=ItemTouchHelper(swipeToDeleteCallBack)
        itemTouchHelper.attachToRecyclerView(swipe_delete_recycle)
    }
}
