package com.barry.java.Thread.c4;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock 读锁是共享锁，写锁是排它锁
 */
public class C9_ReentrantLock_RW {
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    // 读锁
    private static ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    // 写锁
    private static ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    private static void read() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到了读锁，正在读取");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放了读锁");
            readLock.unlock();
        }
    }

    private static void write() {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到了写锁，正在写入");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放了写锁");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                read();
            }
        }, "t1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                read();
            }
        }, "t2").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                write();
            }
        }, "t3").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                write();
            }
        }, "t4").start();
    }
}
