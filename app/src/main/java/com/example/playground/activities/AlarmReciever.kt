package com.example.playground.activities

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.playground.R

class AlarmReciever : BroadcastReceiver() {
    override fun onReceive(context:  Context?, intent: Intent?) {
        val nextActivity=Intent(context,AlarmNotification::class.java)
        nextActivity.flags=(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK )
        val pendingIntent=PendingIntent.getActivity(context,0,nextActivity,0)

        val builder=NotificationCompat.Builder(context!!,"Playground")
            .setSmallIcon(R.drawable.alarm)
            .setContentTitle("Playground")
            .setContentText("It's time to wake up")
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)

        val notificationManagerCompat=NotificationManagerCompat.from(context)
            notificationManagerCompat.notify(123,builder.build())
    }
}
