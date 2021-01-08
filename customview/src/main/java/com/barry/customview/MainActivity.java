package com.barry.customview;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.barry.customview.canvas.CanvasShowActivity;
import com.barry.customview.widget.WidgetShowActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_canvas).setOnClickListener((view) -> startActivity(new Intent(this, CanvasShowActivity.class)));
        findViewById(R.id.btn_widget).setOnClickListener((view) -> startActivity(new Intent(this, WidgetShowActivity.class)));

    }
}
