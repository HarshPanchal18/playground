package com.example.playground.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.playground.R

class ListAdapter(private val list:List<Movie>) :RecyclerView.Adapter<MovieViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater,parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie:Movie=list[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = list.size

}

class MovieViewHolder(inflater:LayoutInflater,parent:ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.swipe_list_item,parent,false)){

    private var titleView: TextView? = null
    private var yearView: TextView? = null

    init {
        titleView = itemView.findViewById(R.id.list_title)
        yearView = itemView.findViewById(R.id.list_year)
    }
    fun bind(movie:Movie){
        titleView?.text=movie.title
        yearView?.text= movie.year.toString()
    }
}
