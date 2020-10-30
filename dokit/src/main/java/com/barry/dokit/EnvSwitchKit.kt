package com.barry.dokit

import android.content.Context
import android.content.Intent
import android.util.Log
import com.didichuxing.doraemonkit.kit.AbstractKit

class EnvSwitchKit: AbstractKit() {
    override val icon: Int
        get() = R.mipmap.ic_launcher_round
    override val name: Int
        get() = R.string.env_name

    override fun onAppInit(context: Context?) {
    }

    override fun onClick(context: Context?) {
        Log.e("Barry", "点击环境切换")
        context?.startActivity(Intent(context, EnvSwitchActivity().javaClass))
    }

}