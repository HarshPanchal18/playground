package com.example.playground.activities

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.playground.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_bottom_sheet.*

class BottomSheet : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_sheet)

        val colors=arrayOf("Red","Green","Cyan","Blue","Yellow","White","Magenta","Light Gray")
        val adapter:ArrayAdapter<*> = ArrayAdapter(this,R.layout.center_in_list,colors)

        colorList.adapter=adapter
        colorList.onItemClickListener=object :AdapterView.OnItemClickListener{
            override fun onItemClick(adp: AdapterView<*>?, v: View?, pos: Int, l: Long) {
                val item = colorList.getItemAtPosition(pos) as String
                when(item){
                    "Red"-> { parentLayout.setBackgroundColor(Color.RED); curr_color.text=item}
                    "Green"-> { parentLayout.setBackgroundColor(Color.GREEN); curr_color.text=item }
                    "Cyan"-> { parentLayout.setBackgroundColor(Color.CYAN); curr_color.text=item }
                    "Blue"-> { parentLayout.setBackgroundColor(Color.BLUE); curr_color.text=item }
                    "Yellow"-> { parentLayout.setBackgroundColor(Color.YELLOW); curr_color.text=item }
                    "White"-> { parentLayout.setBackgroundColor(Color.WHITE); curr_color.text=item }
                    "Magenta"-> { parentLayout.setBackgroundColor(Color.MAGENTA); curr_color.text=item }
                    "Light Gray"-> { parentLayout.setBackgroundColor(Color.LTGRAY); curr_color.text=item }
                }
            }
        }

        BottomSheetBehavior.from(sheet).apply {
            peekHeight=120 // height of collapsed sheet
            this.state=BottomSheetBehavior.STATE_COLLAPSED
            //this.state=BottomSheetBehavior.STATE_EXPANDED
            //this.state=BottomSheetBehavior.STATE_HALF_EXPANDED
        }
    }
}
