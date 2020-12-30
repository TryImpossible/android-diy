package com.barry.animation.scene_transition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.transition.ChangeBounds
import androidx.transition.Explode
import androidx.transition.Scene
import androidx.transition.TransitionManager
import com.barry.animation.R

class SceneTransition1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scene_transition)

        val mContainer = findViewById<FrameLayout>(R.id.fl_container)
//        val scene1 = Scene.getSceneForLayout(mContainer, R.layout.layout_scene_01, this)
//        val scene2 = Scene.getSceneForLayout(mContainer, R.layout.layout_scene_02, this)

        val scene1 = Scene(mContainer, LayoutInflater.from(this).inflate(R.layout.layout_scene_01, null))
        val scene2 = Scene(mContainer, LayoutInflater.from(this).inflate(R.layout.layout_scene_02, null))

        var toggle = false
        mContainer.setOnClickListener {
            if (toggle) {
//                TransitionManager.go(scene1)
//                TransitionManager.go(scene1, ChangeBounds())
                TransitionManager.go(scene1, Explode())
            } else {
//                TransitionManager.go(scene2)
//                TransitionManager.go(scene2, ChangeBounds())
                TransitionManager.go(scene2, Explode())
            }
            toggle = !toggle
        }

        TransitionManager.go(scene1)
    }
}