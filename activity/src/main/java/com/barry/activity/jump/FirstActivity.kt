package com.barry.activity.jump

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.barry.activity.R

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1000) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                call()
            } else {
                Toast.makeText(this, "你拒绝了拨打电话权限", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 隐式跳转至打电话Activity
    fun click1(view: View) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1000)
        } else {
            call()
        }
    }

    private fun call() {
        try {
            // 创建意图，这个是隐式意图
            val intent = Intent()
            intent.action = Intent.ACTION_CALL
            intent.data = Uri.parse("tel:10086")
            // 启动Activity
            startActivity(intent)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    // 显式启动SecondActivity
    fun click2(view: View) {
        val bundle = Bundle()
        bundle.putString("name", "张三")
        bundle.putInt("age", 20)

        val intent = Intent()
        intent.setClass(this, SecondActivity::class.java)
        intent.putExtras(bundle)

        startActivity(intent)
    }

    // 隐式启动SecondActivity
    fun click3(view: View) {
        val bundle = Bundle()
        bundle.putString("name", "张三")
        bundle.putInt("age", 20)

        val intent = Intent()
        intent.action = "a.b.c2"
        intent.setDataAndType(Uri.parse("heima:春眠不觉晚"), "text/name")
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        intent.putExtras(bundle)

        startActivity(intent)
    }

    // 显示启动拨号器
    fun click4(view: View) {
        val intent = Intent()
        intent.setClassName("com.android.dialer", "com.android.dialer.DialtactsActivity")
        intent.data = Uri.parse("tel:10086")
        startActivity(intent)
    }

    // 隐示启动拨号器
    fun click5(view: View) {
        val intent = Intent()
        intent.action = Intent.ACTION_DIAL
        intent.data = Uri.parse("tel:10086")
        startActivity(intent)
    }

    // 显示启动浏览器
    fun click6(view: View) {
        val intent = Intent()
        intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity")
        startActivity(intent)
    }

    // 隐示启动浏览器
    fun click7(view: View) {
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = Uri.parse("https://www.baidu.com")
        startActivity(intent)
    }

    fun click8(view: View) {
//        val intent = Intent()
//        intent.setClassName()
//        startActivity(intent)
    }
}