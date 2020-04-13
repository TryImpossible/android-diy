package com.barry.coordinatorlayout.adpater

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barry.coordinatorlayout.R

class SingleTextAdapter(private val context: Context, private val data: List<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_single_text, parent, false)
        return SingleTextViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val str: String = data[position]
        (holder as SingleTextViewHolder).textView?.text = str
    }

    inner class SingleTextViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var textView: TextView? = null

        init {
            textView = view.findViewById(R.id.tv_text)
        }
    }
}