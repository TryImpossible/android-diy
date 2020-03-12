package com.barry.coordinatorlayout.behavior

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.barry.coordinatorlayout.R
import com.google.android.material.appbar.AppBarLayout

class AppbarZoomBehavior(context: Context, attrs: AttributeSet) : AppBarLayout.Behavior(context, attrs) {

    companion object {
        private const val MAX_ZOOM_HEIGHT: Float = 500f; // 放大最大高度
    }

    private var mImageView: ImageView? = null
    private var mAppbarHeight: Int? = null // AppbarLayout原始高度
    private var mImageViewHeight: Int? = null // ImageView原始高度

    private var mTotalDy: Float = 0f // 手指在Y轴滑动的距离
    private var mScaleValue: Float = 0f // 图片缩放比例
    private var mLastBottom: Int = 0 // Appbar的变化高度

    private var mValueAnimator: ValueAnimator? = null
    private var mIsAnimate: Boolean = false // 是否做动画标志

    override fun onLayoutChild(parent: CoordinatorLayout, abl: AppBarLayout, layoutDirection: Int): Boolean {
        val handled: Boolean = super.onLayoutChild(parent, abl, layoutDirection)
        init(abl)
        return handled
    }

    /**
     * 初始化操作
     * 获取图片引用、Appbar高度
     */
    fun init(abl: AppBarLayout) {
        abl.clipChildren = false
        mAppbarHeight = abl.height
        mImageView = abl.findViewById(R.id.image) as ImageView
        if (mImageView != null) {
            mImageViewHeight = mImageView!!.height
        }
    }

    /**
     * 是否处理嵌套滑动
     */
    override fun onStartNestedScroll(parent: CoordinatorLayout, child: AppBarLayout, directTargetChild: View, target: View, nestedScrollAxes: Int, type: Int): Boolean {
        println("onStartNestedScroll")
        mIsAnimate = true
        return true;
    }

    /**
     * 滑动处理
     */
    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: AppBarLayout, target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        println("onNestedPreScroll, ${child.bottom}, $dy, $type")
        if (mImageView != null && child.bottom >= this!!.mAppbarHeight!! && dy < 0 && type == ViewCompat.TYPE_TOUCH) {
            zoomHeaderImageView(child, dy)
        } else {
            if (mImageView != null && child.bottom > this!!.mAppbarHeight!! && dy > 0 && type == ViewCompat.TYPE_TOUCH) {
//                consumed[1] = dy
//                zoomHeaderImageView(child, dy)
            } else {
                if (mValueAnimator != null && !mValueAnimator!!.isRunning) {
                    super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
                }
            }
        }
    }

    /**
     * 对ImageView进行缩放处理
     * 对AppbarLayout进行高度设置
     */
    fun zoomHeaderImageView(abl: AppBarLayout, dy: Int) {
        mTotalDy += -dy
        mTotalDy = Math.min(mTotalDy, Companion.MAX_ZOOM_HEIGHT)
        mScaleValue = Math.max(1f, 1f + mTotalDy / Companion.MAX_ZOOM_HEIGHT)
        mImageView!!.scaleX = mScaleValue
        mImageView!!.scaleY = mScaleValue
        mLastBottom = mAppbarHeight!! + (mImageViewHeight!! / 3 * (mScaleValue - 1)).toInt()
        abl.bottom = mLastBottom
    }

    /**
     * 处理惯性滑动
     */
    override fun onNestedPreFling(coordinatorLayout: CoordinatorLayout, child: AppBarLayout, target: View, velocityX: Float, velocityY: Float): Boolean {
        println("onNestedPreFling：${velocityY}")
        if (velocityY > 100) {
            mIsAnimate = false
        }
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY)
    }

    /**
     * 滑动停止的时候，恢复AppbarLayout、ImageView的原始状态
     */
    override fun onStopNestedScroll(coordinatorLayout: CoordinatorLayout, abl: AppBarLayout, target: View, type: Int) {
        println("onStopNestedScroll")
        reset(abl)
        super.onStopNestedScroll(coordinatorLayout, abl, target, type)
    }

    /**
     * 通过属性动画的形式，恢复AppbarLayout、ImageView的原始状态
     */
    fun reset(abl: AppBarLayout) {
        if (mTotalDy > 0) {
            mTotalDy = 0f
            if (mIsAnimate) {
                mValueAnimator = ValueAnimator.ofFloat(mScaleValue, 1f).setDuration(200)
                mValueAnimator?.addUpdateListener {
                    val value: Float = it.animatedValue as Float
                    mImageView!!.scaleX = value
                    mImageView!!.scaleY = value
                    abl.bottom = (mLastBottom - (mLastBottom - mAppbarHeight!!) * it.animatedFraction).toInt()
                }
            }
            mValueAnimator?.start()
        } else {
            mImageView!!.scaleX = 1f
            mImageView!!.scaleY = 1f
//            abl.bottom = mAppbarHeight!!
        }
    }
}