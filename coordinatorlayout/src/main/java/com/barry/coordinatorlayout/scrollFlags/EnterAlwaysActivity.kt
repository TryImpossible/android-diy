package com.barry.coordinatorlayout.scrollFlags

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.barry.coordinatorlayout.R

class EnterAlwaysActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_always)


        // enterAlways：AppBarLayout在下拉时直接显示，并不会考虑RecyclerView滚动的位置
    }
}
