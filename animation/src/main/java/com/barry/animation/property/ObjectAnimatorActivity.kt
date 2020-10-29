package com.barry.animation.property

import android.animation.ObjectAnimator
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.ImageView
import com.barry.animation.R

class ObjectAnimatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_object_animator)

        val imageView = findViewById<ImageView>(R.id.iv_image)

        findViewById<Button>(R.id.btn_translate).setOnClickListener{
            val animator = ObjectAnimator.ofFloat(imageView, "translationX", 100f, 200f, 300f, 400f)
            animator.duration = 3000
            animator.interpolator = LinearInterpolator()
            animator.start()
        }
        findViewById<Button>(R.id.btn_alpha).setOnClickListener{
            val animator = ObjectAnimator.ofFloat(imageView, "alpha", 0.1f, 1f)
            animator.duration = 3000
            animator.interpolator = LinearInterpolator()
            animator.start()
        }
        findViewById<Button>(R.id.btn_scale).setOnClickListener{
            val animator = ObjectAnimator.ofFloat(imageView, "scaleX",  0.1f,1.0f,2.0f,1.0f)
            animator.duration = 3000
            animator.interpolator = LinearInterpolator()
            animator.start()
        }
        findViewById<Button>(R.id.btn_rotate).setOnClickListener{
            val animator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 270f)
            animator.duration = 3000
            animator.interpolator = LinearInterpolator()
            animator.start()
        }
    }
}