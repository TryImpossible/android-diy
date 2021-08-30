package com.barry.java.Thread;

public class TestThreadLocal {
    public static void main(String[] args) {
        test1();
        test2();
    }

    /**
     * ThreadLocal 线程隔离
     */
    private static void test1() {
        ///
        /// WeakReference造成内存泄露
        /// 这就导致了一个问题，ThreadLocal在没有外部强引用时，发生GC时会被回收，
        /// 如果创建ThreadLocal的线程一直持续运行，那么这个Entry对象中的value就有可能一直得不到回收，发生内存泄露
        ///
        /// 解决：代码的最后使用remove就好了
        ///
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("Java");
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("我要学习" + threadLocal.get());
            }
        }).start();
    }

    /**
     * InheritableThreadLocal 共享线程
     */
    private static void test2() {
        ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
        threadLocal.set("Android");
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("我要学习" + threadLocal.get());
            }
        }).start();
    }
}
