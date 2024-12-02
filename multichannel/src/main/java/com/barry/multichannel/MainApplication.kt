package com.barry.multichannel

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
//import com.didichuxing.doraemonkit.DoKit

class MainApplication: Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
//        DoKit.Builder(this).customKits(listOf(EnvSwitchKit())).build()
    }
}