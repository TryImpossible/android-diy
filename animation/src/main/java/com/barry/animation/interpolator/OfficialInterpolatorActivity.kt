package com.barry.animation.interpolator

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.*
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.animation.PathInterpolatorCompat
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import com.barry.animation.R

class OfficialInterpolatorActivity : AppCompatActivity() {
    private var imageView: ImageView? = null
    private var animation: Animation? = null
    private val btnClickListener: View.OnClickListener = View.OnClickListener {
        if (imageView?.animation != null && imageView?.animation?.hasEnded()!!) {
            imageView?.animation?.cancel()
        }
        when (it.id) {
            R.id.btn_accelerate -> {
                animation?.interpolator = AccelerateInterpolator()
                imageView?.startAnimation(animation)
            }
            R.id.btn_decelerate -> {
                animation?.interpolator = DecelerateInterpolator()
                imageView?.startAnimation(animation)
            }
            R.id.btn_accelerate_decelerate -> {
                animation?.interpolator = AccelerateDecelerateInterpolator()
                imageView?.startAnimation(animation)
            }
            R.id.btn_linear -> {
                animation?.interpolator = LinearInterpolator()
                imageView?.startAnimation(animation)
            }
            R.id.btn_anticipate -> {
                animation?.interpolator = AnticipateInterpolator()
                imageView?.startAnimation(animation)
            }
            R.id.btn_overshoot -> {
                animation?.interpolator = OvershootInterpolator()
                imageView?.startAnimation(animation)
            }
            R.id.btn_anticipate_overshoot -> {
                animation?.interpolator = AnticipateOvershootInterpolator()
                imageView?.startAnimation(animation)
            }
            R.id.btn_bounce -> {
                animation?.interpolator = BounceInterpolator()
                imageView?.startAnimation(animation)
            }
            R.id.btn_cycle -> {
                animation?.interpolator = CycleInterpolator(5.0f)
                imageView?.startAnimation(animation)
            }
            R.id.btn_path -> {
                animation?.interpolator = PathInterpolatorCompat.create(0.9f, 0.1f)
                imageView?.startAnimation(animation)
            }
            R.id.btn_fastOutLinearIn -> {
                animation?.interpolator = FastOutLinearInInterpolator()
                imageView?.startAnimation(animation)
            }
            R.id.btn_fastOutSlowInIn -> {
                animation?.interpolator = FastOutLinearInInterpolator()
                imageView?.startAnimation(animation)
            }
            R.id.btn_linearOutSlowIn -> {
                animation?.interpolator = LinearOutSlowInInterpolator()
                imageView?.startAnimation(animation)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_official_interpolator)

        imageView = findViewById<ImageView>(R.id.iv_image)

        animation = AnimationUtils.loadAnimation(this, R.anim.translate_interpolator);

        findViewById<Button>(R.id.btn_accelerate).setOnClickListener(btnClickListener)
        findViewById<Button>(R.id.btn_decelerate).setOnClickListener(btnClickListener)
        findViewById<Button>(R.id.btn_accelerate_decelerate).setOnClickListener(btnClickListener)
        findViewById<Button>(R.id.btn_linear).setOnClickListener(btnClickListener)
        findViewById<Button>(R.id.btn_anticipate).setOnClickListener(btnClickListener)
        findViewById<Button>(R.id.btn_overshoot).setOnClickListener(btnClickListener)
        findViewById<Button>(R.id.btn_anticipate_overshoot).setOnClickListener(btnClickListener)
        findViewById<Button>(R.id.btn_bounce).setOnClickListener(btnClickListener)
        findViewById<Button>(R.id.btn_cycle).setOnClickListener(btnClickListener)
        findViewById<Button>(R.id.btn_path).setOnClickListener(btnClickListener)
        findViewById<Button>(R.id.btn_fastOutLinearIn).setOnClickListener(btnClickListener)
        findViewById<Button>(R.id.btn_fastOutSlowInIn).setOnClickListener(btnClickListener)
        findViewById<Button>(R.id.btn_linearOutSlowIn).setOnClickListener(btnClickListener)
    }
}