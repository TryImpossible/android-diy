package com.barry.java.Thread.c3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * InheritableThreadLocal 共享线程
 */
public class C4_InheritableThreadLocal {
    public static void main(String[] args) throws InterruptedException {
//        test1();
        test2();
    }

    private static void test1() throws InterruptedException {
        // 主线程的设置的test，子线程也可以获取到
        InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
        threadLocal.set("test");
        Thread thread = new Thread(() -> {
            String value = threadLocal.get();
            System.out.println("value：" + value);
        });
        thread.start();
        Thread.sleep(10000);
    }

    private static void test2() {
        // 中途修改的值，线程无法感知
        InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
        threadLocal.set("test");

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 20; i++) {
            // 在第11个任务后重新赋值
            if (i == 10) {
                threadLocal.set("test11");
            }
            executorService.execute(() -> {
                String value = threadLocal.get();
                System.out.println("value：" + value);
            });
        }
    }
}
