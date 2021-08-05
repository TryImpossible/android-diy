package com.barry.java.Thread;

public class Tester4 {
    public static void main(String[] args) throws InterruptedException {
        // 更好的终止一个正在执行的线程

        Processor4 p = new Processor4();
        Thread t = new Thread(p);
        t.setName("t5");
        t.start();

        Thread.sleep(5000);
        // 终止
        p.run = false;
    }
}

class Processor4 implements Runnable {
    /**
     * 利用Boolean来控制
     */
    boolean run = true;

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (run) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "-->" + i);
            } else {
                // 结束此方法
                return;
            }
        }
    }
}