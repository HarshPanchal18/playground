package com.example.playground.activities.sqlitedb

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.playground.R
import kotlinx.android.synthetic.main.compound_list_sqlite.view.*
import java.lang.String
import kotlin.Int

class SQLiteCompoundAdapter(
    val activity: Activity,
    val context: Context,
    val id: ArrayList<*>,
    val name: ArrayList<*>,
    val sname: ArrayList<*>,
    val marks: ArrayList<*>,
) : RecyclerView.Adapter<SQLiteCompoundAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idtext:TextView=itemView.findViewById(R.id.idnum)
        val nametext:TextView=itemView.findViewById(R.id.name)
        val snametext:TextView=itemView.findViewById(R.id.sname)
        val markstext:TextView=itemView.findViewById(R.id.marks)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater=LayoutInflater.from(context)
        val v:View=layoutInflater.inflate(R.layout.compound_list_sqlite,parent,false)!!
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.idnum.text = String.valueOf(id[position])
        holder.itemView.name.text = String.valueOf(name[position])
        holder.itemView.sname.text = String.valueOf(sname[position])
        holder.itemView.marks.text = String.valueOf(marks[position])
    }

    override fun getItemCount(): Int = id.size
}
