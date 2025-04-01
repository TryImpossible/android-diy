package com.barry.java.Thread.c1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程
 */
public class CreateThread {
    public static void main(String[] args) {
        /// 创建线程的方式一：继承Thread父类，重写run方法
        Thread t1 = new MyThread();
        t1.start();

        /// 创建线程的方式二：实现Runnable接口，重写run方法
        Thread t2 = new Thread(new MyRunnable());
        // Thread t2 = new Thread(() -> {}); //匿名类方法
        t2.start();

        /// 一：创建线程的方式三：实现线 Callable 接口。相较于实现 Runnable 接口的方式，方式可以有返回值并且可以抛出异常
        /// 二：执行 Callable 方式，需要 FutrueTask 实现类的支持，用于接收运算结果。FutureTask 是 Future 接口的实现类
        MyCallable callable = new MyCallable();
        // 1.执行Callable方式，需要FutureTask实现类的支持，用于接收运算结果
        FutureTask<Integer> result = new FutureTask<>(callable);
        new Thread(result).start();
        // 2.接收线程运算的结果
        Integer sum = null;
        try {
            // FutureTask的get()方法会自动阻塞，直到得到任务执行结果为止
            sum = result.get();
            System.out.println(sum);
            System.out.println("------------------------------------");
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            System.out.println("继承Thread，重写run方法");
        }
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("实现Runnable，实现run方法");
        }
    }

    static class MyCallable implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            int sum = 0;
            for (int i = 0; i < 100000; i++) {
                sum += i;
            }
            return sum;
        }
    }
}
