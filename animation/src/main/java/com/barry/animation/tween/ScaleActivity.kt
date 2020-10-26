package com.barry.animation.tween

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.ScaleAnimation
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.barry.animation.R

class ScaleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scale)
        findViewById<Button>(R.id.btn_xml).setOnClickListener {
            findViewById<TextView>(R.id.tv_demo).startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale))
        }
        findViewById<Button>(R.id.btn_code).setOnClickListener {
//            val animation = ScaleAnimation(1f, 2f, 1f, 2f);
            val animation = ScaleAnimation(1f, 2f, 1f, 2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.duration = 2000
            animation.fillAfter = true
            findViewById<TextView>(R.id.tv_demo).startAnimation(animation)
        }
    }
}