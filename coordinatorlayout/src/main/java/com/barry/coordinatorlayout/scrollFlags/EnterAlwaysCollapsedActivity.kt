package com.barry.coordinatorlayout.scrollFlags

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.barry.coordinatorlayout.R

class EnterAlwaysCollapsedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_always_collapsed)

        // 当给AppBarLayout设置app:layout_scrollFlags=“scroll|enterAlways|enterAlwaysCollapsed”，
        // 同时顶部栏的Toolbar设置minHeight属性时，下拉后会先显示最小高度，到顶部时显示完全。
        // （enterAlwaysCollapsed一般是配合enterAlways一起使用的，同时一定要给Toolbar设置最小高度(minheight)，
        // 不过Toolbar默认最小高度就是ActionBar的高度…）
    }
}
