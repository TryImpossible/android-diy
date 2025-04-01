package com.barry.java.Thread.c1;

import java.util.concurrent.TimeUnit;

/**
 * 等待调用该方法的线程终止
 */
public class JoinThread {
    static int value = 1;
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            // 业务
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            value = 10;
            System.out.println("子线程Runnable");
        });
        t1.start();
        System.out.println("t1线程是否存活：" + t1.isAlive());
        t1.join(1000); // 异步变同步
        System.out.println("t1线程是否存活：" + t1.isAlive());
        System.out.println("主线程value="+value);

        System.out.println("-----------------------------");

        /// 实现按顺序执行
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程0开始执行了");
            }
        });
        thread.start();
        for (int i = 0; i < 10; i++) {
            MyThread jt = new MyThread(thread, i);
            jt.start();
            thread = jt;
        }
    }

    static class MyThread extends Thread {
        private Thread thread;
        private int i;

        public MyThread(Thread thread, int i) {
            this.thread = thread;
            this.i = i;
        }

        @Override
        public void run() {
            try {
                thread.join();
                System.out.println("线程" + (i + 1) + "执行了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
