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
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var dao: StudentDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dao = StudentDao.getInstance(this)
        btn_add.setOnClickListener(this)
        btn_del.setOnClickListener(this)
        btn_query.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_add -> {
                var name: String = et_name.text.toString()
                var gender: String = et_gender.text.toString()
                val values: ContentValues = ContentValues();
                values.put("name", name);
                values.put("gender", if (gender.equals("男")) 1 else 2);
                dao?.insertStudent(values)
            }
            R.id.btn_del -> {
                dao?.deleteStudent();
                ll_content.removeAllViews();
            }
            R.id.btn_query -> {
                ll_content.removeAllViews();

                val cursor: Cursor? = dao?.queryStudent();
                if (cursor?.count == 0) {
                    val text = TextView(this);
                    text.text = String.format("暂无数据");
                    text.layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
                    ll_content.addView(text);
                } else {
                    while (cursor?.moveToNext()!!) {
                        val name: String = cursor.getString(cursor.getColumnIndex("name"));
                        val gender: Int = cursor.getInt(cursor.getColumnIndex("gender"));

                        val text = TextView(this);
                        text.text = String.format("姓名：%s，性别：%s", name, if (gender == 1) "男" else "女");
                        text.layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
                        ll_content.addView(text);
                    }
                }
                cursor?.close();
            }

        }
    }
}
