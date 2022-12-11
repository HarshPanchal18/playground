package com.example.playground.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
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

    override fun getCount(): Int {
        return titles.size
    }

    override fun getItem(p0: Int): Any {
        return p0
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }
    //ArrayAdapter<View>(c, R.layout.compound_list, R.id.idtitle){

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            //View.inflate(context, R.layout.compound_list, parent)

            //val inflater= context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            //val rowView=inflater.inflate(R.layout.compound_list,null,)//true)
            val rowView: View =
                LayoutInflater.from(c).inflate(R.layout.compound_list,parent,false)
            //val rowView:View=inflater.inflate(R.layout.compound_list,null)//,true)
            //val rowView=inflater.inflate(context,R.layout.compound_list,parent)//,true)

            /*      val rowView: View // Creating an instance for View Object
                  val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                  rowView = inflater.inflate(R.layout.compound_list, null)
          */
            val titleTxt=rowView.findViewById(R.id.idtitle) as TextView
            val descTxt=rowView.findViewById(R.id.iddescription) as TextView
            val imgFrame=rowView.findViewById(R.id.imgid) as ImageView

            titleTxt.text=titles[position]
            descTxt.text=description[position]
            imgFrame.setImageResource(images[position])

            return rowView
        }

    }
