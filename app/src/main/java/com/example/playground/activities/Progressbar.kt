package com.example.playground.activities

import android.app.Activity
import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ProgressBar
import com.example.playground.R

class Progressbar : Activity() {
    private var btnStartProgress: Button? = null
    private var progressBar: ProgressDialog? = null
    private var progressBarStatus = 0
    private val progressBarHandler = Handler()
    private var fileSize: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progressbar)
        addListenerOnButton()

        /*val w:WebView=findViewById(R.id.wbview)
        val wSetting:WebSettings=w.settings
        wSetting.javaScriptEnabled=true
        w.webViewClient = WebViewClient()
        w.loadUrl("https://www.google.com/")
        w.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                progressBar!!.progress = newProgress
                if (newProgress == 100)
                    progressBar!!.visibility=View.GONE
                else
                    progressBar!!.progress = View.VISIBLE
            }
        }*/

    }

    private fun addListenerOnButton() {
        btnStartProgress = findViewById<View>(R.id.progressStartbtn) as Button

        btnStartProgress!!.setOnClickListener { v: View ->
            progressBar = ProgressDialog(v.context)
            progressBar!!.setCancelable(true)
            progressBar!!.setMessage("File downloading ...")
            progressBar!!.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
            // Set for Clockwise spinner
            //progressBar!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            progressBar!!.progress = 0
            progressBar!!.max = 100
            progressBar!!.show()
            progressBarStatus = 0
            fileSize = 0

            Thread {
                while (progressBarStatus < 100) {
                    progressBarStatus = doSomeTasks()
                    try {
                        Thread.sleep(1000)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    progressBarHandler.post { progressBar!!.progress = progressBarStatus }
                }

                if (progressBarStatus >= 100) {
                    try {
                        Thread.sleep(1000)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    progressBar!!.dismiss()
                }
            }.start()
        }
    }

    private fun doSomeTasks(): Int {
        while (fileSize <= 1000000) {
            fileSize++
            if (fileSize == 100000L) return 10 else if (fileSize == 200000L) return 20 else if (fileSize == 300000L) return 30
        }
        return 100
    }
}
