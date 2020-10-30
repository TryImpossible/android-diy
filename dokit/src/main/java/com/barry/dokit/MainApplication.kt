package com.barry.dokit

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.didichuxing.doraemonkit.DoraemonKit
import com.didichuxing.doraemonkit.kit.AbstractKit

class MainApplication: Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        val kits = mutableListOf<AbstractKit>();
        kits.add(EnvSwitchKit())
        DoraemonKit.install(this, kits)
    }
}