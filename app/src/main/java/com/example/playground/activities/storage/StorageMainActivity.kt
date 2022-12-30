package com.example.playground.activities.storage

import android.app.TabActivity
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.TabHost
import androidx.appcompat.app.AppCompatActivity
import com.example.playground.MainActivity2
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_storage_main.*

class StorageMainActivity : TabActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage_main)

        toolbarOfStorage.title="Store on Local device"
        toolbarOfStorage.subtitle="Writing to Memories"

        val tabHost= getTabHost()

        val cacheTab=Intent().setClass(this,CacheStorage::class.java)
        val tabSpecCache = tabHost
            .newTabSpec("Cache")
            .setContent(cacheTab)
            .setIndicator("Cache")
        tabHost.addTab(tabSpecCache)

        val internalTab=Intent().setClass(this,InternalStorage::class.java)
        val tabSpecInternal = tabHost
            .newTabSpec("Internal")
            .setContent(internalTab)
            .setIndicator("Internal")
        tabHost.addTab(tabSpecInternal)

        val externalTab=Intent().setClass(this,ExternalStorage::class.java)
        val tabSpecExternal = tabHost
            .newTabSpec("External")
            .setContent(externalTab)
            .setIndicator("External")
        tabHost.addTab(tabSpecExternal)

        val externalPublicTab=Intent().setClass(this,ExternalStoragePublicDir::class.java)
        val tabSpecExternalPub = tabHost
            .newTabSpec("External Public")
            .setContent(externalPublicTab)
            .setIndicator("External-Public")
        tabHost.addTab(tabSpecExternalPub)

        val sharedPrefTab=Intent().setClass(this,SharedPreference::class.java)
        val tabSpecSharedPref = tabHost
            .newTabSpec("Shared Preference")
            .setContent(sharedPrefTab)
            .setIndicator("Shared")
        tabHost.addTab(tabSpecSharedPref)

        tabHost.currentTab=1 // default tab
    }
}
