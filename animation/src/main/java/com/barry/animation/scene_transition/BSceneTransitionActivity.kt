package com.barry.animation.scene_transition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import com.barry.animation.R

class BSceneTransitionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_scene_transition)

        findViewById<Button>(R.id.btn_exit).setOnClickListener {
            ActivityCompat.finishAfterTransition(this)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        ActivityCompat.finishAfterTransition(this)
    }
}