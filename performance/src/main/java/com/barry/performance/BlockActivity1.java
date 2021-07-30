package com.barry.performance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BlockActivity1 extends AppCompatActivity {

    private static final String TAG = "BlockActivity1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block1);
    }

    public void method1(View view) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.e(TAG, "onClick of R.id.button1: ", e);
        }
    }

    public void method2(View view) {
        for (int i = 0; i < 100; ++i) {
            readFile();
        }
    }

    public void method3(View view) {
        double result = compute();
        System.out.println(result);
    }

    private double compute() {
        double result = 0;
        for (int i = 0; i < 1000000; ++i) {
            result += Math.acos(Math.cos(i));
            result -= Math.asin(Math.sin(i));
        }
        return result;
    }

    private void readFile() {
        FileInputStream reader = null;
        try {
            reader = new FileInputStream("/proc/stat");
            while (reader.read() != -1) {
            }
        } catch (IOException e) {
            Log.e(TAG, "readFile: /proc/stat", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(TAG, " on close reader ", e);
                }
            }
        }
    }
}