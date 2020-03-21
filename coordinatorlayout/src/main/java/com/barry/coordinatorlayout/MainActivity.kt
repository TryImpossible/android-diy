package com.barry.coordinatorlayout

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.barry.coordinatorlayout.behavior.MoveBehaviorActivity
import com.barry.coordinatorlayout.behavior.ScrollerBehaviorActivity
import com.barry.coordinatorlayout.behavior.ZoomBehaviorActivity
import com.barry.coordinatorlayout.scrollFlags.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_scroll.setOnClickListener(this)
        btn_enter_always.setOnClickListener(this)
        btn_exit_until_collapsed.setOnClickListener(this)
        btn_enter_always_collapsed.setOnClickListener(this)
        btn_snap.setOnClickListener(this)
        btn_collapsingToolbarLayout.setOnClickListener(this)
        btn_move_behavior.setOnClickListener(this)
        btn_scroller_behavior.setOnClickListener(this)
        btn_zoom_behavior.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_scroll -> startActivity(Intent(this, ScrollActivity().javaClass))
            R.id.btn_enter_always -> startActivity(Intent(this, EnterAlwaysActivity().javaClass))
            R.id.btn_move_behavior -> startActivity(Intent(this, MoveBehaviorActivity().javaClass))
            R.id.btn_exit_until_collapsed -> startActivity(Intent(this, ExitUntilCollapsedActivity().javaClass))
            R.id.btn_snap -> startActivity(Intent(this, SnapActivity().javaClass))
            R.id.btn_collapsingToolbarLayout -> startActivity(Intent(this, CollapsingToolbarLayoutActivity().javaClass))
            R.id.btn_enter_always_collapsed -> startActivity(Intent(this, EnterAlwaysCollapsedActivity().javaClass))
            R.id.btn_scroller_behavior -> startActivity(Intent(this, ScrollerBehaviorActivity::class.java))
            R.id.btn_zoom_behavior -> startActivity(Intent(this, ZoomBehaviorActivity().javaClass))
        }
    }
}
