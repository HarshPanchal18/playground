package com.example.playground.activities

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.playground.MainActivity
import com.example.playground.R
import java.util.*

class Notification : AppCompatActivity() {

    private val CHANNEL_ID="channelID"
    private val CHANNEL_NAME="channelName"
    private val NOTIFICATION_ID=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        val notificationbtn: Button =findViewById(R.id.simplenotibtn)
        notificationbtn.setOnClickListener {
            createSimpleNotification()
        }

        val inboxNotificationbtn: Button =findViewById(R.id.inboxnotibtn)
        inboxNotificationbtn.setOnClickListener {
            createInboxNotification()
        }

        val bigtextNotificationbtn:Button=findViewById(R.id.bigtxtnotibtn)
        bigtextNotificationbtn.setOnClickListener {
            createBigTextNotification()
        }

        val bigpicNotificationbtn:Button=findViewById(R.id.bigpicturenotibtn)
        bigpicNotificationbtn.setOnClickListener {
            createBigPicNotification()
        }
    }

    private fun createSimpleNotification(){
        val channel= NotificationChannel(CHANNEL_ID,CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT) // Priority for notification
            .apply { //bcz we want to do something with channel
                lightColor= Color.GREEN
                enableLights(true)
            }
        val manager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager // to get system service
        manager.createNotificationChannel(channel)

        val intent= Intent(this, MainActivity::class.java)
        val pendingIntent= TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(intent) // add this intent activity if we click on notification
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT) // when the spinning already exists
        }

        val notification= NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle("Awesome Notification")
            .setContentText("This is the content text") // description of notification
            .setSmallIcon(R.drawable.ic_home_current)
            .setPriority(NotificationCompat.PRIORITY_HIGH) // customize the previously set priority if required
            .setContentIntent(pendingIntent) // add a pending intent to stay until clearing the notification manually
            .build() // to really build the notification (above are only designed the notification, not going to build without build())

        val notificationManager= NotificationManagerCompat.from(this) // manager for notification

        notificationManager.notify(NOTIFICATION_ID,notification)
    }

    private fun createInboxNotification() {
        val resultIntent=Intent(this,MainActivity::class.java)
        resultIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val piResult=PendingIntent.getActivities(this,
            Calendar.getInstance().timeInMillis.toInt(), arrayOf(resultIntent),0)

        val inboxStyle=NotificationCompat.InboxStyle()
        inboxStyle.setBigContentTitle("INBOX NOTIFICATION")
        inboxStyle.addLine("Message Line 1")
        inboxStyle.addLine("Message Line 2")
        inboxStyle.addLine("Message Line 3")
        inboxStyle.addLine("Message Line 4")
        inboxStyle.addLine("Message Line 5")
        inboxStyle.setSummaryText("+2 more")

        // build notification
        val notification=NotificationCompat.Builder(this,CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_home_current)
            .setContentTitle("INBOX STYLE NOTIFICATION")
            .setContentText("Testing inbox style notification")
            .setStyle(inboxStyle)
            .addAction(R.mipmap.ic_launcher,"Show Task",piResult)
            .build()

        // Gets an instance of the NotificationManager Service
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // to post your notification to the notification bar
        notificationManager.notify(1,notification)
    }

    private fun createBigTextNotification() {
        val icon:Bitmap=BitmapFactory.decodeResource(resources,R.mipmap.ic_launcher)
        val bigText=NotificationCompat.BigTextStyle()
        bigText.bigText("Gradle first released in 2007, is a build automation tool that uses a Groovy-based DSL to build software. " +
            "It's popular as build tool, specifically for multi-project builds and is JVM-dependent." +
            " As such, it's also compatible with some common Java APIs like custom task types. " +
            "Unfortunately, Gradle builds can get bogged down with heavy computations and inefficient processes.")
        bigText.setBigContentTitle("About Gradle")
        bigText.setSummaryText("By Harsh Panchal")

        val notification=NotificationCompat.Builder(this,CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_home_current)
            .setContentTitle("BIG TEXT STYLE NOTIFICATION")
            .setContentText("Testing big text style notification")
            .setLargeIcon(icon)
            .setStyle(bigText)
            .build()

        // Gets an instance of the NotificationManager Service
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // to post your notification to the notification bar
        notificationManager.notify(2,notification)
    }

    private fun createBigPicNotification() {
        val bigPictureStyle=NotificationCompat.BigPictureStyle()
        bigPictureStyle.bigPicture(BitmapFactory.decodeResource(resources,R.mipmap.ic_launcher)).build()

        // Gets an instance of the NotificationManager Service
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val resultIntent=Intent(this,MainActivity::class.java)
        resultIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val piResult=PendingIntent.getActivities(this,
            Calendar.getInstance().timeInMillis.toInt(), arrayOf(resultIntent),0)

        val notification=NotificationCompat.Builder(this,CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_home_current)
            .setContentTitle("BIG PICTURE STYLE NOTIFICATION")
            .setContentText("Testing big picture style notification")
            .setStyle(bigPictureStyle)
            .addAction(R.drawable.ic_action_hint,"Show Activity",piResult)
            .addAction(R.drawable.ic_action_hint,"Share",
                PendingIntent.getActivity(applicationContext,0,intent,0,null))
            .build()

        // to post your notification to the notification bar
        notificationManager.notify(3,notification)
    }
}
