package com.barry.animation.shared_element

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import com.barry.animation.R

class ASharedElementTransitonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        setContentView(R.layout.activity_a_shared_element_transiton)

        val imageView = findViewById<ImageView>(R.id.iv_image)
        findViewById<Button>(R.id.btn_enter1).setOnClickListener {
            val activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, imageView, "shareElement")
            val intent = Intent(this, BSharedElementTransitonActivity().javaClass)
            startActivity(intent, activityOptionsCompat.toBundle())
        }
        findViewById<Button>(R.id.btn_enter2).setOnClickListener {
            val activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(this, R.anim.translate_left_in, R.anim.translate_right_out)
            val intent = Intent(this, BSharedElementTransitonActivity().javaClass)
            startActivity(intent, activityOptionsCompat.toBundle())
        }
        findViewById<Button>(R.id.btn_enter3).setOnClickListener {
            val activityOptionsCompat = ActivityOptionsCompat.makeClipRevealAnimation(imageView, 0, 0, 72, 72)
            val intent = Intent(this, BSharedElementTransitonActivity().javaClass)
            startActivity(intent, activityOptionsCompat.toBundle())
        }
        findViewById<Button>(R.id.btn_enter4).setOnClickListener {
            val activityOptionsCompat = ActivityOptionsCompat.makeScaleUpAnimation(imageView, imageView.width / 2, imageView.height / 2, 0, 0)
            val intent = Intent(this, BSharedElementTransitonActivity().javaClass)
            startActivity(intent, activityOptionsCompat.toBundle())
        }
        findViewById<Button>(R.id.btn_enter5).setOnClickListener {
            val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher_round)
            val activityOptionsCompat = ActivityOptionsCompat.makeThumbnailScaleUpAnimation(imageView, bitmap, 72, 72)
            val intent = Intent(this, BSharedElementTransitonActivity().javaClass)
            startActivity(intent, activityOptionsCompat.toBundle())
        }
    }
}