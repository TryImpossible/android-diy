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

    private static void test1() {
        Thread currentThread = Thread.currentThread();
        currentThread.interrupt(); // 中断当前线程

        // 静态方法：检查并清除中断状态
        boolean interrupted1 = Thread.interrupted(); // 返回true，中断状态被清除
        System.out.println(interrupted1 + "");
        boolean interrupted2 = Thread.interrupted(); // 返回false，状态已清除
        System.out.println(interrupted2 + "");

        currentThread.interrupt(); // 再次中断

        // 实例方法：检查但不清除状态
        boolean isInterrupted1 = currentThread.isInterrupted(); // 返回true
        System.out.println(isInterrupted1 + "");
        boolean isInterrupted2 = currentThread.isInterrupted(); // 仍返回true
        System.out.println(isInterrupted2 + "");
    }

    private static void test2() {
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
