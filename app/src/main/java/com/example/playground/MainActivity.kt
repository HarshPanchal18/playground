package com.example.playground

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
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
import androidx.browser.customtabs.CustomTabsIntent
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.core.view.MenuItemCompat
import com.example.playground.activities.*
import com.example.playground.activities.GridView
import com.example.playground.activities.starbuzz.StarBuzz
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() { //, PopupMenu.OnMenuItemClickListener { // implements Popmenu for popup window

    lateinit var toggle:ActionBarDrawerToggle
    var shareActionProvider:ShareActionProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // R is a special class in java
        // that enables you to retrieve the references to resource in your app.

        // Appbar Configs
        supportActionBar?.title = resources.getString(R.string.app_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val actionBar: ActionBar? = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#f7ac34"))
        actionBar?.setBackgroundDrawable(colorDrawable)

        FirebaseMessaging.getInstance().subscribeToTopic("Notification")
        //FirebaseMessaging.getInstance().unsubscribeFromTopic("Notification")

        val navView: NavigationView = findViewById(R.id.navView)
        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.page1 -> {
                    applicationContext.showToast("Home Page")
                    startActivity<MainActivity>()
                    //startActivity(Intent(this,MainActivity::class.java))
                }

                R.id.page2 -> {
                    applicationContext.showToast("Secondary Page")
                    startActivity<MainActivity2>()
                }

                R.id.contact -> {
                    startActivity<AdminContact>()
                }

                R.id.logout -> {
                    applicationContext.showToast("Logged out")
                    startActivity<LockScreen>()
                }
            }
            drawer.closeDrawer(GravityCompat.START)
            false
        }

        val scrollTxt: TextView = findViewById(R.id.scrollingtxt)
        scrollTxt.isSelected = true

        toggle= ActionBarDrawerToggle(this,drawer,R.string.open,R.string.close)
        drawer.addDrawerListener(toggle) // attach toggle with drawer
        toggle.syncState()

        val alertbtn: Button = findViewById(R.id.alertbtn)
        alertbtn.setOnClickListener {
            startActivity<AlertActivity>()
        }

        val cardbtn: Button = findViewById(R.id.cardbtn)
        cardbtn.setOnClickListener {
            startActivity<CardView>()
        }

        val tabbtn: Button = findViewById(R.id.tabbtn)
        tabbtn.setOnClickListener {
            startActivity<TabActivity>()
        }

        val spinbtn: Button = findViewById(R.id.spinner)
        spinbtn.setOnClickListener {
            startActivity<SpinnerActivity>()
        }

        val intentbtn: Button = findViewById(R.id.intentbtn)
        intentbtn.setOnClickListener {
            startActivity<Implicit_intent>()
        }

        val sysbtn: Button = findViewById(R.id.sysinfobtn)
        sysbtn.setOnClickListener {
            startActivity<SysInfo>()
        }

        val scrRotatebtn: Button = findViewById(R.id.orientation)
        scrRotatebtn.setOnClickListener {
            startActivity<ScreenRotation>()
        }

        val bgcolorbtn: Button = findViewById(R.id.bgbtn)
        bgcolorbtn.setOnClickListener {
            startActivity<Bgcolor>()
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
                startActivity<LockScreen>()
                finish()
            }

            //cancel action
            builder.setNegativeButton("No")
            { _, _ -> applicationContext.showToast("Welcome back :)") }

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
            startActivity<ClockActivity>()
        }

        val progressbtn: Button = findViewById(R.id.progressbtn)
        progressbtn.setOnClickListener {
            startActivity<Progressbar>()
        }

        val imgbtn: Button = findViewById(R.id.imgbtn)
        imgbtn.setOnClickListener {
            startActivity<ImgActivity>()
        }

        val stopwbtn: Button = findViewById(R.id.stopwatchbtn)
        stopwbtn.setOnClickListener {
            startActivity<Stopwatch>()
        }

        val seekbarRing:SeekBar = findViewById(R.id.seekbarring)
        initVolumeControls(seekbarRing,AudioManager.STREAM_RING)

        val seekbarMedia:SeekBar = findViewById(R.id.seekbarmedia)
        initVolumeControls(seekbarMedia,AudioManager.STREAM_MUSIC)

        val launchbtn:Button=findViewById(R.id.launchbtn)
        launchbtn.setOnClickListener {
            startActivity<LaunchActivity>()
        }

        val bottomtab:Button=findViewById(R.id.bottomtab)
        bottomtab.setOnClickListener {
            startActivity<BottomTab>()
        }

        val gridbtn:Button=findViewById(R.id.gridbtn)
        gridbtn.setOnClickListener {
            startActivity<GridView>()
        }

        val pickerbtn:Button=findViewById(R.id.pickerbtn)
        pickerbtn.setOnClickListener {
            startActivity<PickerActivity>()
        }

        val framebtn:Button=findViewById(R.id.framebtn)
        framebtn.setOnClickListener {
            startActivity<FrameLayoutActivity>()
        }

        val notificationbtn:Button=findViewById(R.id.notificationbtn)
        notificationbtn.setOnClickListener {
            startActivity<Notification>()
        }

        val animbtn:Button=findViewById(R.id.animbtn)
        animbtn.setOnClickListener {
            startActivity<AnimationActivity>()
        }

        val pull:Button=findViewById(R.id.pullrefresh)
        pull.setOnClickListener {
            startActivity<PullRefresh>()
        }

        val list:Button=findViewById(R.id.listbtn)
        list.setOnClickListener {
            startActivity<ListViewActivity>()
        }

        val coffeebtn:Button=findViewById(R.id.coffeebtn)
        coffeebtn.setOnClickListener {
            startActivity<StarBuzz>()
        }

        val expbtn:Button=findViewById(R.id.expandbtn)
        expbtn.setOnClickListener {
            startActivity<ExpandList>()
        }

        val rating: RatingBar =findViewById(R.id.rate)
        rating.setOnRatingBarChangeListener { ratingBar, _, _ ->
            this.showToast(ratingBar.rating.toString())
            if(ratingBar.rating.toInt()>=4){
                val intent=Intent(Intent.ACTION_VIEW)
                intent.data=Uri.parse("https://play.google.com/store/apps/details?id=com.example.playground")
                startActivity(intent)
            }
        }

        val wallpaperbtn:Button=findViewById(R.id.wallpaperbtn)
        wallpaperbtn.setOnClickListener {
            startActivity<WallPaper>()
        }

        val searchbtn:Button=findViewById(R.id.searchbtn)
        searchbtn.setOnClickListener {
            startActivity<SearchActivity>()
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
            startActivity<ScrollVActivity>()
        else
            startActivity<ScrollHActivity>()
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
            this.showToast("Yes")
         else
             this.showToast("No")
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
                    Snackbar.make(homelayout,"Welcome",Snackbar.LENGTH_SHORT).show() }
            snackBar.setActionTextColor(Color.YELLOW)
            val snackBarView=snackBar.view
            snackBarView.setBackgroundColor(Color.BLACK)
            snackBar.show()
        }
        backPressedTime= System.currentTimeMillis()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.options_menu,menu)
        val menuItem:MenuItem=menu.findItem(R.id.action_share)
        shareActionProvider = (MenuItemCompat.getActionProvider(menuItem) as ShareActionProvider?)
        setShareAction("Want to join?")

        return super.onCreateOptionsMenu(menu)
        //return true // true tells the Android you've dealt with the item being clicked
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)) { return true }

        return when(item.itemId)
        {
            R.id.action_setting->{
                //Toast.makeText(applicationContext, "clicked setting", Toast.LENGTH_LONG).show()
                val intent=Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                val uri: Uri =Uri.fromParts("package",packageName,null)
                intent.data = uri
                startActivity(intent)
                true
            }

            R.id.action_share->{
                applicationContext.showToast("Clicked on share")
                true // true tells the Android you've dealt with the item being clicked
            }

            R.id.action_exit->{
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

            R.id.reportbug->{
                openCustomTab("https://github.com/harshpanchal18")
                true
            }
            else->super.onOptionsItemSelected(item)
        }
    }

    private fun setShareAction(str: String) { //create the intent and pass to shareActivity
        val intent=Intent(Intent.ACTION_SEND)
        intent.type="text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,str)
        shareActionProvider?.setShareIntent(intent)
        // you need to call setShareIntent() whenever the content you wish to share has changed
        // i.e. if you're flicking through images in a photos app, you need to make sure you share the current photo
    }

    private fun openCustomTab(url: String) {
        val weburi:Uri = if(!url.contains("https://") && !url.contains("http://"))
            Uri.parse("http://$url")
        else
            Uri.parse(url)

        val customTabIntent=CustomTabsIntent.Builder()
        customTabIntent.setToolbarColor(Color.parseColor("#75FF5722"))
        customTabIntent.setShowTitle(true)

        if(chromeInstalled())
            customTabIntent.build().intent.setPackage("com.android.chrome")
        customTabIntent.build().launchUrl(this,weburi)
    }

    private fun chromeInstalled(): Boolean {
        return try{ packageManager.getPackageInfo("com.android.chrome",0); true
        } catch(e:Exception){ false }
    }

    inline fun <reified T : Activity> Activity.startActivity() {
        startActivity(Intent(this, T::class.java))
    }

    private fun Context.showToast(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}
