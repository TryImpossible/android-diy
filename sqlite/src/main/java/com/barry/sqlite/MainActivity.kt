package com.barry.sqlite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.barry.sqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var db: DatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DatabaseHelper(this);
        binding.btnInsert.setOnClickListener { db?.insert() }
        binding.btnDelete.setOnClickListener { db?.delete() }
        binding.btnUpdate.setOnClickListener { db?.update() }
        binding.btnQuery.setOnClickListener { db?.query() }

    }

}
