package com.barry.java.Thread.c4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class C2_CAS {
    // 库存
    private static AtomicInteger stock = new AtomicInteger(1000000);
    static class StockRunnable implements Runnable {

        @Override
        public void run() {
           int oldValue;
           int newValue;
           do {
               oldValue = stock.get();
               newValue = oldValue - 1;
           } while (!stock.compareAndSet(oldValue, newValue));
        }
    }

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 300; i++) {
                StockRunnable task = new StockRunnable();
                threadPool.execute(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
