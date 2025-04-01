package com.barry.java.Thread.c1;

/**
 * 线程的run、start方法的区别
 */
public class StartAndRunThread {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("2. 子线程启动..");
        });

        System.out.println("1. 开始创建线程");
//        t1.run(); // 同步执行
        t1.start(); // 异步执行
        System.out.println("3. 主线程结束");

        // run方法是同步方法，start方法是异步方法，
        // run方法可以执行无数次，start方法只能执行一次，原因线程不能被重复启动
    }
}
