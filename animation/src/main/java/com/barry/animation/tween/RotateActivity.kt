package com.barry.animation.tween

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import android.widget.Button
import android.widget.TextView
import com.barry.animation.R

class RotateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rotate)
        findViewById<Button>(R.id.btn_xml).setOnClickListener {
            findViewById<TextView>(R.id.tv_demo).startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate))
        }
        findViewById<Button>(R.id.btn_code).setOnClickListener {
//            val animation = RotateAnimation(0f, 360f);
            val animation = RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.repeatCount = -1
            animation.duration = 2000
            animation.fillAfter = true
            findViewById<TextView>(R.id.tv_demo).startAnimation(animation)
        }
    }
}