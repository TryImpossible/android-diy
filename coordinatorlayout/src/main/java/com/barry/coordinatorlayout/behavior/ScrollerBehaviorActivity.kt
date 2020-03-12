package com.barry.coordinatorlayout.behavior

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.barry.coordinatorlayout.R
import kotlinx.android.synthetic.main.activity_scroller_behavior.*

class ScrollerBehaviorActivity : AppCompatActivity() {
    val CAPITAL_LETTER: String = "A\nB\nC\nD\nE\nF\nG\nH\nI\nJ\nK\nL\nM\nN\nO\nP\nQ\nR\nS\nT\nU\nV\nW\nX\nY\nZ\n"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroller_behavior)

        (ns_layout_child.get(0) as TextView).text = CAPITAL_LETTER
        (ns_layout_dependency.get(0) as TextView).text = CAPITAL_LETTER
    }
}
