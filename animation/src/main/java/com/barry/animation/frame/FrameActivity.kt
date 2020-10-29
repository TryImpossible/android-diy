package com.barry.animation.frame

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat
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
            animationDrawable.isOneShot = true
            ContextCompat.getDrawable(this, R.mipmap.g1)?.let { it1 -> animationDrawable.addFrame(it1, 200) }
            ContextCompat.getDrawable(this, R.mipmap.g2)?.let { it1 -> animationDrawable.addFrame(it1, 200) }
            ContextCompat.getDrawable(this, R.mipmap.g3)?.let { it1 -> animationDrawable.addFrame(it1, 200) }
            ContextCompat.getDrawable(this, R.mipmap.g4)?.let { it1 -> animationDrawable.addFrame(it1, 200) }
            ContextCompat.getDrawable(this, R.mipmap.g5)?.let { it1 -> animationDrawable.addFrame(it1, 200) }
            ContextCompat.getDrawable(this, R.mipmap.g6)?.let { it1 -> animationDrawable.addFrame(it1, 200) }
            ContextCompat.getDrawable(this, R.mipmap.g7)?.let { it1 -> animationDrawable.addFrame(it1, 200) }
            ContextCompat.getDrawable(this, R.mipmap.g8)?.let { it1 -> animationDrawable.addFrame(it1, 200) }
            ContextCompat.getDrawable(this, R.mipmap.g9)?.let { it1 -> animationDrawable.addFrame(it1, 200) }
            ContextCompat.getDrawable(this, R.mipmap.g10)?.let { it1 -> animationDrawable.addFrame(it1, 200) }
            ContextCompat.getDrawable(this, R.mipmap.g11)?.let { it1 -> animationDrawable.addFrame(it1, 200) }
            imageView.background = animationDrawable
            animationDrawable.start();
        }
    }
}