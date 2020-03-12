package com.barry.coordinatorlayout.behavior

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.barry.coordinatorlayout.R
import kotlinx.android.synthetic.main.activity_move_behavior.*

class MoveBehaviorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_behavior)

        tv_layout_dependency.setOnClickListener {
            ViewCompat.offsetTopAndBottom(it, 30);
        }
    }
}
