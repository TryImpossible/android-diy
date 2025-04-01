package com.barry.java.Thread.c1;

/**
 * yield方法
 * 提示当前线程主动让出CPU资源，以便其他具有相同或更高优先级的线程有机会执行‌
 */
public class YieldThread {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Task2());
//        t1.setPriority(Thread.MAX_PRIORITY);
        t1.start();

        Thread t2 = new Thread(new Task1());
//        t2.setPriority(Thread.MIN_PRIORITY);
        t2.start();
    }

    static class Task1 implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 200; i++) {
                System.out.println("A:"+i);
            }
        }
    }

    static class Task2 implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                Thread.yield();
                System.out.println("B:"+i);
            }
        }
    }
}
