package com.example.playground.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.playground.MainActivity
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_alert.*


class AlertActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert)

        // Customize the back button
        //actionBar.setHomeAsUpIndicator();
        val actionBar: ActionBar? = supportActionBar

        // showing the back button in action bar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeButtonEnabled(true)
            actionBar.apply {  }
        }

        val items = arrayOf(" PHP", " JAVA", " JSON", " C#", " Objective-C")
        val itemsSelected = ArrayList<Any>()

        checkalert.setOnClickListener {
            val builder=AlertDialog.Builder(this)
            builder.setTitle("Select Languages You Know: ")
            builder.setMultiChoiceItems(items,null
            ) { dialog, selectedItemId, isSelected ->
                if (isSelected) itemsSelected.add(selectedItemId)
                else if (itemsSelected.contains(selectedItemId)) itemsSelected.remove(Integer.valueOf(selectedItemId))
            }

            //positive action
            builder.setPositiveButton("Yes"){
                    _, _ -> Toast.makeText(this, itemsSelected.toString(), Toast.LENGTH_SHORT).show()
            }

            //cancel action
            builder.setNegativeButton("No"){
                    _, _ -> Toast.makeText(applicationContext,"Discarded changes",Toast.LENGTH_SHORT).show()
                checkalert.text="Alerted"
            }

            val alertDialog:AlertDialog=builder.create()
            alertDialog.show()
        }

        val alertbtn: Button =findViewById(R.id.altbtn)
        alertbtn.setOnClickListener {
            val builder=AlertDialog.Builder(this)
            builder.setTitle(R.string.dialogTitle)
            builder.setMessage(R.string.dialogMsgHome)
            builder.setIcon(android.R.drawable.ic_dialog_alert)

            //positive action
            builder.setPositiveButton("Yes"){
                    _, which->startActivity(Intent(this, MainActivity::class.java))
                Toast.makeText(applicationContext,"Clicked YES :)",Toast.LENGTH_SHORT).show()
            }

            //cancel action
            builder.setNegativeButton("No"){
                    _, which->Toast.makeText(applicationContext,"Clicked NO :(",Toast.LENGTH_SHORT).show()
                    alertbtn.text="Alerted"
            }

            //create the alert dialog
            val alertDialog:AlertDialog=builder.create()

            //other properties
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
    }


    fun onCheckBoxClicked(view: View) {
        val select=findViewById<TextView>(R.id.selected)
        if(view is CheckBox)
        {
            val checked:Boolean=view.isChecked
            when(view.id){
                R.id.chk_ice ->{
                    if(checked){
                        Toast.makeText(this,"ICE CREAM Selected",Toast.LENGTH_SHORT).show()
                        Toast.makeText(this,
                            Html.fromHtml("<font color='#F6AE2D' ><b>" + "Ice Cream" + "</b></font>"),Toast.LENGTH_SHORT).show()
                        select.text="Ice Cream"
                    }
                    else{
                        Toast.makeText(this,"ICE CREAM Unselected",Toast.LENGTH_SHORT).show()
                        val toast=Toast.makeText(this,"Unselected", Toast.LENGTH_LONG)
                        val view=toast.view
                        view?.setBackgroundColor(Color.GREEN)
                        toast.show()
                        select.text=""
                    }
                }
                R.id.chk_juice ->{
                    if(checked){
                        Toast.makeText(this,"Fresh Juice Selected",Toast.LENGTH_SHORT).show()
                        select.text="Fruit Juice"
                    }
                    else{
                        Toast.makeText(this,"Fresh Juice Unselected",Toast.LENGTH_SHORT).show()
                        select.text=""
                    }
                }
            }
        }
    }

    fun onRadioClicked(view: View) {
        if(view is RadioButton)
        {
            val checked=view.isChecked
            when(view.id){
                R.id.radioPirates ->if (checked) {
                    Toast.makeText(this,"Being Pirates from now..",Toast.LENGTH_SHORT).show()
                }
                R.id.radioSailer ->if (checked) {
                    Toast.makeText(this,"Being Sailor from now..",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
