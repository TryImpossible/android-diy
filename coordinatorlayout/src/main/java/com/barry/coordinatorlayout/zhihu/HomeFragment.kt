package com.barry.coordinatorlayout.zhihu

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.barry.coordinatorlayout.R
import com.barry.coordinatorlayout.adpater.SingleTextAdapter
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {

        val data = List(100, { it -> "我是第${it + 1}个Item" })
        val adapter = SingleTextAdapter(requireContext(), data)

        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recycler_view.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.bottom = 2
            }

            override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
                super.onDraw(c, parent, state)
                for (i in 0 until parent.childCount - 1) {
                    val child = parent.getChildAt(i)
                    val rect = Rect(child.left, child.bottom, child.right, child.bottom + 2)
                    val paint = Paint()
                    paint.color = Color.LTGRAY
                    c.drawRect(rect, paint)
                }
            }
        })

        refresh_layout.setOnRefreshListener {
            Handler().postDelayed({
                adapter.notifyDataSetChanged()
                refresh_layout.isRefreshing = false
            }, 1000)
        }

    }
}
