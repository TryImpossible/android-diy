package com.barry.animation.tween

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.layoutDirection
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.barry.animation.R
import java.util.Locale

class TranslateDirectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_translate_direction)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.btn_direction).setOnClickListener {
            changeDirection()
        }

        findViewById<Button>(R.id.btn_play).setOnClickListener {
            playAnimation()
        }
    }

    private fun changeDirection() {
        val config = Configuration(resources.configuration)

        val button: Button = findViewById(R.id.btn_direction)
        if (resources.configuration.layoutDirection == View.LAYOUT_DIRECTION_LTR) {
            config.setLayoutDirection(Locale("ar"))
            button.text = "RTL（从右到左）"
        } else if (resources.configuration.layoutDirection == View.LAYOUT_DIRECTION_RTL) {
            config.setLayoutDirection(Locale("en"))
            button.text = "LTR（从左到右）"
        }
        resources.updateConfiguration(config, resources.displayMetrics)
        recreate();
    }

    private fun playAnimation() {
        val ivYellowOval: ImageView = findViewById(R.id.iv_yellow_oval)

        val isRTL = resources.configuration.layoutDirection == View.LAYOUT_DIRECTION_RTL

        val startX = if (isRTL) 0f else 0f
        val endX = if (isRTL) -1f else 1f

        val animation = TranslateAnimation(
            Animation.RELATIVE_TO_PARENT, startX,
            Animation.RELATIVE_TO_PARENT, endX,
            Animation.RELATIVE_TO_PARENT, 0f,
            Animation.RELATIVE_TO_PARENT, 0f
        )
        animation.duration = 3000
        animation.interpolator = LinearInterpolator()
        animation.repeatMode = Animation.REVERSE
        animation.repeatCount = Animation.INFINITE
        animation.fillAfter = true
        ivYellowOval.startAnimation(animation)
    }
}