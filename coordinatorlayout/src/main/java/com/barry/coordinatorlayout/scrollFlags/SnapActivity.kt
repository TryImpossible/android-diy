package com.barry.coordinatorlayout.scrollFlags

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.barry.coordinatorlayout.R

class SnapActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snap)

        // snap：当一个滚动事件结束，如果视图是部分可见的，那么它将被滚动到收缩或展开。
        // 例如，如果视图只有底部25%显示，它将折叠。相反，如果它的底部75%可见，那么它将完全展开
    }
}
