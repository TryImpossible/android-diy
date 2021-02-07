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

        findViewById(R.id.btn_random_num_view).setOnClickListener(this);
        findViewById(R.id.btn_four_corner_layout).setOnClickListener(this);
        findViewById(R.id.btn_flow_layout).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_random_num_view:
                startActivity(new Intent(WidgetShowActivity.this, RandomNumViewActivity.class));
                break;
            case R.id.btn_four_corner_layout:
                startActivity(new Intent(WidgetShowActivity.this, FourCornerLayoutActivity.class));
                break;
            case R.id.btn_flow_layout:
                startActivity(new Intent(WidgetShowActivity.this, FlowLayoutActivity.class));
                break;
            default:
                break;
        }
    }
}