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
import com.example.playground.activities.AdminContact
import com.example.playground.activities.LockScreen
import com.example.playground.activities.ParseData
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main2.*


class MainActivity2 : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

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
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }
}
