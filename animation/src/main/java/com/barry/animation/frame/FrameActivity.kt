package com.barry.animation.frame

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.barry.animation.R

class FrameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame)

        val imageView = findViewById<ImageView>(R.id.iv_image)

        findViewById<Button>(R.id.btn_xml).setOnClickListener {
            imageView.setImageResource(R.drawable.frame_animation)
            (imageView.drawable as AnimationDrawable).start()
        }

        findViewById<Button>(R.id.btn_code).setOnClickListener {
            var animationDrawable = AnimationDrawable()
            animationDrawable.isOneShot = false
            animationDrawable.addFrame(resources.getDrawable(R.mipmap.g1), 200)
            animationDrawable.addFrame(resources.getDrawable(R.mipmap.g2), 200)
            animationDrawable.addFrame(resources.getDrawable(R.mipmap.g3), 200)
            animationDrawable.addFrame(resources.getDrawable(R.mipmap.g4), 200)
            animationDrawable.addFrame(resources.getDrawable(R.mipmap.g5), 200)
            animationDrawable.addFrame(resources.getDrawable(R.mipmap.g6), 200)
            animationDrawable.addFrame(resources.getDrawable(R.mipmap.g7), 200)
            animationDrawable.addFrame(resources.getDrawable(R.mipmap.g8), 200)
            animationDrawable.addFrame(resources.getDrawable(R.mipmap.g9), 200)
            animationDrawable.addFrame(resources.getDrawable(R.mipmap.g10), 200)
            animationDrawable.addFrame(resources.getDrawable(R.mipmap.g11), 200)
            imageView.background = animationDrawable
            animationDrawable.start();
        }
    }
}