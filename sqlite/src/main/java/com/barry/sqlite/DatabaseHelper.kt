package com.barry.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context?) : SQLiteOpenHelper(context, DB, null, 2) {

    companion object {
        const val DB: String = "app.db"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val sql: String = "create table if not exists user (" +
                "id integer primary key autoincrement not null," +
                " name varchar(20) not null," +
                " gender integer not null," +
                " age integer not null" +
                ");"
        db?.execSQL(sql);
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.e("Barry", "$oldVersion - $newVersion")
        if (newVersion == 2) {
            val sql = "alter table user add mobile varchar(20)"
            db?.execSQL(sql)
        }
    }

    fun insert() {
//        val values = ContentValues();
//        values.put("name", "张三")
//        values.put("gender", 1)
//        values.put("age", 27)
//        writableDatabase.insert("user", null, values);

        val sql = "insert into user (name, gender, age) values ('张三', 2, 32)"
        writableDatabase.execSQL(sql)
    }

    fun delete() {
//        writableDatabase.delete("user", null, null)

        val sql = "delete from user"
        writableDatabase.execSQL(sql)
    }

    fun update() {
//        val values = ContentValues();
//        values.put("age", 25)
//        writableDatabase.update("user", values, "name=?", arrayOf("张三"))

        val sql = "update user set gender = 2, age = 18 where name = '张三'"
        writableDatabase.execSQL(sql)
    }

    fun query(): MutableList<Map<String, Any>> {

//        val cursor = readableDatabase.query("user", arrayOf("name", "gender", "age"), "name=?", arrayOf("张三"), null, null, null)
        val cursor = readableDatabase.rawQuery("select * from user where name=?", arrayOf("张三"))

        var list: MutableList<Map<String, Any>> = ArrayList();
        while (cursor.moveToNext()) {
            val name = cursor.getString(cursor.getColumnIndex("name"))
            val gender = cursor.getInt(cursor.getColumnIndex("gender"))
            val age = cursor.getInt(cursor.getColumnIndex("age"))
            val obj = mapOf("name" to name, "gender" to gender, "age" to age)
            list.add(obj)
        }
        cursor.close()
        return list
    }
}