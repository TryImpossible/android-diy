package com.barry.animation.scene_transition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.transition.Fade
import androidx.transition.Scene
import androidx.transition.TransitionManager
import com.barry.animation.R

class SceneTransition2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scene_transition2)

        val mContainer = findViewById<FrameLayout>(R.id.fl_container)
        val scene1 = Scene(mContainer, findViewById<View>(R.id.view1))
        val scene2 = Scene(mContainer, findViewById<View>(R.id.view2))

        var toggle = false;
        mContainer.setOnClickListener {
            if (toggle) {
                val transition = Fade()
                transition.duration = 1000
                TransitionManager.go(scene1, transition)
            } else {
                val transition = Fade()
                transition.duration = 1000
                TransitionManager.go(scene2, transition)
            }
            toggle = !toggle
        }

        TransitionManager.go(scene1)
    }
}