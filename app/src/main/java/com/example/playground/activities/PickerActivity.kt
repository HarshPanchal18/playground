package com.example.playground.activities

import android.app.DatePickerDialog
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Switch
import android.widget.TimePicker
import android.widget.Toast
import com.example.playground.R
import java.util.*
import java.util.Calendar.getInstance

class PickerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picker)

        val timepicker:TimePicker=findViewById(R.id.timepicker)
        val datepicker: DatePicker =findViewById(R.id.datepicker)
        datepicker.visibility = View.INVISIBLE
        val calendar:Calendar= getInstance()

        val pickerswitch: Switch =findViewById(R.id.chooserswitch)
        pickerswitch.setOnCheckedChangeListener { _, on ->
            if (on) {
                timepicker.visibility = View.INVISIBLE
                datepicker.visibility = View.VISIBLE
            } else {
                timepicker.visibility = View.VISIBLE
                datepicker.visibility = View.INVISIBLE
            }
        }

        timepicker.setOnTimeChangedListener(object: TimePicker.OnTimeChangedListener{
            override fun onTimeChanged(tp: TimePicker?, hr: Int, mn: Int) {
                Toast.makeText(applicationContext, "Time is $hr:$mn",Toast.LENGTH_SHORT).show()
            }
        })

        val year = calendar.get(Calendar.YEAR);
        val month = calendar.get(Calendar.MONTH);
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        datepicker.init(year,month,dayOfMonth,object:DatePicker.OnDateChangedListener{
            override fun onDateChanged(p0: DatePicker?, y: Int, m: Int, d: Int) {
                Toast.makeText(applicationContext,"Date is $y/$m/$d",Toast.LENGTH_SHORT).show()
            }
        })
    }
}
