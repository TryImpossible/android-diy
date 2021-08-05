package com.barry.java.Thread;

public class TestInterrupt {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }

    private static class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                System.out.println("进入睡眠状态");
                sleep(10000);
                System.out.println("睡眠完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("run方法执行完毕");
        }
    }
}
