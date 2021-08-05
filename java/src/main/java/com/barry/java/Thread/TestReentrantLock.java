package com.barry.java.Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLock {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        List<String> list = new ArrayList<>();
        // 实现线程A
        Thread threadA = new Thread(() -> {
            reentrantLock.lock();
            try {
                for (int i = 1; i <= 10; i++) {
                    list.add("abc");
                    System.out.println("线程A向list中添加一个元素，此时list中的元素个数为：" + list.size());

                    Thread.sleep(500);

                    if (list.size() == 5) {
                        condition.signal();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        });
        // 实现线程B
        Thread threadB = new Thread(() -> {
            reentrantLock.lock();
            try {
                if (list.size() != 5) {
                    condition.await();
                }
                System.out.println("线程B收到通知，开始执行自己的业务...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        });

        threadB.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadA.start();
    }
}
