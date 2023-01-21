package com.example.playground.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.playground.R
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.text.DecimalFormat

class GetCurrentWeatherService : JobService() {

    companion object {
        private val TAG=GetCurrentWeatherService::class.java.simpleName
        internal const val CITY = "Surat"
        internal const val APP_ID = "c55d4a58da4dc4d141c1adbcb64cd509"
    }

    override fun onStartJob(p0: JobParameters?): Boolean {
        getCurrentWeather(p0 as JobParameters)
        return true
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        return true
    }

    private fun getCurrentWeather(jobParameters: JobParameters) {
        val client= AsyncHttpClient()
        val url="https://api.openweathermap.org/data/2.5/weather?q=$CITY&appid=$APP_ID"
        client.get(url,object:AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                val result=String(responseBody as ByteArray)
                try{
                    val responseObject=JSONObject(result)
                    val currentWeather=responseObject.getJSONArray("weather").getJSONObject(0).getString("main")
                    val description=responseObject.getJSONArray("weather").getJSONObject(0).getString("description")
                    val tempInKelvin=responseObject.getJSONObject("main").getDouble("temp")
                    val tempInCelsius=tempInKelvin-273
                    val temperature=DecimalFormat("##.##").format(tempInCelsius)

                    val title = "Current Weather"
                    val message = "$currentWeather, $description with $temperature celsius"
                    val notifId = 100

                    showNotification(applicationContext,title,message,notifId)
                    jobFinished(jobParameters,false)
                } catch (e:Exception){
                    jobFinished(jobParameters,true)
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                jobFinished(jobParameters,true)
            }
        })
    }

    private fun showNotification(
        applicationContext: Context?,
        title: String,
        message: String,
        notifId: Int,
    ) {
        val CHANNEL_ID="Channel_1"
        val CHANNEL_NAME="Job scheduler channel"
        val notificationManagerCompat=applicationContext?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val alarmSound=RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val builder=NotificationCompat.Builder(applicationContext,CHANNEL_ID)
            .setContentTitle(title)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentText(message)
            //.setColor(ContextCompat.getColor(applicationContext,android.R.color.black))
            .setVibrate(longArrayOf(1000,1000,1000,1000,1000))
            .setSound(alarmSound)

        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )

        channel.enableVibration(true)
        channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)

        builder.setChannelId(CHANNEL_ID)

        notificationManagerCompat.createNotificationChannel(channel)

        val notification=builder.build()
        notificationManagerCompat.notify(notifId,notification)
    }
}
