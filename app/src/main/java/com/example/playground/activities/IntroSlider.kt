package com.example.playground.activities

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.playground.R
import com.example.playground.utils.PrefManager
import kotlinx.android.synthetic.main.activity_intro_slider.*

class IntroSlider : AppCompatActivity() {
    private var myViewPagerAdapter: MyViewPagerAdapter? = null
    private var dotsLayout: LinearLayout? = null
    private var layouts: IntArray? = null
    private var prefManager: PrefManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_slider)
        supportActionBar?.hide()

        prefManager= PrefManager(this)

        if(!prefManager!!.isFirstTimeLaunch()!!){
            launchLockScreen()
            finish()
        }

        // Making notification bar transparent
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        dotsLayout = findViewById(R.id.layoutDots)

        // layouts of all intro sliders
        layouts = intArrayOf(R.layout.slide1, R.layout.slide2, R.layout.slide3, R.layout.slide4)

        addBottomDots(0) // adding bottom dots

        changeStatusBarColor() // making notification bar transparent

        myViewPagerAdapter=MyViewPagerAdapter()
        view_pager!!.adapter=myViewPagerAdapter
        view_pager!!.addOnPageChangeListener(viewPagerPageChangeListener)

        btn_skip.setOnClickListener { launchLockScreen() }
        btn_next.setOnClickListener {
            val current=getItem(+1)

            if(current<layouts!!.size)
                view_pager!!.currentItem = current // move to next screen
            else
                launchLockScreen()
        }
    }

    private fun addBottomDots(currentPage: Int) {
        val dots:Array<TextView?> = arrayOfNulls(layouts!!.size)

        dotsLayout!!.removeAllViews()
        for(i in dots.indices){
            dots[i]= TextView(this)
            dots[i]!!.text= Html.fromHtml("&#8226;")
            dots[i]!!.textSize = 35f
            dots[i]!!.setTextColor(resources.getIntArray(R.array.array_dot_inactive)[currentPage])
            dotsLayout!!.addView(dots[i])
        }

        if(dots.isNotEmpty())   dots[currentPage]!!.setTextColor(resources.getColor(R.color.white))
    }

    private fun getItem(i: Int): Int {
        return view_pager!!.currentItem + i
    }

    private fun launchLockScreen() {
        prefManager!!.setLaunched(false)
        startActivity(Intent(this, LockScreen::class.java))
        finish()
    }

    private var viewPagerPageChangeListener:ViewPager.OnPageChangeListener=object : ViewPager.OnPageChangeListener{
        override fun onPageSelected(position: Int) {
            addBottomDots(position)

            //changing the next button
            if(position==layouts!!.size-1){
                // last page. make button text to GOT IT
                btn_next!!.text = getString(R.string.start)
                btn_skip!!.visibility = View.GONE
            } else {
                // still pages are left
                btn_next!!.text = getString(R.string.next)
                btn_skip!!.visibility = View.VISIBLE
            }
        }

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}

        override fun onPageScrollStateChanged(arg0: Int) {}
    }

    private fun changeStatusBarColor() {
        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
    }

    inner class MyViewPagerAdapter : PagerAdapter() {

        private var layoutInflater: LayoutInflater? = null
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            layoutInflater=getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view=layoutInflater!!.inflate(layouts!![position],container,false)
            container.addView(view)
            return view
        }

        override fun getCount(): Int {
            return layouts!!.size
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view==obj
        }

        override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
            val v=obj as View
            container.removeView(v)
        }
    }
}
