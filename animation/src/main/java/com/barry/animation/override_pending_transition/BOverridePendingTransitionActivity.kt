package com.barry.animation.override_pending_transition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.barry.animation.R

class BOverridePendingTransitionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_override_pending_transition)
        findViewById<Button>(R.id.btn_exit1).setOnClickListener {
            finish()
            overridePendingTransition(0, R.anim.translate_right_out)
        }
        findViewById<Button>(R.id.btn_exit2).setOnClickListener {
            finish()
            overridePendingTransition(R.anim.translate_left_in, R.anim.translate_right_out)
        }
        findViewById<Button>(R.id.btn_exit3).setOnClickListener {
            finish()
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out)
        }
    }
}