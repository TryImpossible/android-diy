//package com.barry.multichannel
//
//import android.app.Activity
//import android.content.Context
//import android.content.Intent
//import android.util.Log
//import com.didichuxing.doraemonkit.kit.AbstractKit
//
//class EnvSwitchKit: AbstractKit() {
//    override val icon: Int
//        get() = R.mipmap.ic_launcher_round
//    override val name: Int
//        get() = R.string.env_name
//
//    override fun onAppInit(context: Context?) {
//    }
//
//    override fun onClickWithReturn(activity: Activity): Boolean {
//        Log.e("Barry", "点击环境切换")
//        activity.startActivity(Intent(activity, EnvSwitchActivity().javaClass))
//        return true
//    }
//
//}