package com.barry.uniapplets;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import io.dcloud.feature.sdk.DCUniMPSDK;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_applets).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    DCUniMPSDK.getInstance().startApp(MainActivity.this,"__UNI__04E3A11");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
