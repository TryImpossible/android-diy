package com.barry.animation.layout_animation

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.LayoutAnimationController
import android.view.animation.LinearInterpolator
import android.view.animation.ScaleAnimation
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.barry.animation.R

class LayoutAnimationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_animation)

        val mRecyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        val mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mRecyclerView.layoutManager = mLayoutManager

        val scaleAnimation = ScaleAnimation(0.1f, 1.0f, 0.1f, 1.0f)
        scaleAnimation.duration = 500
        scaleAnimation.interpolator = LinearInterpolator()
        val controller = LayoutAnimationController(scaleAnimation)
        controller.delay = 2.0f
        controller.interpolator = AccelerateDecelerateInterpolator()
        mRecyclerView.layoutAnimation = controller

        val mData = mutableListOf<String>()
        mData.add("111111111111");
        mData.add("2222222222222");
        mData.add("3333333333");
        mData.add("444444444444444");
        mData.add("55555555555");
        mData.add("6666666666666666666");
        mData.add("7777777777777777777");
        mData.add("888888888888888888888");
        mData.add("99999999999999999");
        mData.add("10101010010101010");
        mData.add("111111111111");
        mData.add("2222222222222");
        mData.add("3333333333");
        mData.add("444444444444444");
        mData.add("55555555555");
        mData.add("6666666666666666666");
        mData.add("7777777777777777777");
        mData.add("888888888888888888888");
        mData.add("99999999999999999");
        mData.add("10101010010101010");
        mData.add("111111111111");
        mData.add("2222222222222");
        mData.add("3333333333");
        mData.add("444444444444444");
        mData.add("55555555555");
        mData.add("6666666666666666666");
        mData.add("7777777777777777777");
        mData.add("888888888888888888888");
        mData.add("99999999999999999");
        mData.add("10101010010101010");

        val textAdapter = TextAdapter(this, mData)
        mRecyclerView.adapter = textAdapter

    }

    class TextAdapter(context: Context, list: List<String>) : RecyclerView.Adapter<TextAdapter.MyViewHolder>() {
        private var context = context
        private var list = list

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextAdapter.MyViewHolder {
            var view: View = LayoutInflater.from(context).inflate(R.layout.item_text, parent, false)
            return MyViewHolder(view)
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.textView.text = list[position]
        }

        inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var textView: TextView = view.findViewById(R.id.text_view)
        }
    }
}