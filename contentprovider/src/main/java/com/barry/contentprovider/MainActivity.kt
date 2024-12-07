package com.barry.contentprovider

import android.content.ContentValues
import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.barry.contentprovider.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    var dao: StudentDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        dao = StudentDao.getInstance(this)
        binding.btnAdd.setOnClickListener(this)
        binding.btnDel.setOnClickListener(this)
        binding.btnQuery.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_add -> {
                val name: String = binding.etName.text.toString()
                val gender: String = binding.etGender.text.toString()
                val values: ContentValues = ContentValues();
                values.put("name", name);
                values.put("gender", if (gender.equals("男")) 1 else 2);
                dao?.insertStudent(values)
            }
            R.id.btn_del -> {
                dao?.deleteStudent();
                binding.llContent.removeAllViews();
            }
            R.id.btn_query -> {
                binding.llContent.removeAllViews();

                val cursor: Cursor? = dao?.queryStudent();
                if (cursor?.count == 0) {
                    val text = TextView(this);
                    text.text = String.format("暂无数据");
                    text.layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
                    binding.llContent.addView(text);
                } else {
                    while (cursor?.moveToNext()!!) {
                        val name: String = cursor.getString(cursor.getColumnIndex("name"));
                        val gender: Int = cursor.getInt(cursor.getColumnIndex("gender"));

                        val text = TextView(this);
                        text.text = String.format("姓名：%s，性别：%s", name, if (gender == 1) "男" else "女");
                        text.layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
                        binding.llContent.addView(text);
                    }
                }
                cursor.close();
            }

        }
    }
}
