package com.example.playground.activities.storage

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_internal_storage.*
import java.io.*

class InternalStorage : AppCompatActivity() {
    private val fileName:String="internalFile"
    private var name: String? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_internal_storage)
        supportActionBar!!.hide()

        internalSavebtn.setOnClickListener { saveFile() }
        internalReadbtn.setOnClickListener { readFile() }
    }

    private fun readFile() {
        try {
            val fin:FileInputStream=openFileInput(fileName)
            val inputStream= InputStreamReader(fin)
            val buffReader= BufferedReader(inputStream)
            val stringBuilder=StringBuilder()
            var line:String?

            while(run {
                line = buffReader.readLine()
                line
            } !=null)
            { stringBuilder.append(line) }

            fin.close()
            inputStream.close()

            internalReadText.text=stringBuilder.toString()
            Toast.makeText(this,"Retrieved..",Toast.LENGTH_SHORT).show()
        } catch (e:IOException) { e.printStackTrace() }
    }

    private fun saveFile() {
        try {
            val fos:FileOutputStream=openFileOutput(fileName, Context.MODE_PRIVATE) // MODE_APPEND to append in the File
            name=internalName.text.toString()
            fos.write(name!!.toByteArray())
            fos.close()
            Toast.makeText(this,"Written to the internal Storage",Toast.LENGTH_SHORT).show()
        } catch (e:IOException) { e.printStackTrace() }
    }
}
