package com.barry.coordinatorlayout.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout

class ScrollerViewBehavior(context: Context?, attrs: AttributeSet?) : CoordinatorLayout.Behavior<View>(context, attrs) {

    /**
     * 是否需要处理滑动
     *
     * 当手指按下屏幕的时候触发，用来决定是否要让Behavior处理这次滑动，true为处理，false为不处理，
     * 如果不处理，那么Behavior的后续方法也就不会在再调用了，
     * 方法中也提供了一些辅助参数，比如type，可以用来判断用户动作，比如是TYPE_TOUCH按住屏幕拖动，TYPE_NON_TOUCH快速拉动屏幕等
     */
    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: View, directTargetChild: View, target: View, axes: Int, type: Int): Boolean {
//        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type)
        return true;
    }

    /**
     * 在Behavior处理这次滑动前调用（onStartNestedScroll返回true），可以在这里做一些初始化操作
     */
    override fun onNestedScrollAccepted(coordinatorLayout: CoordinatorLayout, child: View, directTargetChild: View, target: View, axes: Int, type: Int) {
        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, axes, type)
    }

    /**
     *
     * 滑动即将开始，这个方法有个参数 int[] consumed，可以用来表示做了多少位移，
     * 假设用户滑动了100px，你做了 90px 的位移，那么就需要把 consumed[1] 改成 90（下标 0、1 分别对应 x、y 轴），这样就可以让后续的方法去处理这10px
     */
    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: View, target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
    }

    /**
     * 具体处理滑动
     *
     * 上一个方法结束后，剩下的滑动位移（dxUnconsumed、dyUnconsumed）未处理的，可以在这里处理
     */
    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: View, target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type)
        val offsetY = target.scrollY;
        child.scrollY = offsetY
    }

    /**
     * 当用户快速滑动屏幕，产生惯性滑动的时候，会触发此方法，这个方法参数中提供了滑动方向与速度
     */
    override fun onNestedFling(coordinatorLayout: CoordinatorLayout, child: View, target: View, velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed)
    }

    /**
     * 滑动停止的时候调用，如果没有发生惯性滑动，那么会直接到这个方法
     */
    override fun onStopNestedScroll(coordinatorLayout: CoordinatorLayout, child: View, target: View, type: Int) {
        super.onStopNestedScroll(coordinatorLayout, child, target, type)
    }
}