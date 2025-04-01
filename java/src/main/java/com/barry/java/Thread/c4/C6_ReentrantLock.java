package com.barry.java.Thread.c4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 更灵活，需要手动上锁和解锁 java实现  锁粒度细
 *               一定要解锁，最好解锁前lock.isHeldByCurrentThread() 适用动态解锁场景
 * synchronized        隐式上锁和解锁 jvm c++实现
 */
public class C6_ReentrantLock {
    private static Integer stock = 100000;

    // 1.创建ReentrantLock对象
    private static ReentrantLock lock = new ReentrantLock();

    static class StockRunnable implements Runnable {
//        @Override
//        public synchronized void run() {
//            stock--;
//        }

        @Override
        public void run() {
            try {
                lock.lock();
                stock--;
            } finally {
                // 是否在当前线程加锁
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        StockRunnable task = new StockRunnable();

        try {
            for (int i = 0; i < 100000; i++) {
                threadPool.execute(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("库存为" + stock);
            threadPool.shutdown();
        }
    }
}
