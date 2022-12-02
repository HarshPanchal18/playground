package com.example.playground.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.playground.R

class AdminContact : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_contact)
        supportActionBar!!.hide()

        val gitwebpage:WebView=findViewById(R.id.github_profile)
        val webSettings1:WebSettings=gitwebpage.settings
        webSettings1.javaScriptEnabled=true
        gitwebpage.webViewClient= WebViewClient()
        gitwebpage.loadUrl("https://github.com/HarshPanchal18")

        val redditwebpage:WebView=findViewById(R.id.reddit_profile)
        val webSettings2:WebSettings=redditwebpage.settings
        webSettings2.javaScriptEnabled=true
        redditwebpage.webViewClient= WebViewClient()
        redditwebpage.loadUrl("https://www.reddit.com/user/HarshPanchal_")
    }
}
