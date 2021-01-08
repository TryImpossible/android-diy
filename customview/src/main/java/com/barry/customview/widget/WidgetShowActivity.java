package com.barry.customview.widget;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.barry.customview.R;

public class WidgetShowActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_show);

        findViewById(R.id.btn_random_num).setOnClickListener(this);
        findViewById(R.id.btn_four_corner).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_random_num:
                startActivity(new Intent(WidgetShowActivity.this, RandomNumViewActivity.class));
                break;
            case R.id.btn_four_corner:
                startActivity(new Intent(WidgetShowActivity.this, FourCornerViewActivity.class));
                break;
            default:
                break;
        }
    }
}