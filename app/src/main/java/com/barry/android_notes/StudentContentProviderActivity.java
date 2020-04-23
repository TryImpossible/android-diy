package com.barry.android_notes;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class StudentContentProviderActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = StudentContentProviderActivity.class.getSimpleName();

    private static final String AUTHORITY = "com.barry.studentProvider";
    private static final Uri STUDENT_URI = Uri.parse("content://" + AUTHORITY + "/student");

    private ContentResolver mContentResolver;
    private MyContentObserver mContentObserver;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Log.i(TAG, "数据发生改变了");
            Toast.makeText(StudentContentProviderActivity.this, "数据发生改变了,即将为你更新数据", Toast.LENGTH_LONG).show();
            new Handler().postAtTime(new Runnable() {
                @Override
                public void run() {
                    query();
                }
            }, 200);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_content_provider);

        mContentResolver = getContentResolver();
        mContentObserver = new MyContentObserver(mHandler);
        mContentResolver.registerContentObserver(STUDENT_URI, true, mContentObserver);

        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_del).setOnClickListener(this);
        findViewById(R.id.btn_query).setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mContentResolver.unregisterContentObserver(mContentObserver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                EditText etName = findViewById(R.id.et_name);
                EditText etGender = findViewById(R.id.et_gender);
                ContentValues values = new ContentValues();
                values.put("name", etName.getText().toString());
                values.put("gender", etGender.getText().toString().equals("男") ? 1 : 2);
                mContentResolver.insert(STUDENT_URI, values);
                break;
            case R.id.btn_del:
                mContentResolver.delete(STUDENT_URI, null, null);
                break;
            case R.id.btn_query:
                query();
                break;
            default:
                break;
        }
    }

    private void query() {
        LinearLayout llContent = findViewById(R.id.ll_content);
        llContent.removeAllViews();

        Cursor cursor = mContentResolver.query(STUDENT_URI, null, null, null, null);
        if (cursor == null || cursor.getCount() == 0) {
            TextView text = new TextView(this);
            text.setText("暂无数据");
            text.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            llContent.addView(text);
        } else {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int gender = cursor.getInt(cursor.getColumnIndex("gender"));

                TextView text = new TextView(this);
                text.setText(String.format("姓名：%s，性别：%s", name, gender == 1 ? "男" : "女"));
                text.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                llContent.addView(text);
            }
        }
        cursor.close();
    }


    private static class MyContentObserver extends ContentObserver {

        private Handler mHandler;

        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        public MyContentObserver(Handler handler) {
            super(handler);
            mHandler = handler;
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            super.onChange(selfChange, uri);
            Message message = mHandler.obtainMessage();
            message.obj = uri;
            mHandler.sendMessage(message);
        }
    }
}
