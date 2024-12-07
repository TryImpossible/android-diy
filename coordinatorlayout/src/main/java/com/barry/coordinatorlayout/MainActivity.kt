package com.barry.coordinatorlayout

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.barry.coordinatorlayout.behavior.MoveBehaviorActivity
import com.barry.coordinatorlayout.behavior.ScrollerBehaviorActivity
import com.barry.coordinatorlayout.behavior.ZoomBehaviorActivity
import com.barry.coordinatorlayout.databinding.ActivityMainBinding
import com.barry.coordinatorlayout.scrollFlags.*
import com.barry.coordinatorlayout.zhihu.ZhiHuActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        binding.btnScroll.setOnClickListener(this)
        binding.btnSnap.setOnClickListener(this)
        binding.btnEnterAlways.setOnClickListener(this)
        binding.btnEnterAlwaysCollapsed.setOnClickListener(this)
        binding.btnExitUntilCollapsed.setOnClickListener(this)
        binding.btnCollapsingToolbarLayout.setOnClickListener(this)
        binding.btnMoveBehavior.setOnClickListener(this)
        binding.btnScrollerBehavior.setOnClickListener(this)
        binding.btnZoomBehavior.setOnClickListener(this)
        binding.btnZhihu.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_scroll -> startActivity(Intent(this, ScrollActivity().javaClass))
            R.id.btn_snap -> startActivity(Intent(this, SnapActivity().javaClass))
            R.id.btn_enter_always -> startActivity(Intent(this, EnterAlwaysActivity().javaClass))
            R.id.btn_enter_always_collapsed -> startActivity(Intent(this, EnterAlwaysCollapsedActivity().javaClass))
            R.id.btn_exit_until_collapsed -> startActivity(Intent(this, ExitUntilCollapsedActivity().javaClass))
            R.id.btn_collapsingToolbarLayout -> startActivity(Intent(this, CollapsingToolbarLayoutActivity().javaClass))
            R.id.btn_move_behavior -> startActivity(Intent(this, MoveBehaviorActivity().javaClass))
            R.id.btn_scroller_behavior -> startActivity(Intent(this, ScrollerBehaviorActivity::class.java))
            R.id.btn_zoom_behavior -> startActivity(Intent(this, ZoomBehaviorActivity().javaClass))
            R.id.btn_zhihu -> startActivity(Intent(this, ZhiHuActivity().javaClass))
        }
    }
}
