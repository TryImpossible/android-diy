package com.barry.java.Thread;

public class Tester1 {
    public static void main(String[] args) {
        Thread t1 = new Processor();
        t1.start();

        Thread t2 = new Thread(new Processor1());
        t2.start();
    }
}

class Processor extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println("继承Thread，重写run方法");
    }
}

class Processor1 implements Runnable {
    @Override
    public void run() {
        System.out.println("实现Runnable，实现run方法");
    }
}