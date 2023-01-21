package com.example.playground.activities

import android.annotation.SuppressLint
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.playground.R
import com.example.playground.utils.GetCurrentWeatherService
import kotlinx.android.synthetic.main.activity_job_scheduler.*

class JobScheduler : AppCompatActivity() {

    companion object { private const val JOB_ID = 10 }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_scheduler)

        btn_start.setOnClickListener { startJob() }
        btn_cancel.setOnClickListener { cancelJob() }
    }

    private fun cancelJob() {
        val scheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        scheduler.cancel(JOB_ID)
        Toast.makeText(this, "Job Service canceled", Toast.LENGTH_SHORT).show()
    }

    private fun startJob() {
        if(isJobRunning(this)){
            Toast.makeText(this,"Job service is already scheduled",Toast.LENGTH_SHORT).show()
            return
        }

        val mServiceComponent=ComponentName(this,GetCurrentWeatherService::class.java)
        val builder= JobInfo.Builder(JOB_ID,mServiceComponent)
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
        builder.setRequiresDeviceIdle(false)
        builder.setRequiresCharging(false)

        builder.setPeriodic(900000)

        val scheduler=getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        scheduler.schedule(builder.build())
        Toast.makeText(this,"Job service started",Toast.LENGTH_SHORT).show()
    }

    private fun isJobRunning(context: Context): Boolean {
        var isScheduled=false
        val scheduler=context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        for(jobInfo in scheduler.allPendingJobs){
            if(jobInfo.id == JOB_ID){
                isScheduled=true
                break
            }
        }
        return isScheduled
    }
}
