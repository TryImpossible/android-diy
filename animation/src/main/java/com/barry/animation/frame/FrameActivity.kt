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

        val imageView = findViewById<ImageView>(R.id.iv_image);
        imageView.setBackgroundResource(R.drawable.frame_animation)

        findViewById<Button>(R.id.btn_start).setOnClickListener {
            (imageView.background as AnimationDrawable).start()
        }
    }
}