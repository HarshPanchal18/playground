package com.example.playground.activities.bitspizza

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ShareActionProvider
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_bits_pizza_main.*

class BitsPizzaMain : AppCompatActivity() {
    private var shareActionProvider:ShareActionProvider?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bits_pizza_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val actionBar: ActionBar? = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#f7ac34"))
        actionBar?.setBackgroundDrawable(colorDrawable)

        val pagerAdapter=SectionsAdapter(supportFragmentManager)
        pager.adapter=pagerAdapter

        tabs.setupWithViewPager(pager) // links the viewPager with tab layout
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        val menuItem=menu.findItem(R.id.action_share)
        shareActionProvider=
            (MenuItemCompat.getActionProvider(menuItem) as ShareActionProvider?)
        setShareActionIntent("Want to join me for Pizza??")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_create_order -> {
                startActivity(Intent(this,OrderActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setShareActionIntent(s: String) {
        val intent=Intent(Intent.ACTION_SEND)
        intent.type="text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,s)
        shareActionProvider?.setShareIntent(intent)
    }

    private class SectionsAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getCount(): Int { return 4 }

        override fun getItem(position: Int): Fragment {
            return when(position){
                0 -> TopFragment()
                1 -> PizzaFragment()
                2 -> PastaFragment()
                else -> StoreFragment()
            }
        }

        override fun getPageTitle(pos:Int): CharSequence? { // adds the text to the tabs
            return when(pos){
                0-> "Home"
                1-> "Pizza"
                2-> "Pasta"
                3-> "Store"
                else -> null
            }
        }
    }
}
