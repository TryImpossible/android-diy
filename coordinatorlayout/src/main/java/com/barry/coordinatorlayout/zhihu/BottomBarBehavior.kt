package com.barry.coordinatorlayout.zhihu

import android.animation.Animator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.interpolator.view.animation.FastOutSlowInInterpolator

class BottomBarBehavior(context: Context, attrs: AttributeSet) : CoordinatorLayout.Behavior<View>(context, attrs) {
    private val TAG: String = BottomBarBehavior::class.java.simpleName

//    override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
//        return dependency is AppBarLayout
//    }
//
//    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
//        child.translationY = Math.abs(dependency.top.toFloat())
//        return true
//    }

    private var mLastDy: Int = 0

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: View, directTargetChild: View, target: View, axes: Int, type: Int): Boolean {
        return (axes and ViewCompat.SCROLL_AXIS_VERTICAL) !== 0
    }

    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: View, target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
        Log.d(TAG, "dy = $dy, mLastDy = $mLastDy, childHeight = ${child.height}");

        if (dy > 0 && mLastDy < 0 || dy < 0 && mLastDy > 0) {
            child.animate().cancel()
            mLastDy = 0
        }
        mLastDy += dy
        val visibility: Int = child.visibility
        if (mLastDy > child.height && visibility == View.VISIBLE) {
            hide(child)
        }
        if (mLastDy < 0 && visibility == View.INVISIBLE) {
            show(child)
        }
    }

    private fun show(child: View) {
        child.animate()
                .translationY(0f)
                .setInterpolator(FastOutSlowInInterpolator())
                .setDuration(200)
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(animation: Animator) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onAnimationEnd(animation: Animator) {
                        child.visibility = View.VISIBLE
                    }

                    override fun onAnimationCancel(animation: Animator) {
                        hide(child)
                    }

                    override fun onAnimationStart(animation: Animator) {

                    }
                }).start()
    }

    private fun hide(child: View) {
        child.animate()
                .translationY(child.height.toFloat())
                .setInterpolator(FastOutSlowInInterpolator())
                .setDuration(200)
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(animation: Animator) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onAnimationEnd(animation: Animator) {
                        child.visibility = View.INVISIBLE
                    }

                    override fun onAnimationCancel(animation: Animator) {
                        show(child)
                    }

                    override fun onAnimationStart(animation: Animator) {

                    }
                }).start()
    }
}