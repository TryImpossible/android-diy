package com.barry.java.Thread.c2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池 new ThreadPoolExecutor
 */
public class C2_CustomThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(
                10,
                20,
                0L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        try {
            for (int i = 0; i < 100; i++) {
                MyTask task = new MyTask(i+1);
                executorService.execute(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    static class MyTask implements Runnable {
        int i = 0;

        public MyTask(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "程序员做第" + i + "个项目");
            try {
                Thread.sleep(3000L); // 业务逻辑
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
