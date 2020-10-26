package com.barry.animation.tween

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import com.barry.animation.R

class AlphaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alpha)

        findViewById<Button>(R.id.btn_xml).setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
            findViewById<TextView>(R.id.tv_demo).startAnimation(animation);
        }

        findViewById<Button>(R.id.btn_code).setOnClickListener {
            val animation = AlphaAnimation(1f, 0f);
            animation.duration = 2000
            animation.fillAfter = true

            findViewById<TextView>(R.id.tv_demo).startAnimation(animation);
        }
    }
}