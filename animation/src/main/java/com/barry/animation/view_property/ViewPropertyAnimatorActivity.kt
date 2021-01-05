package com.barry.animation.view_property

import android.animation.Animator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.barry.animation.R

class ViewPropertyAnimatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_property_animator)

        val imageView = findViewById<ImageView>(R.id.iv_image)

        findViewById<Button>(R.id.btn_animator_1).setOnClickListener {
            imageView.animate().setDuration(2000).alpha(0f)
        }

        findViewById<Button>(R.id.btn_animator_2).setOnClickListener {
            val viewPropertyAnimator = imageView.animate()
            viewPropertyAnimator.setDuration(2000).translationY(300f).start()
            viewPropertyAnimator.translationX(300f)
        }

        findViewById<Button>(R.id.btn_animator_3).setOnClickListener {
            imageView.animate().setDuration(3000).translationYBy(300f)
        }

        findViewById<Button>(R.id.btn_animator_4).setOnClickListener {
            imageView.animate().setDuration(3000).x(300f).y(300f)
        }

        findViewById<Button>(R.id.btn_animator_5).setOnClickListener {
            imageView.animate().setDuration(2000).rotation(270f)
        }

        findViewById<Button>(R.id.btn_animator_6).setOnClickListener {
            imageView.animate().setDuration(3000).x(700f).y(700f).rotation(270f).alpha(0.5f)
                    .withEndAction {
                        println("=========withEndAction=======");
                    }.withStartAction {
                        println("=========withStartAction=======");
                    }.setUpdateListener {

                    }.setListener(object : Animator.AnimatorListener {
                        override fun onAnimationRepeat(animation: Animator?) {
                            TODO("Not yet implemented")
                        }

                        override fun onAnimationEnd(animation: Animator?) {
                            println("=========onAnimationEnd=======");
                        }

                        override fun onAnimationCancel(animation: Animator?) {
                            TODO("Not yet implemented")
                        }

                        override fun onAnimationStart(animation: Animator?) {
                            println("=========onAnimationStart=======");
                        }

                    })
        }
    }
}