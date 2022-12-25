package com.example.playground.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.playground.R
import com.example.playground.adapters.ListAdapter
import com.example.playground.adapters.Movie
import com.example.playground.adapters.SwipeToDeleteCallBack
import kotlinx.android.synthetic.main.activity_swipe_hold.*

class SwipeHold : AppCompatActivity() {

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
        setContentView(R.layout.activity_swipe_hold)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        swipe_delete_recycle.apply {
            layoutManager=LinearLayoutManager(this@SwipeHold)
            adapter= ListAdapter(moviesList)
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

        val itemTouchHelper2=object:ItemTouchHelper.Callback(){
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
            ): Int {
                val dragFlags=ItemTouchHelper.UP or ItemTouchHelper.DOWN
                return makeMovementFlags(dragFlags,0)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean { // notify item moved
                recyclerView.adapter?.notifyItemMoved(viewHolder.adapterPosition,target.adapterPosition) // front and end positions
                return true // item has been successfully moved...
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}
        }

        val itemTouchHelperCallback=ItemTouchHelper(itemTouchHelper2)
        itemTouchHelperCallback.attachToRecyclerView(swipe_delete_recycle)
    }
}
