package com.example.playground

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.annotation.Nullable
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.playground.activities.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {//, PopupMenu.OnMenuItemClickListener { // implements Popmenu for popup window

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
        val actionBar: ActionBar? = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#f7ac34"))
        actionBar?.setBackgroundDrawable(colorDrawable)

        val scrollTxt: TextView = findViewById(R.id.scrollingtxt)
        scrollTxt.isSelected = true

        val layout: ConstraintLayout = findViewById(R.id.homelayout)

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
            startActivity(Intent(this, ProgressbarActivity::class.java))
        }

        val imgbtn: Button = findViewById(R.id.imgbtn)
        imgbtn.setOnClickListener {
            startActivity(Intent(this, ImgActivity::class.java))
        }

        val stopwbtn: Button = findViewById(R.id.stopwatchbtn)
        stopwbtn.setOnClickListener {
            startActivity(Intent(this, Stopwatch::class.java))
        }

        val seekbar:SeekBar = findViewById(R.id.seekbar)
        val seektext:TextView = findViewById(R.id.barnumber)
        seektext.text= seekbar.progress.toString()+"/"+seekbar.max
        seekbar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            var prog_val:Int=0
            override fun onProgressChanged(seekbarr: SeekBar?, progress: Int, fromuser: Boolean) {
                prog_val=progress
            }

            override fun onStartTrackingTouch(seekbarr: SeekBar?) { // called as you starting to slide
                seektext.text=prog_val.toString()+"/"+seekbar.max
            }

            override fun onStopTrackingTouch(seekbarr: SeekBar?) {
                seektext.text=prog_val.toString()+"/"+seekbar.max
            }
        })

        val launchbtn:Button=findViewById(R.id.launchbtn)
        launchbtn.setOnClickListener {
            startActivity(Intent(this,LaunchActivity::class.java))
        }
    }

    fun CustomToast(
        context: Context?, text: String?, duration: Int,
        @Nullable backgroundColor: Int?,
        @Nullable textColor: Int?
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
        menuInfo: ContextMenu.ContextMenuInfo?) {
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
    protected fun isOnline(): Boolean {
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
