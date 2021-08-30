package com.barry.activity.jump

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.barry.activity.R

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val name = intent.extras?.getString("name")
        findViewById<TextView>(R.id.tv_name).text = "姓名：$name"

        val age = intent.extras?.getInt("age")
        findViewById<TextView>(R.id.tv_age).text = "年龄：$age"
    }
}