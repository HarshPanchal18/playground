package com.example.playground

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.playground.activities.AdminContact
import com.example.playground.activities.LockScreen
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
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
                    //Toast.makeText(applicationContext,"")
                    drawer2.closeDrawer(GravityCompat.START)
                    startActivity(Intent(this, AdminContact::class.java))
                }

                R.id.logout -> {
                    Toast.makeText(applicationContext,"Log Out", Toast.LENGTH_SHORT).show()
                    drawer2.closeDrawer(GravityCompat.START)
                    startActivity(Intent(this, LockScreen::class.java))
                }
            }
            true
        }

        toggle= ActionBarDrawerToggle(this,drawer2,R.string.open,R.string.close)
        drawer2.addDrawerListener(toggle) // attach toggle with drawer
        toggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }
}
