package com.barry.sqlite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var db: DatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DatabaseHelper(this);
        btn_insert.setOnClickListener { db?.insert() }
        btn_delete.setOnClickListener { db?.delete() }
        btn_update.setOnClickListener { db?.update() }
        btn_query.setOnClickListener { db?.query() }

    }

}
