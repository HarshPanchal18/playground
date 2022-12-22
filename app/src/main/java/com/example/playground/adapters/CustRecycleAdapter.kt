package com.example.playground.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.playground.R

class CustRecycleAdapter(var context:Context,personNames: ArrayList<String>) :
    RecyclerView.Adapter<CustRecycleAdapter.ViewHolder>() {

    private var personNames: ArrayList<String>

    init { this.personNames = personNames }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflate the item Layout
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.rowlayout, parent, false)
        return ViewHolder(v) // set the view's size, margins, paddings and layout parameters
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = personNames[position].toString() // set the data in items
        holder.itemView.setOnClickListener {
            Toast.makeText(context,
                personNames[position],
                Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = personNames.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name : TextView // init the item view's
        init { name = itemView.findViewById<View>(R.id.recyclename) as TextView }
    }
}
