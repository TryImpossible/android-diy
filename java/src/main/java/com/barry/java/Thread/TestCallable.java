package com.barry.java.Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 一：创建线程的方式三：实现线 Callable 接口。相较于实现 Runnable 接口的方式，方式可以有返回值并且可以抛出异常
 * 二：执行 Callable 方式，需要 FutrueTask 实现类的支持，用于接收运算结果。FutureTask 是 Future 接口的实现类
 */
public class TestCallable {
    public static void main(String[] args) {
        MyCallable callable = new MyCallable();
        // 1.执行Callable方式，需要FutureTask实现类的支持，用于接收运算结果
        FutureTask<Integer> result = new FutureTask<>(callable);
        new Thread(result).start();

        // 2.接收线程运算的结果
        Integer sum = null;
        try {
            sum = result.get();
            System.out.println(sum);
            System.out.println("------------------------------------");
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class MyCallable implements Callable<Integer> {
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


