package com.example.playground.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.playground.R

class MyHorizontalAdapter(private val versionList:ArrayList<Version>) :
    RecyclerView.Adapter<MyHorizontalAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(version:Version){
            val textView = itemView.findViewById<TextView>(R.id.cardName)
            val imageView = itemView.findViewById<ImageView>(R.id.cardImageView)
            textView.text = version.name

            Glide.with(itemView.context).load(version.url).into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_horizontal,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(versionList[position])
    }

    override fun getItemCount(): Int {
        return versionList.size
    }
}
