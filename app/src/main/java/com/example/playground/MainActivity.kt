package com.example.playground

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
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
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.playground.activities.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {//, PopupMenu.OnMenuItemClickListener { // implements Popmenu for popup window

     override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    var BackLightValue: Float = 0.5f
    //lateinit var mAudio:AudioManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // R is a special class in java
        // that enables you to retrieve the references to resource in your app.

        // Appbar Configs
        supportActionBar?.title = "Playground"
        val actionBar: ActionBar? = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#f7ac34"))
        actionBar?.setBackgroundDrawable(colorDrawable)

        val scrollTxt: TextView = findViewById(R.id.scrollingtxt)
        scrollTxt.isSelected = true


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
            CustomToast(this, "Hold Me", Toast.LENGTH_SHORT, Color.BLACK, Color.WHITE)
        }

        /*scrollbtn.setOnClickListener(object: View.OnClickListener{
            /*override fun onClick(v: View?) {
                val popup:PopupMenu= PopupMenu(this,v)
                //popup.setOnMenuItemClickListener(MainActivity)
            }*/
            override fun onClick(v: View?) {
                val popup = PopupMenu(this@MainActivity, v)
                popup.setOnMenuItemClickListener(this@MainActivity)
                popup.inflate(R.menu.popup_menu)
                popup.show()
            }
        })*/

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

        createNotificationChannel()

        val intent=Intent(this,MainActivity::class.java)
        val pendingIntent=TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(intent) // add this intent activity if we click on notification
            getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT) // when the spinning already exists
        }

        val notification=NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle("Awesome Notification")
            .setContentText("This is the content text") // description of notification
            .setSmallIcon(R.drawable.ic_home_current)
            .setPriority(NotificationCompat.PRIORITY_HIGH) // customize the previously set priority if required
            .setContentIntent(pendingIntent) // add a pending intent to stay until clearing the notification manually
            .build() // to really build the notification (above are only designed the notification, not going to build without build())
        val notificationManager= NotificationManagerCompat.from(this) // manager for notification

        val notificationbtn:Button=findViewById(R.id.notificationbtn)
        notificationbtn.setOnClickListener {
            notificationManager.notify(NOTIFICATION_ID,notification)
        }

        val animbtn:Button=findViewById(R.id.animbtn)
        animbtn.setOnClickListener {
            startActivity(Intent(this,AnimationActivity::class.java))
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

    private val CHANNEL_ID="channelID"
    private val CHANNEL_NAME="channelName"
    private val NOTIFICATION_ID=0
    private fun createNotificationChannel(){
        val channel= NotificationChannel(CHANNEL_ID,CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT) // Priority for notification
            .apply { //bcz we want to do something with channel
                lightColor=Color.GREEN
                enableLights(true)
            }
        val manager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager // to get system service
        manager.createNotificationChannel(channel)
    }

    private fun CustomToast(
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

    // For popup menu
    /*override fun onMenuItemClick(item: MenuItem): Boolean {
        Toast.makeText(this, "Selected Item: " + item.title, Toast.LENGTH_SHORT).show()
        return when (item.itemId) {
            R.menu.popup_menu ->                 // do your code
                true
            /*R.id.copy_item ->                 // do your code
                true
            R.id.print_item ->                 // do your code
                true*/
            else -> false
        }
    }*/

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

    // Ask again when exit
    private var backPressedTime:Long=0
    lateinit var layout:ConstraintLayout

    override fun onBackPressed() {
        /*this@MainActivity.finish()
        exitProcess(0)*/

        if(backPressedTime+2000>System.currentTimeMillis()) {
            super.onBackPressed()
            exitProcess(0)
        }
        else{
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
        Toast.makeText(this,"Selected",Toast.LENGTH_SHORT).show()
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
                /*startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", packageName, null)})*/
            }
            R.id.action_share->{
                Toast.makeText(applicationContext, "clicked on share", Toast.LENGTH_LONG).show()
                true
            }
            R.id.action_exit->{
                Toast.makeText(applicationContext, "clicked on exit", Toast.LENGTH_LONG).show()
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Lock")
                builder.setMessage(R.string.dialogMsgExit)
                builder.setIcon(android.R.drawable.ic_dialog_alert)

                //positive action
                builder.setPositiveButton("Yes") { _, _ ->
                    //startActivity(Intent(this, LockScreen::class.java))
                    finish() }

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
