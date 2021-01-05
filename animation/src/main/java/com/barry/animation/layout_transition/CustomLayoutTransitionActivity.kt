package com.barry.animation.layout_transition

import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import com.barry.animation.R

class CustomLayoutTransitionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_layout_transition)

        val mContainer = findViewById<LinearLayout>(R.id.ll_container)

        setLayoutTransition(mContainer)

        findViewById<Button>(R.id.btn_add).setOnClickListener {
            var button = Button(this)
            button.setPadding(20, 20, 20, 20)
            button.text = "tempBtn"
            val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            mContainer.addView(button, 2, params)
        }
        findViewById<Button>(R.id.btn_remove).setOnClickListener {
            if (mContainer.childCount >= 3) {
                mContainer.removeViewAt(2)
            }
        }
    }

    fun setLayoutTransition(container: LinearLayout) {
        val layoutTransition = LayoutTransition()

        // 移除View时View的DISAPPEARING动画
        val addAnimator = ObjectAnimator.ofFloat(null, View.TRANSLATION_X, 0f, 50f, 0f).setDuration(1500)
        layoutTransition.setAnimator(LayoutTransition.DISAPPEARING, addAnimator)

        // 添加View时View的APPEARING动画
        val removeAnimator = ObjectAnimator.ofFloat(null, View.SCALE_X, 0.5f, 1f).setDuration(1500)
        layoutTransition.setAnimator(LayoutTransition.APPEARING, removeAnimator)

        val changeLeft = PropertyValuesHolder.ofInt("left", 0, 0)
        val changeTop = PropertyValuesHolder.ofInt("top", 0, 0)
        val changeRight = PropertyValuesHolder.ofInt("right", 0, 0)
        val changeBottom = PropertyValuesHolder.ofInt("bottom", 0, 0)

        // 添加View时，其它受影响view动画效果
        val aniChanApp = PropertyValuesHolder.ofFloat("rotation", 0f, 50f, 0f)
        val changeApp = ObjectAnimator.ofPropertyValuesHolder(this, changeLeft, changeTop, aniChanApp)
        layoutTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, changeApp)

        // 移除View时，其它受影响view动画效果
        val aniChangeDis = PropertyValuesHolder.ofFloat("rotation", 0f, 50f, 0f)
        val changeDis = ObjectAnimator.ofPropertyValuesHolder(this, changeRight, changeBottom, aniChangeDis)
        layoutTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, changeDis)

        container.layoutTransition = layoutTransition
    }
}