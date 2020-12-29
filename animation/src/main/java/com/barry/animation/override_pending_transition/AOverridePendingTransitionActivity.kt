package com.barry.animation.override_pending_transition

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.barry.animation.R

class AOverridePendingTransitionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a_override_pending_transition)

        findViewById<Button>(R.id.btn_enter1).setOnClickListener {
            startActivity(Intent(this, BOverridePendingTransitionActivity().javaClass))
            overridePendingTransition(R.anim.translate_left_in, 0)
        }

        findViewById<Button>(R.id.btn_enter2).setOnClickListener {
            startActivity(Intent(this, BOverridePendingTransitionActivity().javaClass))
            overridePendingTransition(R.anim.translate_left_in, R.anim.translate_out2)
        }

        findViewById<Button>(R.id.btn_enter3).setOnClickListener {
            startActivity(Intent(this, BOverridePendingTransitionActivity().javaClass))
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out)
        }
    }
}