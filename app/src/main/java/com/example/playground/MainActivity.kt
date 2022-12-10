package com.example.playground

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.AudioManager
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.annotation.Nullable
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import com.example.playground.activities.*
import com.example.playground.activities.starbuzz.StarBuzz
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() { //, PopupMenu.OnMenuItemClickListener { // implements Popmenu for popup window

    lateinit var toggle:ActionBarDrawerToggle

     override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // R is a special class in java
        // that enables you to retrieve the references to resource in your app.

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
                    Toast.makeText(applicationContext,"Home Page",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,MainActivity::class.java))
                }

                R.id.page2 -> {
                    Toast.makeText(applicationContext,"Secondary Page",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,MainActivity2::class.java))
                }

                R.id.contact -> {
                    startActivity(Intent(this, AdminContact::class.java))
                }

                R.id.logout -> {
                    Toast.makeText(applicationContext,"Log Out",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,LockScreen::class.java))
                }
            }
            drawer.closeDrawer(GravityCompat.START)
            true
        }

        val scrollTxt: TextView = findViewById(R.id.scrollingtxt)
        scrollTxt.isSelected = true

        toggle= ActionBarDrawerToggle(this,drawer,R.string.open,R.string.close)
        drawer.addDrawerListener(toggle) // attach toggle with drawer
        toggle.syncState()

        val alertbtn: Button = findViewById(R.id.alertbtn)
        alertbtn.setOnClickListener {
            startActivity(Intent(this, AlertActivity::class.java))
        }

        val cardbtn: Button = findViewById(R.id.cardbtn)
        cardbtn.setOnClickListener {
            startActivity(Intent(this, CardView::class.java))
        }

        val tabbtn: Button = findViewById(R.id.tabbtn)
        tabbtn.setOnClickListener {
            startActivity(Intent(this, TabActivity::class.java))
        }

        val spinbtn: Button = findViewById(R.id.spinner)
        spinbtn.setOnClickListener {
            startActivity(Intent(this, SpinnerActivity::class.java))
        }

        val intentbtn: Button = findViewById(R.id.intentbtn)
        intentbtn.setOnClickListener {
            startActivity(Intent(this, Implicit_intent::class.java))
        }

        val sysbtn: Button = findViewById(R.id.sysinfobtn)
        sysbtn.setOnClickListener {
            startActivity(Intent(this, SysInfo::class.java))
        }

        val scrRotatebtn: Button = findViewById(R.id.orientation)
        scrRotatebtn.setOnClickListener {
            startActivity(Intent(this, ScreenRotation::class.java))
        }

        val bgcolorbtn: Button = findViewById(R.id.bgbtn)
        bgcolorbtn.setOnClickListener {
            startActivity(Intent(this,Bgcolor::class.java))
        }

        //--------Flat button
        val fltbtn: FloatingActionButton = findViewById(R.id.floating)
        fltbtn.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Lock")
            builder.setMessage(R.string.dialogMessageLock)
            builder.setIcon(android.R.drawable.ic_dialog_alert)

            //positive action
            builder.setPositiveButton("Yes") { _, _ ->
                startActivity(Intent(this, LockScreen::class.java))
                finish()
            }

            //cancel action
            builder.setNegativeButton("No")
            { _, _ -> Toast.makeText(applicationContext, "Welcome Back :)", Toast.LENGTH_SHORT).show() }

            //create the alert dialog
            val alertDialog: AlertDialog = builder.create()

            //other properties
            alertDialog.setCancelable(false)
            alertDialog.show()
        }

        val netbtn: Button = findViewById(R.id.networkbtn)
        netbtn.setOnClickListener {
            checkConnection()
        }

        val scrollbtn: Button = findViewById(R.id.scrollbtn)
        registerForContextMenu(scrollbtn) // Register For Hold Action
        scrollbtn.setOnClickListener {
            /*val toast:Toast=Toast.makeText(this,"Hold Me",Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.CENTER,0,0)
            val view=toast.view
            view?.background?.setColorFilter(Color.BLACK,PorterDuff.Mode.SRC_IN)
            toast.show()*/
            customToast(this, "Hold Me", Toast.LENGTH_SHORT, Color.BLACK, Color.WHITE)
        }

        val txtclockbtn: Button = findViewById(R.id.clockbtn)
        txtclockbtn.setOnClickListener {
            startActivity(Intent(this, ClockActivity::class.java))
        }

        val progressbtn: Button = findViewById(R.id.progressbtn)
        progressbtn.setOnClickListener {
            startActivity(Intent(this, Progressbar::class.java))
        }

        val imgbtn: Button = findViewById(R.id.imgbtn)
        imgbtn.setOnClickListener {
            startActivity(Intent(this, ImgActivity::class.java))
        }

        val stopwbtn: Button = findViewById(R.id.stopwatchbtn)
        stopwbtn.setOnClickListener {
            startActivity(Intent(this, Stopwatch::class.java))
        }

        val seekbarRing:SeekBar = findViewById(R.id.seekbarring)
        initVolumeControls(seekbarRing,AudioManager.STREAM_RING)

        val seekbarMedia:SeekBar = findViewById(R.id.seekbarmedia)
        initVolumeControls(seekbarMedia,AudioManager.STREAM_MUSIC)

        val launchbtn:Button=findViewById(R.id.launchbtn)
        launchbtn.setOnClickListener {
            startActivity(Intent(this,LaunchActivity::class.java))
        }

        val bottomtab:Button=findViewById(R.id.bottomtab)
        bottomtab.setOnClickListener {
            startActivity(Intent(this,BottomTab::class.java))
        }

        val sendsms:Button=findViewById(R.id.sendsms)
        sendsms.setOnClickListener {
            startActivity(Intent(this,SendSMS::class.java))
        }

        val pickerbtn:Button=findViewById(R.id.pickerbtn)
        pickerbtn.setOnClickListener {
            startActivity(Intent(this,PickerActivity::class.java))
        }

        val framebtn:Button=findViewById(R.id.framebtn)
        framebtn.setOnClickListener {
            startActivity(Intent(this, FrameLayoutActivity::class.java))
        }

        val notificationbtn:Button=findViewById(R.id.notificationbtn)
        notificationbtn.setOnClickListener {
            startActivity(Intent(this,Notification::class.java))
        }

        val animbtn:Button=findViewById(R.id.animbtn)
        animbtn.setOnClickListener {
            startActivity(Intent(this,AnimationActivity::class.java))
        }

        val pull:Button=findViewById(R.id.pullrefresh)
        pull.setOnClickListener {
            startActivity(Intent(this,PullRefresh::class.java))
        }

        val list:Button=findViewById(R.id.listbtn)
        list.setOnClickListener {
            startActivity(Intent(this,ListViewActivity::class.java))
        }

        val coffeebtn:Button=findViewById(R.id.coffeebtn)
        coffeebtn.setOnClickListener {
            startActivity(Intent(this, StarBuzz::class.java))
        }

        val expbtn:Button=findViewById(R.id.expandbtn)
        expbtn.setOnClickListener {
            startActivity(Intent(this,ExpandList::class.java))
        }

        val rating: RatingBar =findViewById(R.id.rate)
        rating.setOnRatingBarChangeListener { ratingBar, fl, b ->
            Toast.makeText(this,ratingBar.rating.toString(),Toast.LENGTH_SHORT).show()
            val intent=Intent(Intent.ACTION_VIEW)
            intent.data=Uri.parse("https://play.google.com/store/apps/details?id=com.example.playground")
            startActivity(intent)
        }

        val wallpaperbtn:Button=findViewById(R.id.wallpaperbtn)
        wallpaperbtn.setOnClickListener {
            startActivity(Intent(this,WallPaper::class.java))
        }
    }

    // Central Function For Volume
    private fun initVolumeControls(seek: SeekBar?, stream: Int) {
        val mAudio= getSystemService(Context.AUDIO_SERVICE) as AudioManager
        seek?.max = mAudio.getStreamMaxVolume(stream)
        seek?.progress = mAudio.getStreamVolume(stream)
        seek?.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(bar: SeekBar, progress: Int, fromUser: Boolean) {
                mAudio.setStreamVolume(stream, progress, AudioManager.FLAG_PLAY_SOUND)
            }
            override fun onStartTrackingTouch(bar: SeekBar) {}
            override fun onStopTrackingTouch(bar: SeekBar) {}
        })
    }

    private fun customToast(
        context: Context?, text: String?, duration: Int,
        @Nullable backgroundColor: Int?,
        @Nullable textColor: Int?,
    ) {
        val t = Toast.makeText(context, text, duration)
        if (backgroundColor != null) t.view?.backgroundTintList =
            ColorStateList.valueOf(backgroundColor)
        if (textColor != null) (t.view!!.findViewById<View>(android.R.id.message) as TextView)
            .setTextColor(textColor)
        t.show()
    }

    // -----------Context for Scroll View
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?,
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu?.setHeaderTitle("Select Orientation:")

        if (v != null) { menu?.add(0,v.id,0,"Horizontal") }
        if (v != null) { menu?.add(0,v.id,0,"Vertical") }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        if(item.title=="Vertical")
            startActivity(Intent(this, ScrollVActivity::class.java))
        else
            startActivity(Intent(this,ScrollHActivity::class.java))
        return true
    }

    // Online button
    private fun isOnline(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }

    private fun checkConnection() {
        if (isOnline())
            Toast.makeText(this, "Yes", Toast.LENGTH_SHORT).show()
         else
            Toast.makeText(this,"No",Toast.LENGTH_SHORT).show()
    }

    // Ask again for exit
    private var backPressedTime:Long=0
    lateinit var layout:ConstraintLayout

    override fun onBackPressed() {
        /*this@MainActivity.finish()
        exitProcess(0)*/

        if(backPressedTime+2000>System.currentTimeMillis()) {
            super.onBackPressed()
            exitProcess(0)
        }
        else {
            layout=findViewById(R.id.homelayout)
            val snackBar=
                Snackbar.make(homelayout,"Press back again to exit",Snackbar.LENGTH_SHORT)
                .setAction("BACK"){
                    Snackbar.make(homelayout,"Welcome",Snackbar.LENGTH_SHORT).show()
                }
            snackBar.setActionTextColor(Color.YELLOW)
            val snackBarView=snackBar.view
            snackBarView.setBackgroundColor(Color.BLACK)
            snackBar.show()
        }
        backPressedTime= System.currentTimeMillis()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)) { return true }

        return when(item.itemId)
        {
            R.id.action_setting->{
                Toast.makeText(applicationContext, "clicked on setting", Toast.LENGTH_LONG).show()
                val intent=Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                val uri: Uri =Uri.fromParts("package",packageName,null)
                intent.data = uri
                startActivity(intent)
                true
            }

            R.id.action_share->{
                Toast.makeText(applicationContext, "clicked on share", Toast.LENGTH_LONG).show()
                true
            }

            R.id.action_exit->{
                Toast.makeText(applicationContext, "clicked on exit", Toast.LENGTH_LONG).show()
                val builder = AlertDialog.Builder(this)
                builder.setTitle("EXIT")
                builder.setMessage(R.string.dialogMsgExit)
                builder.setIcon(android.R.drawable.ic_dialog_alert)

                //positive action
                builder.setPositiveButton("Yes") { _, _ -> finish() }

                //cancel action
                builder.setNegativeButton("No")
                { _, _ -> Toast.makeText(applicationContext, "Welcome Back :)", Toast.LENGTH_SHORT).show() }

                //create the alert dialog
                val alertDialog: AlertDialog = builder.create()

                //other properties
                alertDialog.setCancelable(false)
                alertDialog.show()
                true
            }
            else->super.onOptionsItemSelected(item)
        }
    }
}
