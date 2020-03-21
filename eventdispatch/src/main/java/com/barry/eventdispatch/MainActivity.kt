package com.barry.eventdispatch

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_viewgroup_dispatch.setOnClickListener {
            startActivity(Intent(this, ViewGroupDispatchActivity().javaClass))
        }
        btn_view_dispatch.setOnClickListener {
            startActivity(Intent(this, ViewDispatchActivity().javaClass))
        }
    }
}
