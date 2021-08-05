package com.barry.java.Thread;

public class TestVolatile {
    private static volatile Boolean flag = true;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (flag) {
                        System.out.println("线程A");
                        flag = false;
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (!flag) {
                        System.out.println("线程B");
                        flag = true;
                    }
                }
            }
        }).start();
    }
}
