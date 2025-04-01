package com.barry.java.Thread.c4;

import org.openjdk.jol.info.ClassLayout;

/**
 * synchronized锁升级
 */
public class C5_Synclevel {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(4200);
        class T {
            Integer age;
        }

        T o = new T();
        /// 无锁状态
//        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        /// 偏向锁
//        synchronized (o) {
//            System.out.println(ClassLayout.parseInstance(o).toPrintable());
//        }
        // 没有线程竞争的条件下，不会升级
        /*
        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
        */

        /// 轻量锁
        /*
        synchronized (o) {
            System.out.println("Main = " + ClassLayout.parseInstance(o).toPrintable());
        }
        Thread thread = new Thread() {
            @Override
            public void run() {
                synchronized (o) {
                    System.out.println("Thread = " + ClassLayout.parseInstance(o).toPrintable());
                }
            }
        };
        thread.start();
        thread.join();
        System.out.println("End = " + ClassLayout.parseInstance(o).toPrintable());
         */

        /// 重量级锁
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o) {
                    System.out.println("Thread1 = " + ClassLayout.parseInstance(o).toPrintable());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o) {
                    System.out.println("Thread2 = " + ClassLayout.parseInstance(o).toPrintable());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
