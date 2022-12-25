package com.example.playground.activities

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_text_links.*

class TextLinks : AppCompatActivity() {
    private val longTexts="All you need to visit stackoverflow.com in case of stucked between bugs, or ask seniors on subreddits at reddit.com, or READ DOCUMENTATIONS :)"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_links)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        clickableLinks(longTexts)
    }

    private fun clickableLinks(longTexts: String) {
            try {
                val spanned= SpannableString(longTexts)
                val matcher= Patterns.WEB_URL.matcher(longTexts) // where WEB_URL is a builtin regex function for grabbing out the Website
                var matchStart:Int
                var matchEnd:Int // both are for tracking cursor or index

                while(matcher.find()){ // enters if string contains any URL
                    matchStart=matcher.start(1)
                    matchEnd=matcher.end()

                    var url=longTexts.substring(matchStart,matchEnd)
                    if(!url.startsWith("http://") && !url.startsWith("https://"))
                        url="https:$url"

                    val clickableSpan:ClickableSpan=object:ClickableSpan(){
                        override fun onClick(p0: View) {
                            val intent=Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            startActivity(intent)
                        }

                        override fun updateDrawState(ds: TextPaint) { // for customize the state of that link
                            super.updateDrawState(ds)
                            ds.color=Color.RED
                            ds.isUnderlineText=false
                        }
                    }
                    spanned.setSpan(clickableSpan,matchStart,matchEnd,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                    longtxts.text=spanned
                    longtxts.movementMethod=LinkMovementMethod.getInstance()
                }
            } catch(e:Exception) { e.printStackTrace() }
    }
}
