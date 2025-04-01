package com.barry.java.Thread.c4;

/**
 * 可重入锁
 */
public class C10_Synchronized_ReLock {
    private static final Object objectLockA = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            relock();
        }, "a").start();
    }

    private static Integer i = 0;
    private static void relock() {
        synchronized (objectLockA) {
            if (i == 3) {
                return;
            } else {
                i++;
                System.out.println(i + "调用");
                relock();
            }
        }
    }
}
