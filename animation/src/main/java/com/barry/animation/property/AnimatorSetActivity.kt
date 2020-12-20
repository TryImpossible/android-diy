package com.barry.animation.property

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.barry.animation.R

class AnimatorSetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animator_set)

        var textView1 = findViewById<TextView>(R.id.textView1)
        var textView2 = findViewById<TextView>(R.id.textView2)
        var textView3 = findViewById<TextView>(R.id.textView3)

        val objectAnimator1 = ObjectAnimator.ofArgb(textView1, "backgroundColor", Color.WHITE, Color.GREEN)
        val objectAnimator2 = ObjectAnimator.ofFloat(textView1, "scaleX", 0.1f, 1.2f)
        val objectAnimator3 = ObjectAnimator.ofFloat(textView1, "scaleY", 0.5f, 1.0f)
        val objectAnimator4 = ObjectAnimator.ofFloat(textView1, "translationY", 0.0f, 250.0f)

        val objectAnimator5 = ObjectAnimator.ofArgb(textView2, "backgroundColor", Color.WHITE, Color.GREEN)
        val objectAnimator6 = ObjectAnimator.ofFloat(textView2, "scaleX", 0.1f, 1.2f)
        val objectAnimator7 = ObjectAnimator.ofFloat(textView2, "scaleY", 0.5f, 1.0f)
        val objectAnimator8 = ObjectAnimator.ofFloat(textView2, "translationY", 0.0f, 250.0f)

        val objectAnimator9 = ObjectAnimator.ofArgb(textView3, "backgroundColor", Color.WHITE, Color.GREEN)
        val objectAnimator10 = ObjectAnimator.ofFloat(textView3, "scaleX", 0.1f, 1.2f)
        val objectAnimator11 = ObjectAnimator.ofFloat(textView3, "scaleY", 0.5f, 1.0f)
        val objectAnimator12 = ObjectAnimator.ofFloat(textView3, "translationY", 0.0f, 250.0f)


        findViewById<Button>(R.id.btn_playTogether1).setOnClickListener {
            val animatorSet = AnimatorSet()
            animatorSet.playTogether(objectAnimator1, objectAnimator2, objectAnimator3, objectAnimator4)
            animatorSet.duration = 3000
            animatorSet.start()
        }

        findViewById<Button>(R.id.btn_playTogether2).setOnClickListener {
            val animatorSet = AnimatorSet()
            animatorSet.playTogether(objectAnimator1, objectAnimator2, objectAnimator3, objectAnimator4,
                    objectAnimator5, objectAnimator6, objectAnimator7, objectAnimator8,
                    objectAnimator9, objectAnimator10, objectAnimator11, objectAnimator12)
            animatorSet.duration = 3000
            animatorSet.start()
        }

        findViewById<Button>(R.id.btn_playSequentially1).setOnClickListener {
            val animatorSet = AnimatorSet()
            animatorSet.playSequentially(objectAnimator1, objectAnimator2, objectAnimator3, objectAnimator4,
                    objectAnimator5, objectAnimator6, objectAnimator7, objectAnimator8,
                    objectAnimator9, objectAnimator10, objectAnimator11, objectAnimator12)
            animatorSet.duration = 1000
            animatorSet.start()
        }

        findViewById<Button>(R.id.btn_play1).setOnClickListener {
            val animatorSet = AnimatorSet()
            animatorSet.play(objectAnimator1).with(objectAnimator7).with(objectAnimator11)
            animatorSet.duration = 1000
            animatorSet.start()
        }

        findViewById<Button>(R.id.btn_play2).setOnClickListener {
            val animatorSet = AnimatorSet()
            animatorSet.play(objectAnimator1).before(objectAnimator2)
            animatorSet.duration = 1000
            animatorSet.start()
        }

        findViewById<Button>(R.id.btn_play3).setOnClickListener {
            val animatorSet = AnimatorSet()
            animatorSet.play(objectAnimator1).after(objectAnimator2).after(1000)
            animatorSet.duration = 1000
            animatorSet.start()
        }

        findViewById<Button>(R.id.btn_xml).setOnClickListener {
            val animatorSet = AnimatorInflater.loadAnimator(this, R.animator.animator_set)
            animatorSet.setTarget(textView1)
            animatorSet.start()
        }
    }
}