package com.barry.java.Thread.c3;

/**
 * 线程的可见性
 * 对一个变量解锁之前 ，必须先把此变量同步到主存中，这样解锁后，线程就可以访问到被修改后值。
 * 所以synchronized锁住的对象，其值具有可见性
 */
public class C1_VisibilityTest {
    static Boolean always = true;
    // volatile 保证可见性，禁止指令重排（保证有序性）
//    static volatile Boolean  always = true;

    public static void main(String[] args) throws InterruptedException {
        // 线程1
        new Thread(() -> {
            while (always) {
                // 注意 不能有输出 System.out.println("执行...")

//                synchronized (always) {
//
//                }
            }
        }).start();

        Thread.sleep(2000);

        // 线程2

        always = false;
    }

}
