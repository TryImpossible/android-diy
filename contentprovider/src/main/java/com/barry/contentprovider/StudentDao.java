package com.barry.contentprovider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class StudentDao {
    public static final String TABLE_NAME = "student";

    public static final String TABLE_SQL = "create table if not exists student (" +
            "id integer primary key autoincrement not null, " +
            "name varchar(20) not null," +
            "gender integer not null" +
            ");";

    private static volatile StudentDao instance;
    private static SQLiteHelper mHelper;

    private StudentDao() {
    }

    public static StudentDao getInstance(Context context) {
        if (instance == null) {
            synchronized (StudentDao.class) {
                if (instance == null) {
                    instance = new StudentDao();
                    mHelper = new SQLiteHelper(context);
                }
            }
        }
        return instance;
    }

    public Cursor queryStudent() {
        return mHelper.getWritableDatabase().query(TABLE_NAME, new String[]{"name", "gender"}, null, null, null, null, null);
    }

    public void insertStudent(ContentValues values) {
        mHelper.getWritableDatabase().insert(TABLE_NAME, null, values);
    }

    public int deleteStudent() {
        return mHelper.getWritableDatabase().delete(TABLE_NAME, null, null);
    }
}
