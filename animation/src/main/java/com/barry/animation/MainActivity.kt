package com.barry.animation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.barry.animation.frame.FrameActivity
import com.barry.animation.interpolator.CustomInterpolatorActivity
import com.barry.animation.interpolator.OfficialInterpolatorActivity
import com.barry.animation.tween.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_frame).setOnClickListener {
            startActivity(Intent(this, FrameActivity().javaClass))
        }
        findViewById<Button>(R.id.btn_translate).setOnClickListener {
            startActivity(Intent(this, TranslateActivity().javaClass))
        }
        findViewById<Button>(R.id.btn_alpha).setOnClickListener {
            startActivity(Intent(this, AlphaActivity().javaClass))
        }
        findViewById<Button>(R.id.btn_scale).setOnClickListener {
            startActivity(Intent(this, ScaleActivity().javaClass))
        }
        findViewById<Button>(R.id.btn_rotate).setOnClickListener {
            startActivity(Intent(this, RotateActivity().javaClass))
        }
        findViewById<Button>(R.id.btn_ani_set).setOnClickListener {
            startActivity(Intent(this, AniSetActivity().javaClass))
        }
        findViewById<Button>(R.id.btn_interpolator).setOnClickListener {
            startActivity(Intent(this, OfficialInterpolatorActivity().javaClass))
        }
        findViewById<Button>(R.id.btn_custom_interpolator).setOnClickListener {
            startActivity(Intent(this, CustomInterpolatorActivity().javaClass))
        }
    }
}