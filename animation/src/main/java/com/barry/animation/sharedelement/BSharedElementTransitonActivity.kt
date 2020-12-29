package com.barry.animation.sharedelement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.ActivityCompat
import com.barry.animation.R

class BSharedElementTransitonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_shared_element_transiton)

        findViewById<Button>(R.id.btn_exit).setOnClickListener {
            ActivityCompat.finishAfterTransition(this)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        ActivityCompat.finishAfterTransition(this)
    }
}