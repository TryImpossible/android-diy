package com.barry.coordinatorlayout.behavior

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.barry.coordinatorlayout.R
import com.barry.coordinatorlayout.databinding.ActivityMoveBehaviorBinding

class MoveBehaviorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMoveBehaviorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoveBehaviorBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_move_behavior)

        binding.tvLayoutDependency.setOnClickListener {
            ViewCompat.offsetTopAndBottom(it, 30);
        }
    }
}
