package com.barry.java.Thread.c2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 创建线程池的四种方式
 */
public class C1_CreateThreadPool {
    public static void main(String[] args) {
        testNewFixedThreadPool();
//        testNewCachedThreadPool();
//        testNewSingleThreadExecutor();
//        testNewScheduledThreadPool();
    }

    /// 固定大小的线程池：一个饭店，5个桌子
    private static void testNewFixedThreadPool() {
        //创建一个包含5个线程的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭线程池，问题：线程池中线程一直占用系统资源，内存泄露，主线程一直不会退出
            // 不会立马停止正在执行的线程，会等待执行完毕后才彻底关闭
            threadPool.shutdown();

            // 不会立马停止正在执行的线程，办会等待正在执行的线程执行完后才彻底关闭
            threadPool.shutdownNow();

            try {
                // 等待线程池关闭，等待线程池中的所有线程执行完成
                threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 判断线程池是否真正"终止"了，并且代表线程也已经执行完毕
            System.out.println(threadPool.isTerminated());
        }
    }

    /// 动态创建线程的线程池：一个饭店，可以动态加桌子
    private static void testNewCachedThreadPool() {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 100; i++) {
                threadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("线程名：" + Thread.currentThread().getName());
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

    }

    /// 单个线程的线程池：一个饭店，1个桌子
    private static void testNewSingleThreadExecutor() {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        try {
            for (int i = 0; i < 20; i++) {
                threadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("线程名：" + Thread.currentThread().getName());
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    private static void testNewScheduledThreadPool() {
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(5);
        try {
            for (int i = 0; i < 20; i++) {
                threadPool.schedule(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("线程名：" + Thread.currentThread().getName());
                    }
                }, 5, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
