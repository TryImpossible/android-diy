package com.barry.animation.tween

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.TextView
import com.barry.animation.R

class TranslateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translate)

        findViewById<Button>(R.id.btn_xml).setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.translate);
            findViewById<TextView>(R.id.tv_demo).startAnimation(animation);
        }

        findViewById<Button>(R.id.btn_code).setOnClickListener {
            val animation = TranslateAnimation(0.0f, 400f, 0f, 200f)
            animation.duration = 2000
            animation.fillAfter = true

            findViewById<TextView>(R.id.tv_demo).startAnimation(animation);
        }
    }
}