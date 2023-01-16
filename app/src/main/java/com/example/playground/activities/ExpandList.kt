package com.example.playground.activities

import android.content.Context
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.TextView
import com.example.playground.R

class ExpandList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expand_list)

        val myChild:List<String>
        val adapter:MyAdapter
        val explist: ExpandableListView = findViewById(R.id.expandlist)
        val myHeader:HashMap<String,List<String>> = DataProvider.getInfo()

        myChild=ArrayList<String>(myHeader.keys)
        adapter=MyAdapter(this,myHeader,myChild)
        explist.setAdapter(adapter)
    }

class MyAdapter
    (private val ctx: Context, private val childTitles:HashMap<String,List<String>>, private val headerTitle:List<String>) :
    BaseExpandableListAdapter() {

    override fun getGroupCount(): Int {
        return headerTitle.size
    }

    override fun getChildrenCount(p0: Int): Int {
        return childTitles[headerTitle[p0]]?.size!!
    }

    override fun getGroup(p0: Int): Any {
        return headerTitle[p0]
    }

    override fun getChild(p0: Int, p1: Int): Any {
        return childTitles[headerTitle[p0]]!![p1]
    }

    override fun getGroupId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getChildId(p0: Int, p1: Int): Long {
        return p0.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(p0: Int, p1: Boolean, view: View?, p3: ViewGroup?): View? {
        var _view=view
        val title:String=this.getGroup(p0) as String
        if(view==null){
            val inflater= this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            _view=inflater.inflate(R.layout.expand_children,null)
        }
        val txt: TextView = _view?.findViewById(R.id.child)!!
        txt.setTypeface(null, Typeface.BOLD)
        txt.text=title
        return _view
    }

    override fun getChildView(p0: Int, p1: Int, p2: Boolean, view: View?, p4: ViewGroup?): View? {
        var _view=view
        val title:String=this.getChild(p0,p1) as String
        if(view==null){
            val inflater= this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            _view=inflater.inflate(R.layout.expand_children,null)
        }
        val txt: TextView = _view?.findViewById(R.id.child)!!
        txt.text=title
        return _view
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return false
    }
}

class DataProvider{

    companion object{
        fun getInfo():HashMap<String, List<String>> {
            val child1=ArrayList<String>()
            child1.add("Children 1")
            child1.add("Children 2")
            child1.add("Children 3")
            child1.add("Children 4")

            val child2=ArrayList<String>()
            child2.add("Children 5")
            child2.add("Children 6")
            child2.add("Children 7")
            child2.add("Children 8")

            val child3=ArrayList<String>()
            child3.add("Children 9")
            child3.add("Children 10")
            child3.add("Children 11")
            child3.add("Children 12")

            val headerDetail=HashMap<String,List<String>>()
            headerDetail["Header 1"] = child1
            headerDetail["Header 2"] = child2
            headerDetail["Header 3"] = child3

            return headerDetail
        }
    }
}
}
