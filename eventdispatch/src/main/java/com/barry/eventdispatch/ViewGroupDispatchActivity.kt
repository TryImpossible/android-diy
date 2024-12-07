package com.barry.eventdispatch

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.barry.eventdispatch.databinding.ActivityViewGroupDispatchBinding

class ViewGroupDispatchActivity : AppCompatActivity() {

    private var TAG: String = ViewGroupDispatchActivity::class.java.simpleName
    private lateinit var binding: ActivityViewGroupDispatchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewGroupDispatchBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_view_group_dispatch)

        // 1.为viewgroup布局设置监听事件
        binding.llContainer.setOnClickListener {
            Log.d(TAG, "点击了ViewGroup")
        }

        // 2.为按钮1设置监听事件
        binding.button1.setOnClickListener {
            Log.d(TAG, "点击了button1")
        }

        // 3.为按钮2设置监听事件
        binding.button2.setOnClickListener {
            Log.d(TAG, "点击了button2")
        }

        /// 点击Button时，执行Button.onClick()，但ViewGroupLayout注册的onTouch（）不会执行
        /// 只有点击空白区域时，才会执行ViewGroupLayout的onTouch（）
        /// 结论：Button的onClick()将事件消费掉了，因此事件不会再继续向下传递。
    }
}
