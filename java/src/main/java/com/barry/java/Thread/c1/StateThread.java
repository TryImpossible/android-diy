package com.barry.java.Thread.c1;

import java.util.concurrent.TimeUnit;

/**
 * 线程的生命周期
 */
public class StateThread {
    public static void main(String[] args) throws InterruptedException {
        test5();
    }

    private static void test1() { // NEW
        Thread t1 = new Thread(() -> {
            System.out.println("Thread1------");
        });
        System.out.println(t1.getState());
    }

    private static void test2() { // RUNNABLE
        Thread t1 = new Thread(() -> {
            System.out.println("Thread1------");
        });
        t1.start();
        System.out.println(t1.getState());
    }

    private static void test3() throws InterruptedException { // TERMINATED
        Thread t1 = new Thread(() -> {
            System.out.println("Thread1开始执行------");
            System.out.println("Thread1结束执行------");
        });
        t1.start();
        TimeUnit.SECONDS.sleep(3); //睡眠3s后线程已结束
        System.out.println(t1.getState());
    }

    private static void test4() throws InterruptedException { // BLOCK
        class Table {
            public synchronized void use() {
                System.out.println(Thread.currentThread().getName() + "-使用桌子");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "-就餐结束");
            }
        }


        System.out.println(Thread.currentThread().getName());
        Table table = new Table();
        Thread student1 = new Thread(() -> {
            table.use();
        }, "s1");
        Thread student2 = new Thread(() -> {
            table.use();
        }, "s2");
        student1.start();

        student2.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(student2.getState());
    }

    private static void test5() throws InterruptedException { // WAITING/TIMED_WAITING
        class Table {
            public synchronized void use() throws InterruptedException {
                System.out.println(Thread.currentThread().getName() + "-使用桌子");
                System.out.println("忘记点餐了");
//                wait(); //WAITING
                wait(3000); //TIMED_WAITING
                System.out.println(Thread.currentThread().getName() + "-就餐结束");
            }
        }


        System.out.println(Thread.currentThread().getName());
        Table table = new Table();
        Thread student1 = new Thread(() -> {
            try {
                table.use();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "s1");
        student1.start();
        Thread.sleep(100);
        System.out.println(student1.getState());
    }
}
