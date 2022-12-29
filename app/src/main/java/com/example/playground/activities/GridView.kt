package com.example.playground.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.example.playground.R
import com.example.playground.adapters.GridAdapter
import kotlinx.android.synthetic.main.activity_grid_view.*

class GridView : AppCompatActivity() , AdapterView.OnItemClickListener{

    private var arrList:ArrayList<GridAdapter.gridItem>?=null
    private var gridAdapter:GridAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_view)

        arrList=ArrayList()
        arrList=setDataList()
        gridAdapter= GridAdapter(applicationContext,arrList!!)
        gridView.adapter=gridAdapter
        gridView.onItemClickListener=this
    }

    private fun setDataList() : ArrayList<GridAdapter.gridItem> {
        val gridArrList:ArrayList<GridAdapter.gridItem> = ArrayList()
        for(i in 0..10)
            gridArrList.add(GridAdapter.gridItem(R.mipmap.ic_launcher,i.toString()))
        return gridArrList
    }

    override fun onItemClick(adapterView: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        val gridItem:GridAdapter.gridItem=arrList!![pos]
        Toast.makeText(applicationContext,gridItem.name,Toast.LENGTH_SHORT).show()
    }
}
