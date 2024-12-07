package com.barry.eventdispatch

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.barry.eventdispatch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        binding.btnViewgroupDispatch.setOnClickListener {
            startActivity(Intent(this, ViewGroupDispatchActivity().javaClass))
        }
        binding.btnViewDispatch.setOnClickListener {
            startActivity(Intent(this, ViewDispatchActivity().javaClass))
        }
    }
}
