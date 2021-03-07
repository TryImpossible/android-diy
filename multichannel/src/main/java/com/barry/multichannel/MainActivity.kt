package com.barry.multichannel

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var text = findViewById<TextView>(R.id.text)
        text.setText("env：" + BuildConfig.ENV + ", servel_url: " +  BuildConfig.SERVER_URL)
    }
}