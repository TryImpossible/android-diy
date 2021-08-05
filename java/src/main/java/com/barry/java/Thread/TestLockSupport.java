package com.barry.java.Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

public class TestLockSupport {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        // 实现线程B
        final Thread threadB = new Thread(() -> {
            if (list.size() != 5) {
                LockSupport.park();
            }
            System.out.println("线程B收到通知，开始执行自己的业务。。。");
        });
        // 实现线程A
        final Thread threadA = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                list.add("abc");
                System.out.println("线程A向list中添加一个元素，此时list中的元素个数为：" + list.size());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (list.size() == 5) {
                    LockSupport.unpark(threadB);
                }
            }
        });
        threadA.start();
        threadB.start();
    }
}
