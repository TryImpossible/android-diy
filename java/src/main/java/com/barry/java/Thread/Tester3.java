package com.barry.java.Thread;

public class Tester3 {
    public static void main(String[] args) throws InterruptedException{
        //依靠异常处理机制，3s后打断线程的休眠

        Thread t = new Thread(new Processor3());
        t.setName("t");
        t.start();

        Thread.sleep(3000);
        // 打断t的休眠
        t.interrupt();
    }
}

class Processor3 implements Runnable {
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