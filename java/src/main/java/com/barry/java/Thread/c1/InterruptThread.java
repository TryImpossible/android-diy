package com.barry.java.Thread.c1;

import java.util.concurrent.TimeUnit;

/**
 * 中断线程
 */
public class InterruptThread {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                // 会清除打断标记 ---> 撤销打断
                System.out.println(Thread.interrupted() + "");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("睡眠完毕");
        });
        t1.start();
//        System.out.println(t1.interrupted() + ""); // 不清除打断标记 ---> 不撤销打断
        // 打断线程
        t1.interrupt();
//        System.out.println(t1.interrupted() + ""); // 不清除打断标记 ---> 不撤销打断
    }

    private void test2() {
        Thread t1 = new Thread(() ->{
            try {
                System.out.println("进入睡眠状态");
                TimeUnit.SECONDS.sleep(10);
                System.out.println("睡眠完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("run方法执行完毕");
        });
        t1.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();
    }

}
