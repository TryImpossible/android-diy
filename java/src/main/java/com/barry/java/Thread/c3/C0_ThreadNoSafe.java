package com.barry.java.Thread.c3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 什么是线程不安全
 */
public class C0_ThreadNoSafe {
    public static Integer stock = 1000000;
    //    public static AtomicInteger stock = new AtomicInteger(1000000);
    static class StockRunnable implements Runnable {
        @Override
        public void run() {
            if (stock > 0) {
                // 购买 stock=stock-1，1000000-1
                stock--;
            }
        }

//        @Override
//        public void run() {
//            if (stock.get() > 0) {
//                // 购买 stock=stock-1，1000000-1
//                stock.decrementAndGet();
//            }
//        }

//        @Override
//        public synchronized void run() {
//            if (stock > 0) {
//                // 购买 stock=stock-1，1000000-1
//                stock--;
//            }
//        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        StockRunnable task = new StockRunnable();
        try {
            for (int i = 0; i < 1000000; i++) {
                executorService.execute(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
            // 等待关闭
            executorService.awaitTermination(1000, TimeUnit.SECONDS);
        }
        System.out.println("剩余库存：" + stock);
    }
}
