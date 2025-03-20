package com.barry.animation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.barry.animation.circular_reveal.CircularReveal1Activity
import com.barry.animation.circular_reveal.CircularReveal2Activity
import com.barry.animation.frame.FrameActivity
import com.barry.animation.interpolator.CustomInterpolatorActivity
import com.barry.animation.interpolator.OfficialInterpolatorActivity
import com.barry.animation.layout_animation.LayoutAnimationActivity
import com.barry.animation.layout_transition.CustomLayoutTransitionActivity
import com.barry.animation.layout_transition.DefaultLayoutTransitionActivity
import com.barry.animation.override_pending_transition.AOverridePendingTransitionActivity
import com.barry.animation.property.*
import com.barry.animation.scene_transition.*
import com.barry.animation.shared_element.ASharedElementTransitonActivity
import com.barry.animation.shimmer.ShimmerActivity
import com.barry.animation.tween.*
import com.barry.animation.view_property.ViewPropertyAnimatorActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_frame).setOnClickListener {
            startActivity(Intent(this, FrameActivity().javaClass))
        }
        findViewById<Button>(R.id.btn_translate).setOnClickListener {
            startActivity(Intent(this, TranslateActivity().javaClass))
        }
        findViewById<Button>(R.id.btn_translate_direction).setOnClickListener {
            startActivity(Intent(this, TranslateDirectionActivity().javaClass))
        }
        findViewById<Button>(R.id.btn_alpha).setOnClickListener {
            startActivity(Intent(this, AlphaActivity().javaClass))
        }
        findViewById<Button>(R.id.btn_scale).setOnClickListener {
            startActivity(Intent(this, ScaleActivity().javaClass))
        }
        findViewById<Button>(R.id.btn_rotate).setOnClickListener {
            startActivity(Intent(this, RotateActivity().javaClass))
        }
        findViewById<Button>(R.id.btn_ani_set).setOnClickListener {
            startActivity(Intent(this, AniSetActivity().javaClass))
        }
        findViewById<Button>(R.id.btn_interpolator).setOnClickListener {
            startActivity(Intent(this, OfficialInterpolatorActivity().javaClass))
        }
        findViewById<Button>(R.id.btn_custom_interpolator).setOnClickListener {
            startActivity(Intent(this, CustomInterpolatorActivity().javaClass))
        }
        findViewById<Button>(R.id.btn_value_animator).setOnClickListener {
            startActivity(Intent(this, ValueAnimatorActivity().javaClass))
        }
        findViewById<Button>(R.id.btn_object_animator).setOnClickListener {
            startActivity(Intent(this, ObjectAnimatorActivity().javaClass))
        }
        findViewById<Button>(R.id.btn_custom_animator_property).setOnClickListener {
            startActivity(Intent(this, CustomAnimatorPropertyActivity().javaClass))
        }
        findViewById<Button>(R.id.btn_full_animator_property).setOnClickListener {
            startActivity(Intent(this, FullObjectAnimatorActivity().javaClass))
        }
        findViewById<Button>(R.id.btn_animator_set).setOnClickListener {
            startActivity(Intent(this, AnimatorSetActivity().javaClass))
        }
        findViewById<Button>(R.id.btn_default_layout_transition).setOnClickListener {
            startActivity(Intent(this, DefaultLayoutTransitionActivity().javaClass))
        }
        findViewById<Button>(R.id.btn_custom_layout_transition).setOnClickListener {
            startActivity(Intent(this, CustomLayoutTransitionActivity().javaClass))
        }
        findViewById<Button>(R.id.btn_share_element).setOnClickListener {
            startActivity(Intent(this, ASharedElementTransitonActivity().javaClass))
        }
        findViewById<Button>(R.id.btn_view_property_animator).setOnClickListener {
            startActivity(Intent(this, ViewPropertyAnimatorActivity().javaClass))
        }
        findViewById<Button>(R.id.btn_activity_enter_animator).setOnClickListener {
            startActivity(Intent(this, AOverridePendingTransitionActivity().javaClass))
        }
        findViewById<Button>(R.id.btn_transition_animator).setOnClickListener {
            startActivity(Intent(this, ASceneTransitionActivity().javaClass))
        }
        findViewById<Button>(R.id.btn_scene_01_animator).setOnClickListener {
            startActivity(Intent(this, SceneTransition1Activity().javaClass))
        }
        findViewById<Button>(R.id.btn_scene_02_animator).setOnClickListener {
            startActivity(Intent(this, SceneTransition2Activity().javaClass))
        }
        findViewById<Button>(R.id.btn_scene_03_animator).setOnClickListener {
            startActivity(Intent(this, SceneTransition3Activity().javaClass))
        }
        findViewById<Button>(R.id.btn_scene_04_animator).setOnClickListener {
            startActivity(Intent(this, SceneTransition4Activity().javaClass))
        }
        findViewById<Button>(R.id.btn_circular_reveal_01_animator).setOnClickListener {
            startActivity(Intent(this, CircularReveal1Activity().javaClass))
        }
        findViewById<Button>(R.id.btn_circular_reveal_02_animator).setOnClickListener {
            startActivity(Intent(this, CircularReveal2Activity().javaClass))
        }
        findViewById<Button>(R.id.btn_layout_animation).setOnClickListener {
            startActivity(Intent(this, LayoutAnimationActivity().javaClass))
        }
        findViewById<Button>(R.id.btn_shimmer).setOnClickListener {
            startActivity(Intent(this, ShimmerActivity().javaClass))
        }
    }
}