package com.barry.animation.scene_transition

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import android.transition.ChangeBounds
import android.transition.ChangeClipBounds
import android.transition.ChangeImageTransform
import android.transition.ChangeTransform
import com.barry.animation.R

class ASceneTransitionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // 代码实现
//        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
//        window.enterTransition = Explode()
//        window.exitTransition = Fade()
//        window.returnTransition = TransitionInflater.from(this).inflateTransition(R.transition.explode)
//        window.reenterTransition = TransitionInflater.from(this).inflateTransition(android.R.transition.slide_bottom)

        // explode效果
//        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
//        window.enterTransition = Explode()
//        window.exitTransition = Explode()
//        window.returnTransition = TransitionInflater.from(this).inflateTransition(R.transition.explode)
//        window.reenterTransition = TransitionInflater.from(this).inflateTransition(android.R.transition.explode)

        // fade效果
//        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
//        window.enterTransition = Fade()
//        window.exitTransition = Fade()
//        window.returnTransition = TransitionInflater.from(this).inflateTransition(R.transition.fade)
//        window.reenterTransition = TransitionInflater.from(this).inflateTransition(android.R.transition.fade)

        // solid效果
//        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
//        window.enterTransition = Slide(Gravity.BOTTOM)
//        window.exitTransition = Slide(Gravity.TOP)
//        window.returnTransition = TransitionInflater.from(this).inflateTransition(R.transition.slide)
//        window.reenterTransition = TransitionInflater.from(this).inflateTransition(android.R.transition.slide_bottom)


        // 共享元素动画
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        window.sharedElementsUseOverlay = true
        val changeBounds = ChangeBounds()
        changeBounds.duration = 1000
        val changeClipBounds = ChangeClipBounds()
        changeClipBounds.duration = 1000
        val changeImageTransform = ChangeImageTransform()
        changeImageTransform.duration = 1000
        val changeTransform = ChangeTransform()
        changeTransform.duration = 1000
        window.sharedElementExitTransition = changeBounds
        window.sharedElementExitTransition = changeClipBounds
        window.sharedElementReenterTransition = changeImageTransform
        window.sharedElementReturnTransition = changeTransform

        //super.onCreate之前
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a_scene_transition)

        findViewById<Button>(R.id.btn_enter1).setOnClickListener {
            val activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this)
            val intent = Intent(this, BSceneTransitionActivity().javaClass)
            startActivity(intent, activityOptionsCompat.toBundle())
        }

        val imageView = findViewById<ImageView>(R.id.iv_image)
        findViewById<Button>(R.id.btn_enter2).setOnClickListener {
            val activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, imageView, "shareElement")
            val intent = Intent(this, BSceneTransitionActivity().javaClass)
            startActivity(intent, activityOptionsCompat.toBundle())
        }
    }
}