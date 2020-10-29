package com.barry.animation.property

import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.barry.animation.R

class CustomAnimatorPropertyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_animator_property)
        val circleView = findViewById<com.barry.animation.property.CircleView2>(R.id.circleView)

        findViewById<Button>(R.id.btn_color).setOnClickListener {
            val animator = ObjectAnimator.ofInt(circleView, "color", Color.RED, Color.GREEN)
            animator.duration = 3000
            animator.interpolator = LinearInterpolator()
            animator.start()
        }

        findViewById<Button>(R.id.btn_radius).setOnClickListener {
            val animator = ObjectAnimator.ofInt(circleView, "radius", 50, 300)
            animator.duration = 3000
            animator.interpolator = LinearInterpolator()
            animator.start()
        }

    }
}