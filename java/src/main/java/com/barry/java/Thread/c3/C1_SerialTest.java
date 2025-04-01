package com.barry.java.Thread.c3;

/**
 * 线程的有序性（指令重排）
 */
public class C1_SerialTest {
    static C1_SerialTest instance;
//    static Boolean isInit = false;
    static volatile Boolean isInit = false;

    void doSomething() {
        System.out.println("doSomething...");
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000000; i++) {
            isInit = false;
            instance = null;

            // 线程1
            Thread thread1 = new Thread(() -> {
                instance = new C1_SerialTest();
                isInit = true;
//                synchronized (isInit) {
//                    instance = new SerialTest();
//                    isInit = true;
//                }
            });

            // 线程2
            Thread thread2 = new Thread(() -> {
                if (isInit) {
                    instance.doSomething();
                }
//                synchronized (isInit) {
//                    if (isInit) {
//                        instance.doSomething();
//                    }
//                }
            });

            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();
        }
    }
}
