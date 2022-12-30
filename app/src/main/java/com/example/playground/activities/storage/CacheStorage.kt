package com.example.playground.activities.storage

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_cache.*
import java.io.*

class CacheStorage : AppCompatActivity() {

    private val fileName:String="myCache"
    var name:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cache)
        supportActionBar!!.hide()

        cacheSavebtn.setOnClickListener { saveFile() }
        cacheReadbtn.setOnClickListener { readFile() }
    }

    private fun readFile(){
        try {
            val file= File(cacheDir,fileName)
            val fin=FileInputStream(file)
            val inputStream= InputStreamReader(fin)
            val bufferedReader= BufferedReader(inputStream)
            val stringBuilder=StringBuilder()
            var line: String?

            while(run {
                    line = bufferedReader.readLine()
                    line
                } != null) // https://discuss.kotlinlang.org/t/assignment-not-allow-in-while-expression/339/6
            { stringBuilder.append(line) }

            fin.close()
            inputStream.close()

            cacheReadText.text=stringBuilder.toString()
            Toast.makeText(this,"Retrieved..",Toast.LENGTH_SHORT).show()
        } catch (e:IOException) { e.printStackTrace() }
    }

    private fun saveFile(){
        try{
            val file=File(cacheDir,fileName)
            val fos=FileOutputStream(file)
            this.name=cacheName.text.toString()
            fos.write(name.toByteArray())
            fos.close()
            Toast.makeText(this,"Saved..",Toast.LENGTH_SHORT).show()
        } catch (e:IOException) { e.printStackTrace() }
    }
}
