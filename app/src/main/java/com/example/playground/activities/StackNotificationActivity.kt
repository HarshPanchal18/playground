package com.example.playground.activities

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.playground.R
import com.example.playground.utils.NotificationItem
import kotlinx.android.synthetic.main.activity_stack_notification.*

class StackNotificationActivity : AppCompatActivity() {

    private var idNotification = 0
    private val stackNotif = ArrayList<NotificationItem>()

    companion object{
        private const val CHANNEL_NAME="Decoding channel"
        private const val GROUP_KEY_EMAILS = "group_key_emails"
        private const val NOTIFICATION_REQUEST_CODE = 200
        private const val MAX_NOTIFICATION = 2
        private const val CHANNEL_ID = "channel_01"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stack_notification)

        et_sender.setText(intent.getStringExtra("name"))
        et_message.setText(intent.getStringExtra("message"))

        btn_sender.setOnClickListener {
            val sender=et_sender.text.toString()
            val msg=et_message.text.toString()
            if(sender.isEmpty()||msg.isEmpty()) {
                Toast.makeText(this,"Data must be filled",Toast.LENGTH_SHORT).show()
            } else {
                stackNotif.add(NotificationItem(idNotification,sender,msg))
                sendNotification()
                idNotification++
                et_sender.text.clear()
                et_message.text.clear()

                val methodManager=getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                methodManager.hideSoftInputFromWindow(et_message.windowToken,0)
            }
        }
    }

    private fun sendNotification() {
        val mNotificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val largeIcon=BitmapFactory.decodeResource(resources,R.drawable.ic_baseline_notification_24)
        val intent= Intent(this, StackNotificationActivity::class.java)

        intent.putExtra("name",et_sender.text.toString())
        intent.putExtra("message",et_message.text.toString())
        intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP

        val pendingIntent=PendingIntent.getActivity(
            this,
            NOTIFICATION_REQUEST_CODE,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT)

        val mBuilder:NotificationCompat.Builder

        if(idNotification< MAX_NOTIFICATION){
            mBuilder=NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("New Email from ${stackNotif[idNotification].sender}")
                .setContentText(stackNotif[idNotification].message)
                .setSmallIcon(R.drawable.ic_baseline_mail_24)
                .setLargeIcon(largeIcon)
                .setGroup(GROUP_KEY_EMAILS)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
        } else {
            val inboxStyle=NotificationCompat.InboxStyle()
                .addLine("New Email from ${stackNotif[idNotification].sender}")
                .addLine("New Email from ${stackNotif[idNotification - 1].sender}")
                .setBigContentTitle("$idNotification new emails")
                .setSummaryText("mail@Harsh")

            mBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("$idNotification new emails")
                .setContentText("mail@Harsh.in")
                .setSmallIcon(R.drawable.ic_baseline_mail_24)
                .setGroup(GROUP_KEY_EMAILS)
                .setGroupSummary(true)
                .setContentIntent(pendingIntent)
                .setStyle(inboxStyle)
                .setAutoCancel(true)
        }

        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )

        mBuilder.setChannelId(CHANNEL_ID)
        mNotificationManager.createNotificationChannel(channel)

        val notification = mBuilder.build()
        mNotificationManager.notify(idNotification, notification)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        stackNotif.clear()
        idNotification = 0
    }
}
