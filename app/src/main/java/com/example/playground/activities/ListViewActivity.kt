package com.example.playground.activities

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.playground.R
import com.example.playground.adapters.MyCompoundAdapter

class ListViewActivity : AppCompatActivity() {
    val title=arrayOf("Title 1","Title 2","Title 3","Title 4")//,"Title 5")
    val descriptions=arrayOf("Description 1","Description 2","Description 3","Description 4")//,"Description 5")
    val images=arrayOf(R.drawable.ic_home_current,R.drawable.ic_profile_current,R.drawable.ic_add_current,R.drawable.ic_action_hint)
    val characters= arrayOf("Kenya","Kyrgyzstan","Kuwait","Kazakhstan" ,"Kiribati","South Korea","Pakistan",
                            "Nepal","Syria","Portugal","Russia","Moscow","Hong Kong","Brazil","Japan","Thailand","Singapore")
    lateinit var compoundList:ListView
    lateinit var clist:ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        clist = findViewById(R.id.countrylist)
        compoundList=findViewById(R.id.compundlist)

        val adapter: ArrayAdapter<*>
        adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,characters)
        clist.adapter=adapter
        clist.setOnItemClickListener(object: AdapterView.OnItemClickListener{
            override fun onItemClick(adp: AdapterView<*>?, v: View?, position: Int, l: Long) {
                val item=clist.getItemAtPosition(position) as String
                Toast.makeText(applicationContext,"Position: $position\tValue: $item",Toast.LENGTH_SHORT).show()
            }
        })

        val compoundAdapter= MyCompoundAdapter(this,title,descriptions,images)
        compoundList.adapter = compoundAdapter
        compoundList.setOnItemClickListener(object: AdapterView.OnItemClickListener{
            override fun onItemClick(adp: AdapterView<*>?, v: View?, position: Int, l: Long) {
                val item=compoundList.getItemAtPosition(position) as String
                Toast.makeText(applicationContext,"Position: $position\tValue: $item",Toast.LENGTH_SHORT).show()
            }
        })
    }
}
