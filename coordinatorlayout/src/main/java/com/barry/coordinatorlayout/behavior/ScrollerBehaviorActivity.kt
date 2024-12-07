package com.barry.coordinatorlayout.behavior

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.barry.coordinatorlayout.R
import com.barry.coordinatorlayout.databinding.ActivityScrollerBehaviorBinding

class ScrollerBehaviorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScrollerBehaviorBinding
    val CAPITAL_LETTER: String = "A\nB\nC\nD\nE\nF\nG\nH\nI\nJ\nK\nL\nM\nN\nO\nP\nQ\nR\nS\nT\nU\nV\nW\nX\nY\nZ\n"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScrollerBehaviorBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_scroller_behavior)

        (binding.nsLayoutChild.get(0) as TextView).text = CAPITAL_LETTER
        (binding.nsLayoutDependency.get(0) as TextView).text = CAPITAL_LETTER
    }
}
