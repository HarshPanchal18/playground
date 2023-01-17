package com.example.playground

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.playground.activities.*
import com.example.playground.activities.bitspizza.BitsPizzaMain
import com.example.playground.activities.compass.CompassActivity
import com.example.playground.activities.sqlitedb.DatabaseMainActivity
import com.example.playground.activities.storage.StorageMainActivity
import com.example.playground.activities.workout.WorkoutMainActivity
import com.google.android.material.navigation.NavigationView
import com.r0adkll.slidr.Slidr
import com.r0adkll.slidr.model.SlidrConfig
import com.r0adkll.slidr.model.SlidrInterface
import com.r0adkll.slidr.model.SlidrPosition
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var slidr : SlidrInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // Appbar Configs
        supportActionBar?.title = "Playground"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val actionBar: ActionBar? = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#f7ac34"))
        actionBar?.setBackgroundDrawable(colorDrawable)

        // Made a Activity Slider
        slidr=Slidr.attach(this)
        val config=SlidrConfig.Builder()
            .position(SlidrPosition.LEFT)
            .build()
        Slidr.attach(this,config)

        val navView: NavigationView = findViewById(R.id.navView)
        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.page1 -> {
                    Toast.makeText(applicationContext,"Home Page", Toast.LENGTH_SHORT).show()
                    drawer2.closeDrawer(GravityCompat.START)
                    finish()
                }

                R.id.page2 -> {
                    Toast.makeText(applicationContext,"Secondary Page", Toast.LENGTH_SHORT).show()
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
                    finish()
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
            finish()
            startActivity(intent)
            overridePendingTransition(0,1) // Call immediately after one of the flavors of #startActivity(Intent) or #finish to specify an explicit transition animation to perform next.
            Toast.makeText(this,"Restarted",Toast.LENGTH_SHORT).show()
        }

        switcher.setOnClickListener {
            startActivity(Intent(this,Switcher::class.java))
        }

        fragmentbtn.setOnClickListener {
            startActivity(Intent(this, WorkoutMainActivity::class.java))
        }

        swipe_deletebtn.setOnClickListener {
            startActivity(Intent(this,SwipeHold::class.java))
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

        bitspizzabtn.setOnClickListener {
            startActivity(Intent(this,BitsPizzaMain::class.java))
        }

        txtlinks.setOnClickListener {
            startActivity(Intent(this,TextLinks::class.java))
        }

        dynamic_list.setOnClickListener {
            startActivity(Intent(this,DynamicList::class.java))
        }

        bottomSheetbtn.setOnClickListener {
            startActivity(Intent(this,BottomSheet::class.java))
        }

        shimmerbtn.setOnClickListener {
            startActivity(Intent(this,ShimmerLoading::class.java))
        }

        sqlitebtn.setOnClickListener {
            startActivity(Intent(this, DatabaseMainActivity::class.java))
        }

        dropDownbtn.setOnClickListener {
            startActivity(Intent(this,DropDownActivity::class.java))
        }

        slideImagebtn.setOnClickListener {
            startActivity(Intent(this,ImageSlider::class.java))
        }

        alarmbtn.setOnClickListener {
            startActivity(Intent(this,AlarmActivity::class.java))
        }

        interFragbtn.setOnClickListener {
            startActivity(Intent(this,InterFragment::class.java))
        }

        compassbtn.setOnClickListener {
            startActivity(Intent(this,CompassActivity::class.java))
        }

        gesturebtn.setOnClickListener {
            startActivity(Intent(this,GestureDetectorActivity::class.java))
        }

        horizontalRecyclerViewbtn.setOnClickListener {
            startActivity(Intent(this,HorizontalRecyclerView::class.java))
        }

        screenshotbtn.setOnClickListener {
            startActivity(Intent(this,ScreenShot::class.java))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }
}
