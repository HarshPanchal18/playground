package com.example.playground.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.playground.R

class MyCompoundAdapter
    (
    private val c: Context,
    private val titles: Array<String>,
    private val description: Array<String>,
    private val images: Array<Int>,
    ) : BaseAdapter() {

    override fun getCount(): Int = titles.size

    override fun getItem(p0: Int): Any = p0

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val rowView: View = LayoutInflater.from(c).inflate(R.layout.compound_list,parent,false)
        val titleTxt=rowView.findViewById(R.id.idtitle) as TextView
        val descTxt=rowView.findViewById(R.id.iddescription) as TextView
        val imgFrame=rowView.findViewById(R.id.imgid) as ImageView

        titleTxt.text=titles[position]
        descTxt.text=description[position]
        imgFrame.setImageResource(images[position])

        return rowView
    }
}
