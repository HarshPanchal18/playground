package com.example.playground

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import com.example.playground.activities.*
import com.example.playground.activities.storage.StorageMainActivity
import com.example.playground.activities.workout.WorkoutMainActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    val CAMERA_REQUEST:Int=123
    var hasFlash=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA),CAMERA_REQUEST)
        hasFlash=packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)

        // Appbar Configs
        supportActionBar?.title = "Playground"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val actionBar: ActionBar? = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#f7ac34"))
        actionBar?.setBackgroundDrawable(colorDrawable)

        val navView: NavigationView = findViewById(R.id.navView)
        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.page1 -> {
                    Toast.makeText(applicationContext,"Page 1", Toast.LENGTH_SHORT).show()
                    drawer2.closeDrawer(GravityCompat.START)
                    startActivity(Intent(this,MainActivity::class.java))
                }

                R.id.page2 -> {
                    Toast.makeText(applicationContext,"Page 2", Toast.LENGTH_SHORT).show()
                    drawer2.closeDrawer(GravityCompat.START)
                    startActivity(Intent(this,MainActivity2::class.java))
                }

                R.id.contact -> {
                    drawer2.closeDrawer(GravityCompat.START)
                    startActivity(Intent(this, AdminContact::class.java))
                }

                R.id.logout -> {
                    Toast.makeText(applicationContext,"Logged Out", Toast.LENGTH_SHORT).show()
                    drawer2.closeDrawer(GravityCompat.START)
                    startActivity(Intent(this, LockScreen::class.java))
                }
            }
            true
        }

        toggle= ActionBarDrawerToggle(this,drawer2,R.string.open,R.string.close)
        drawer2.addDrawerListener(toggle) // attach toggle with drawer
        toggle.syncState()

        parsebtn.setOnClickListener {
            startActivity(Intent(this, ParseData::class.java))
        }

        flashfloatbtn.setOnClickListener {
            var counter=0
            if(hasFlash){
                if(counter.mod(2)==0) flashOff()
                else flashOn()
                counter++
            } else { Toast.makeText(this,"No Flashes",Toast.LENGTH_SHORT).show() }
        }

        switcher.setOnClickListener {
            startActivity(Intent(this,Switcher::class.java))
        }

        fragmentbtn.setOnClickListener {
            startActivity(Intent(this, WorkoutMainActivity::class.java))
        }

        swipe_deletebtn.setOnClickListener {
            startActivity(Intent(this,SwipeDelete::class.java))
        }

        storagebtn.setOnClickListener {
            startActivity(Intent(this,StorageMainActivity::class.java))
        }

        validationbtn.setOnClickListener {
            startActivity(Intent(this,ValidationActivity::class.java))
        }

        recyclerbtn.setOnClickListener {
            startActivity(Intent(this,RecyclerActivity::class.java))
        }

        viewSliderbtn.setOnClickListener {
            startActivity(Intent(this,ViewPager::class.java))
        }
    }

    private fun flashOn() {
        val cameraManager=getSystemService(Context.CAMERA_SERVICE) as CameraManager
        try {
            val cameraID:String=cameraManager.cameraIdList[0]
            cameraManager.setTorchMode(cameraID,true)
        } catch (e:CameraAccessException) {  }
    }

    private fun flashOff() {
        val cameraManager=getSystemService(Context.CAMERA_SERVICE) as CameraManager
        try {
            val cameraID:String=cameraManager.cameraIdList[0]
            cameraManager.setTorchMode(cameraID,false)
        } catch (e:CameraAccessException) {  }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            CAMERA_REQUEST -> {
                if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    hasFlash=packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)
                else {
                    flashfloatbtn.isEnabled=true
                    Toast.makeText(this,"Permission denied",Toast.LENGTH_SHORT).show()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }
}
