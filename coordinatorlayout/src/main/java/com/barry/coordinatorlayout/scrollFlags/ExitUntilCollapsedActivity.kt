package com.barry.coordinatorlayout.scrollFlags

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.barry.coordinatorlayout.R

class ExitUntilCollapsedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exit_until_collapsed)


        // 当给AppBarLayout设置app:layout_scrollFlags=“scroll|exitUntilCollapsed”，
        // 同时顶部栏的Toolbar设置minHeight属性时。当RecyclerView向上滚动至顶部栏的最小高度后，AppBarLayout不在收缩。
        // 下拉时，RecyclerView没有到达最顶部时，只显示顶部栏最小高度
    }
}
