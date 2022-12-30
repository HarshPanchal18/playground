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
import kotlinx.android.synthetic.main.activity_external_storage_public_dir.*
import java.io.*

class ExternalStoragePublicDir : AppCompatActivity() {
    private val PERMISSION_WRITE_EXTERNAL_STORAGE = 123
    private val fileName:String="externalPublicFile"
    private var name: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_external_storage_public_dir)
        supportActionBar!!.hide()

        externalPublicSavebtn.setOnClickListener {
            val permissionCheck= ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            if(permissionCheck== PackageManager.PERMISSION_GRANTED)
                saveFile()
            else
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    PERMISSION_WRITE_EXTERNAL_STORAGE
                )
        }
        externalPublicReadbtn.setOnClickListener { readFile() }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            PERMISSION_WRITE_EXTERNAL_STORAGE -> {
                if(grantResults.isNotEmpty() &&
                    grantResults[0]== PackageManager.PERMISSION_GRANTED)
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
            // To save the File in a Directory called "MyData" in the "Downloads" Folder
            val myDir=File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path+"/MyData")
            myDir.mkdirs()
            val fileInDir=File(myDir,fileName)

            // To save the file directly in Downloads,
            val file= File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),fileName)

            // This will remain the same for both above
            val fos= FileOutputStream(file)
            name=externalPublicName.text.toString()
            fos.write(name!!.toByteArray())
            fos.close()
            Toast.makeText(
                this,
                "Date Saved to the External Storage",
                Toast.LENGTH_SHORT
            ).show()
        } catch (e: IOException) { e.printStackTrace() }
    }

    private fun readFile() {
        try {
            // To save the File in a Directory called "MyData" in the Downloads folder,
            val myDir=File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path+"/MyData")
            myDir.mkdirs()
            val fileInDir=File(myDir,fileName)

            // To read the File Directly in Downloads folder
            val file= File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),fileName)
            val fin = FileInputStream(file)
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

            externalPublicReadText.text=stringBuilder.toString()
            Toast.makeText(this,"Retrieved..", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) { e.printStackTrace() }
    }
}
