package com.barry.animation.sharedelement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import com.barry.animation.R

class ASharedElementTransitonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a_shared_element_transiton)

        val imageView = findViewById<ImageView>(R.id.iv_image)
        imageView.setOnClickListener {
            val bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this, imageView, "shareElement").toBundle()
            val intent = Intent(this, BSharedElementTransitonActivity().javaClass)
            startActivity(intent, bundle)
        }
    }
}