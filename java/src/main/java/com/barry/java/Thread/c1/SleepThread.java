package com.barry.java.Thread.c1;

/**
 * 睡眠线程
 */
public class SleepThread {
    public static void main(String[] args) throws InterruptedException{
        //依靠异常处理机制，3s后打断线程的休眠

        Thread t = new Thread(new MyThread());
        t.setName("t");
        t.start();

        Thread.sleep(3000);
//        TimeUnit.SECONDS.sleep(3);
        // 打断t的休眠
        t.interrupt();
    }

    static class MyThread implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "-->" + i);
            }
        }
    }
}
