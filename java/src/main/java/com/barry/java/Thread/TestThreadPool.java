package com.barry.java.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestThreadPool {
    public static void main(String[] args) {
//        testNewFixedThreadPool();
//        testNewCachedThreadPool();
//        testNewSingleThreadExecutor();
        testNewScheduledThreadPool();
    }

    private static void testNewFixedThreadPool() {
        //创建一个包含5个线程的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //使用线程池执行任务
//        for (int i = 0; i < 5; i++) {
//            //可以用submit执行任务
//            threadPool.submit(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("线程名：" + Thread.currentThread().getName());
//                }
//            });
//        }
        //也可以用execute执行任务
        for (int i = 0; i < 20; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程名：" + Thread.currentThread().getName());
                }
            });
        }
    }

    private static void testNewCachedThreadPool() {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程名：" + Thread.currentThread().getName());
                }
            });
        }
    }

    private static void testNewSingleThreadExecutor() {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 20; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程名：" + Thread.currentThread().getName());
                }
            });
        }
    }

    private static void testNewScheduledThreadPool() {
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 20; i++) {
            threadPool.schedule(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程名：" + Thread.currentThread().getName());
                }
            }, 5000, TimeUnit.MILLISECONDS);
        }
    }
}
