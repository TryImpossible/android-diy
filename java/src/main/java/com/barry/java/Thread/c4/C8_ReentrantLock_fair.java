package com.barry.java.Thread.c4;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 公平锁、非公平锁
 */
public class C8_ReentrantLock_fair {
    private static int num = 0;
    // 默认非公共锁，true 公共锁
    private static ReentrantLock fairLock = new ReentrantLock(true);
    private static class T extends Thread {
        T(String name){
            super(name);
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                fairLock.lock();
                try {
                    System.out.println(this.getName() + "获得锁!");
                } finally {
                    fairLock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T t1 = new T("t1");
        T t2 = new T("t2");
        T t3 = new T("t3");
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }
}
