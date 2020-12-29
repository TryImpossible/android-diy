package com.barry.animation.layouttransition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import com.barry.animation.R

class DefaultLayoutTransitionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_default_layout_transition)

        val mContainer = findViewById<LinearLayout>(R.id.ll_container)
        val mBtnAdd = findViewById<Button>(R.id.btn_add)
        val mBtnRemove = findViewById<Button>(R.id.btn_remove)

        mBtnAdd.setOnClickListener {
            var button = Button(this)
            button.setPadding(20, 20, 20, 20)
            button.text = "tempBtn"
            mContainer.addView(button)
        }

        mBtnRemove.setOnClickListener {
            val count = mContainer.childCount;
            if (count >= 3) {
                mContainer.removeViewAt(2)
            }
        }
    }
}