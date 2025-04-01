package com.barry.java.Thread.c2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 线程池的execute和submit方法的区别
 */
public class C3_ExecuteVSSubmit {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        // execute和submit方法有什么区别
        // 1.参数
        // execute只允许Runnable，submit还允许Callable
        // 2.返回值
        // execute void，submit Future
        // 3.异常
        // execute会在子线程中抛出异常，在主线程捕获不到；submit不会立马抛出异常，会将异常暂时存储起来，等future.get()方法时才抛出，并且可以在主线程捕获
        threadPool.execute(() -> {
            System.out.println("执行线程...");
        });

        // 为什么execute也可以执行带返回值的线程
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            System.out.println("子线程运行中...");
            return 5;
        });
        threadPool.execute(futureTask);
        Integer integer = futureTask.get();
        System.out.println(integer);


        Future<Integer> future = threadPool.submit(() -> {
            System.out.println("执行线程...");
            return 5;
        });
        Integer integer1 = future.get();
        System.out.println(integer1);

        try {
            threadPool.execute(() -> {
                int a = 1 / 0;
                System.out.println("执行");
            });
        } catch (Exception e) {
            System.out.println("出现异常");
            e.printStackTrace();
        }

        try {
            Future<?> future1 = threadPool.submit(() -> {
                int a = 1 / 0;
                System.out.println("执行");
            });
            future1.get();
        } catch (Exception e) {
            System.out.println("出现异常");
            e.printStackTrace();
        }
    }
}
