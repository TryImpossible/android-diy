package com.barry.animation.scene_transition

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.ChangeBounds
import androidx.transition.Scene
import androidx.transition.TransitionManager
import com.barry.animation.R

class SceneTransition3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scene_transition3)

        val mContainer = findViewById<FrameLayout>(R.id.fl_container)
        val scene1 = Scene.getSceneForLayout(mContainer, R.layout.layout_scene_01, this)
        val scene2 = Scene.getSceneForLayout(mContainer, R.layout.layout_scene_02, this)

        var toggle = false
        mContainer.setOnClickListener {
            if (toggle) {
                val transition = ChangeBounds()
                transition.addTarget(R.id.view1)
                TransitionManager.go(scene1, transition)
            } else {
                val transition = ChangeBounds()
                transition.addTarget(R.id.view1)
                TransitionManager.go(scene2, transition)
            }
            toggle = !toggle
        }

        TransitionManager.go(scene1)
    }
}