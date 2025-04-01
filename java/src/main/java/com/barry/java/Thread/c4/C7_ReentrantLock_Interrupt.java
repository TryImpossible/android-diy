package com.barry.java.Thread.c4;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock  通过interrupt()可中断等待的线程
 */
public class C7_ReentrantLock_Interrupt {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("线程 1 获取了锁");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
                System.out.println("线程 2 获取了锁");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("线程 2 异常");
                e.printStackTrace();
            } finally {
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        });

        thread1.start();
        Thread.sleep(1000);
        thread2.start();
        Thread.sleep(1000);
        thread2.interrupt();
    }
}
