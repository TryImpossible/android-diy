package com.barry.java.Thread.c1;

/**
 * 设置线程名称、获取线程名称
 */
public class NameThread {
    public static void main(String[] args) {
        // 创建线程
        Thread t1 = new MyThread();
        // 给线程起名
        t1.setName("t1");
        // 设置优先级（由低到高1~10）
        t1.setPriority(5);

        Thread t2 = new Thread(() -> {
            System.out.println("实现Runnable，重写run方法" + "\n" + "当前线程名称：" + Thread.currentThread().getName());
        });
        t2.setName("t2");
        t2.setPriority(10);

        // 告诉JVM再分配一个新的栈给t线程，run方法不需要手动调用
        t1.start();
        // 系统进程启动后自动调用run方法
        t2.start();
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            System.out.println("继承Thread，重写run方法" + "\n" + "当前线程名称：" + getName());
        }
    }
}


