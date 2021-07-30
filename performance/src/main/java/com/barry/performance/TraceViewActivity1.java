package com.barry.performance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Toast;

import java.util.Date;

public class TraceViewActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trace_view1);

        setTitle("通过TraceView分析方法运行时长");
    }

    private int jisuan() {
        for (int i = 0; i < 10000; i++) {
            System.out.println(i);
        }
        return 1;
    }

    public void method1(View view) {
        int result = jisuan();
        System.out.println(result);
    }

    public void method2(View view) {
        SystemClock.sleep(2000);
    }

    public void method3(View view) {
        int sum = 0;
        for (int i = 0; i < 10000; i++) {
            sum += i;
        }
        System.out.println("sum=" + sum);
    }

    public void method4(View view) {
        Toast.makeText(this, "" + new Date(), Toast.LENGTH_SHORT).show();
    }
}