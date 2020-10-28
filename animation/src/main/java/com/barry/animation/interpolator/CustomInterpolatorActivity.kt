package com.barry.animation.interpolator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.view.animation.Interpolator
import android.widget.Button
import android.widget.ImageView
import com.barry.animation.R

class CustomInterpolatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_interpolator)

        val imageView = findViewById<ImageView>(R.id.iv_image);
        val animation = AnimationUtils.loadAnimation(this, R.anim.translate_interpolator);

        findViewById<Button>(R.id.btn_custom).setOnClickListener {
            animation.interpolator = CustomInterpolator()
            imageView.startAnimation(animation)
        }
    }

    inner class CustomInterpolator : Interpolator {
        override fun getInterpolation(input: kotlin.Float): kotlin.Float {
            if (input > 0 && input < 0.5) {
                return input / 10;
            } else {
                return input * input
            }
        }
    }
}