package com.barry.eventdispatch

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.barry.eventdispatch.databinding.ActivityViewDispatchBinding

class ViewDispatchActivity : AppCompatActivity() {

    private val TAG: String = ViewDispatchActivity::class.java.simpleName
    private lateinit var binding: ActivityViewDispatchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewDispatchBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_view_dispatch)

        /**
         * 结论验证1：在回调onTouch()里返回false
         */
        // 1. 通过onTouchListener()复写onTouch()，从而手动设置返回false
        binding.button1.setOnTouchListener { v, event ->
            Log.d(TAG, "执行了onTouch()，动作是：${event.action}")
            false
        }

        // 2. 通过 OnClickListener（）为控件设置点击事件，为mOnClickListener变量赋值（即不为空），从而往下回调onClick（）
        binding.button1.setOnClickListener {
            Log.d(TAG, "执行了onClick()")
        }


        /**
         * 结论验证2：在回调onTouch()里返回true
         */
        // 1.通过onTouchListener()里复写onTouch()，手动设置返回true
        binding.button2.setOnTouchListener { v, event ->
            Log.d(TAG, "执行了onTouch()，动作是：${event.action}")
            true
        }

        // 2.通过 onClickListener()为控件设置点击事件，为mOnClickListener变量赋值（即不为空）
        // 但由于dispatchTouchEvent()返回true，即事件不再向下传递，故不调用 onClick()
        binding.button2.setOnClickListener {
            Log.d(TAG, "执行了onClick()")
        }
    }
}
