package com.barry.java.Thread;

public class DeadLock {
    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = new Object();
        Thread t1 = new Thread(new T1(obj1, obj2));
        Thread t2 = new Thread(new T1(obj1, obj2));
        t1.start();
        t2.start();
    }
}

class T1 implements Runnable {
    private Object obj1;
    private Object obj2;

    public T1(Object obj1, Object obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    @Override
    public void run() {
        synchronized (obj1) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj2) {}
        }
    }
}

class T2 implements Runnable {
    private Object obj1;
    private Object obj2;

    public T2(Object obj1, Object obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    @Override
    public void run() {
        synchronized (obj2) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj1) {}
        }
    }
}