package com.barry.android_notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.barry.baselib.activity.WebActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnProvider = findViewById(R.id.btn_provider);
        btnProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StudentContentProviderActivity.class));
            }
        });
        findViewById(R.id.btn_webview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                intent.putExtra("url", "https://www.baidu.com");
                intent.putExtra("hasNav", "true");
                startActivity(intent);
            }
        });
    }
}
