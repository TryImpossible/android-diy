package com.barry.animation.scene_transition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.transition.ChangeBounds
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.transition.TransitionManager
import com.barry.animation.R

class SceneTransition4Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scene_transition4)

        var toggle = false
        val mView = findViewById<View>(R.id.view)
        val mContainer =  findViewById<ConstraintLayout>(R.id.cl_container);
        mContainer.setOnClickListener {
            val transition = ChangeBounds()
            transition.duration = 1000
            TransitionManager.beginDelayedTransition(mContainer, transition)
            if (toggle) {
                val params = mView.layoutParams;
                params.height = 50
                params.width = 50
                mView.layoutParams = params
            } else {
                val params = mView.layoutParams;
                params.height = 200
                params.width = 200
                mView.layoutParams = params
            }
            toggle = !toggle
        }

    }
}