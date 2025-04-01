package com.barry.java.Thread.c4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 尝试锁
 */
public class C6_ReentrantLock_trylock {
    private static ReentrantLock lock = new ReentrantLock(false);

    static class T extends Thread {
        public T(String name) {
            super(name);
        }

        @Override
        public void run() {
            try {
                System.out.println(System.currentTimeMillis() + ":" + this.getName() + "开始获取锁!;");
                // trylock 尝试获取锁，如果锁被占用，返回false。    没有占用返回true  指定获取锁的等待时间
//                if (lock.tryLock()) {
                if (lock.tryLock(2, TimeUnit.SECONDS)) {
                    System.out.println(System.currentTimeMillis() + ":" + this.getName() + "获取到了锁!;");
                    // 获取锁之后，休眠5s
                    TimeUnit.SECONDS.sleep(5);
                } else {
                    System.out.println(System.currentTimeMillis() + ":" + this.getName() + "未能获取到锁!;");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        T t1 = new T("t1");
        t1.start();

        T t2 = new T("t2");
        t2.start();
    }
}
