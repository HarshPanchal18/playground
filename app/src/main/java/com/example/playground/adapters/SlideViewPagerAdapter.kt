package com.example.playground.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.playground.R

class SlideViewPagerAdapter(
    private val title: List<String>,
    private val desc: List<String>,
    private val image: List<Int>,
) : RecyclerView.Adapter<SlideViewPagerAdapter.Pager2ViewHolder>() {

    inner class Pager2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val itemTitle: TextView=itemView.findViewById(R.id.viewTitle)
        val itemDesc: TextView=itemView.findViewById(R.id.viewDesc)
        val itemImage: ImageView =itemView.findViewById(R.id.viewImage)

        init {
            itemImage.setOnClickListener {
                val pos=adapterPosition
                Toast.makeText(itemView.context,"Clicked on ${pos+1}th",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent:ViewGroup,viewType:Int) :SlideViewPagerAdapter.Pager2ViewHolder {
        return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_page,parent,false))
    }

    override fun getItemCount() =  title.size

    override fun onBindViewHolder(holder:SlideViewPagerAdapter.Pager2ViewHolder,pos:Int){
        holder.itemTitle.text=title[pos]
        holder.itemDesc.text=desc[pos]
        holder.itemImage.setImageResource(image[pos])
    }
}
