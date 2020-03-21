package com.barry.coordinatorlayout.scrollFlags

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.barry.coordinatorlayout.R

class ScrollActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll)

        /// scroll：所有想滚动出屏幕的view都需要设置这个flag，没有设置这个flag的view将被固定在屏幕顶部
    }
}
