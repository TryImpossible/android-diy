package com.barry.java.Thread.c4;

/**
 * 1. 所属类不同：sleep是线程中的方法，而wait是Object中的方法
 * 2. 语法不同：sleep方法不依赖于同步器synchronized，但是wait需要依赖synchronized关键字
 * 3. 参数不同：sleep必须设置参数时间，wait可以不设置参数时间，不设置将一直休眠
 * 4. 释放资源不同：sleep方法不会释放lock，但是wait会释放，而且会加入到等待队列中
 * 5. 唤醒方式不同：sleep不需要被唤醒（休眠之后退出阻塞），但是wait需要（不指定时间需要别人中断）
 * 6. 线程进入状态不同：调用sleep方法会进入TIMED_WAITING有时限等待状态，而调用无参数的wait方法，线程会进入WAITING无时限等待状态
 */
public class C4_SleepAndWait {
    public static final Object monitor = new Object();

    public static void main(String[] args) throws InterruptedException {
//        demo1();
//        demo2();
//        demo3();
//        demo4();
        demo5();
    }

    private static void demo1() throws InterruptedException {
        Thread.sleep(100);
        // 运行报错
        monitor.wait(2000);
    }

    private static void demo2() throws InterruptedException {
        Thread.sleep(100);

        synchronized (monitor) {
            monitor.wait(2000);
        }
    }

    private static void demo3() throws InterruptedException {
        new Thread(() -> {
            System.out.println("子线程执行...");
            synchronized (monitor) {
                try {
//                    Thread.sleep(5000); // sleep不会释放锁
                    monitor.wait(5000); // wait带时间参数方法 会释放锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(1000);

        synchronized (monitor) {
            System.out.println("主线程执行...");
        }
    }

    private static void demo4() throws InterruptedException {
        new Thread(() -> {
            System.out.println("子线1程执行...");
            synchronized (monitor) {
                try {
                    monitor.wait(); // wait为带时间参数方法，不会释放锁，需调用notify方法
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            System.out.println("子线2程执行...");
            synchronized (monitor) {
                try {
                    monitor.wait(100000); // wait为带时间参数方法，不会释放锁，需调用notify方法
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(1000);

        synchronized (monitor) {
            System.out.println("主线程执行...");
            monitor.notify();
            monitor.notifyAll();
        }
    }

    private static void demo5() throws InterruptedException {
        Thread mainThread = Thread.currentThread();

        Thread thread = new Thread(() -> {
            System.out.println(mainThread.getState());
        });
        thread.start();

        synchronized (monitor) {
            monitor.wait(2000);
        }
    }
}
