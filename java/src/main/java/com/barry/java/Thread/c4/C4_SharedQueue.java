package com.barry.java.Thread.c4;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 生产消息者队列
 */
public class C4_SharedQueue {
    // 声明队列最大长度
    private static int queueSize = 10;
    private static ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(queueSize);

    /// 消费者
    static class Consumer extends Thread {
        @Override
        public void run() {
            // 消费者需要重复的工作
            while (true) {
                // 保证整个消息的过程是线程安全的
                synchronized (queue) {
                    // 如何队列为空，消费者睡眠
                    if (queue.isEmpty()) {
                        System.out.println("当前队列为空...");
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            // 一亘出线异常，手动唤醒
                            queue.notify();
                        }
                    } else {
                        // 队列不为空，消费者需要进行消息
                        // 消费头部的信息
                        Integer value = queue.poll();

                        System.out.println("消费者从队列中消费信息：" + value +"，队列当前长度：" + queue.size());

                        // 唤醒生产者可以继续工作了
                        queue.notify();

                        // 模拟业务处理
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /// 生产者
    static class Producter extends Thread {
        @Override
        public void run() {
            // 保证生产者在整个生产过程中是安全的
            synchronized (queue) {
                // 1. 判断当前队列是否小于最大长度
                if (queue.size() < queueSize) {
                    // 2. 如何小于，生产者就可以生产消息了

                    // 2.1 往队列queue添加一条消息
                    queue.add(queue.size() + 1);

                    System.out.println("生产者往队列中加入消息，队列当前长度：" + queue.size());
                    // 2.2 唤醒消费者，有活了
                    queue.notify();

                    // 模拟业务处理
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } else {
                    // 3. 如何大于 生产者停止工作 稍微歇一歇
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        queue.notify();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        // 消费者持续运行
        Consumer consumer = new Consumer();
        consumer.start();

        //生产10条消息
        for (int i = 0; i < 10; i++) {
            // 创建10生产者线程
            Producter producter = new Producter();
            producter.start();
        }
    }
}
