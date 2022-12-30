package com.example.playground.activities.storage

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_external_storage.*
import java.io.*

class ExternalStorage : AppCompatActivity() {
    private val PERMISSION_WRITE_EXTERNAL_STORAGE = 123
    private val fileName:String="externalFile"
    private var name: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_external_storage)
        supportActionBar!!.hide()

        externalSavebtn.setOnClickListener {
            val permissionCheck=ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            if(permissionCheck==PackageManager.PERMISSION_GRANTED)
                saveFile()
            else
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    PERMISSION_WRITE_EXTERNAL_STORAGE
                )
        }
        externalReadbtn.setOnClickListener { readFile() }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            PERMISSION_WRITE_EXTERNAL_STORAGE -> {
                if(grantResults.isNotEmpty() &&
                    grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    saveFile()
                else
                    Toast.makeText(
                        this,
                        "Please grant Permission to write data",
                        Toast.LENGTH_SHORT
                    ).show()
                return
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun saveFile() {
        try {
            // To save the File in a Directory called "MyData",
            /*val myDir=File(Environment.getExternalStorageDirectory().path+"/MyData")
            myDir.mkdirs()
            val fileInDir=File(myDir,fileName)*/

            // To save the file directly in root,
            val file= File(Environment.getExternalStorageDirectory(),fileName)

            // This will remain the same for both above
            val fos=FileOutputStream(file)
            name=externalName.text.toString()
            fos.write(name!!.toByteArray())
            fos.close()
            Toast.makeText(
                this,
                "Date Saved to the External Storage",
                Toast.LENGTH_SHORT
            ).show()
        } catch (e:IOException) { e.printStackTrace() }
    }

    private fun readFile() {
        try {
            // To save the File in a Directory called "MyData"
            /*val myDir=File(Environment.getExternalStorageDirectory().path+"/MyData")
            myDir.mkdirs()
            val fileInDir=File(myDir,fileName)*/

            // To read the File Directly in root
            val file= File(Environment.getExternalStorageDirectory(),fileName)
            val fin =FileInputStream(file)
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

            externalReadText.text=stringBuilder.toString()
            Toast.makeText(this,"Retrieved..",Toast.LENGTH_SHORT).show()
        } catch (e:IOException) { e.printStackTrace() }
    }
}
