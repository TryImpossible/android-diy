package com.barry.animation.shimmer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.barry.animation.R
import com.facebook.shimmer.ShimmerFrameLayout

class ShimmerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN)
        supportActionBar!!.hide()
        setContentView(R.layout.activity_shimmer)
    }

    override fun onResume() {
        super.onResume()
        val shimmerView: ShimmerFrameLayout = findViewById(R.id.shimmer_view)
        shimmerView.alpha = 0f
        shimmerView.animate().setDuration(800).translationY(-240f * resources.displayMetrics.density).alpha(1f).withEndAction {
            shimmerView.startShimmer()
        }.start();
    }
}