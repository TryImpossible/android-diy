package com.barry.java.Thread.c3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocal 强引用 造成内存泄露
 * -Xms25m -Xmx25m
 */
public class C3_ThreadLocalOOM {
    public static void main(String[] args) {
        ThreadLocal<User> userL = new ThreadLocal<>();
        ExecutorService executorService = Executors.newFixedThreadPool(30);
        for (int i = 0; i < 30; i++) {
            // 线程1
            executorService.execute(() -> {
//                new User();
                // 不进行remove会很内存溢出
                userL.set(new User());
                userL.remove();
            });
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class User {
        public int age = 0;
        public byte[] info = new byte[1024 * 1024];
    }
}
