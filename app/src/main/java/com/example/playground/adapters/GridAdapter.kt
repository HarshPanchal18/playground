package com.example.playground.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.playground.R

class GridAdapter(val context: Context, val arrList: ArrayList<gridItem>) : BaseAdapter() {

    override fun getCount(): Int = arrList.size

    override fun getItem(pos: Int): Any = arrList[pos]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view:View=View.inflate(context,R.layout.grid_row_item,null)
        val icons:ImageView=view.findViewById(R.id.grid_img)
        val names:TextView=view.findViewById(R.id.grid_title)

        val griditem:gridItem=arrList[position]
        icons.setImageResource(griditem.icons!!)
        names.text=griditem.name

        return view
    }

    class gridItem(var icons: Int?, var name: String?) {}
}
