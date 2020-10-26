package com.barry.animation.tween

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.*
import android.widget.Button
import android.widget.TextView
import com.barry.animation.R

class AniSetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ani_set)

        findViewById<Button>(R.id.btn_xml).setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.set);
            findViewById<TextView>(R.id.tv_demo).startAnimation(animation);
        }

        findViewById<Button>(R.id.btn_code).setOnClickListener {
            val translate = TranslateAnimation(0f, 100f, 0f, 100f)
            val alpha = AlphaAnimation(0.5f, 1.0f)
            val scale = ScaleAnimation(0f, 2f, 0f, 2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
            val rotate = RotateAnimation(0f, -270f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)

            val animation = AnimationSet(true);
            animation.duration = 3000
            animation.fillAfter = true
            animation.addAnimation(translate)
            animation.addAnimation(alpha)
            animation.addAnimation(scale)
            animation.addAnimation(rotate)


            findViewById<TextView>(R.id.tv_demo).startAnimation(animation);
        }
    }
}