package com.barry.java.Thread.c3;

/**
 * 局部变量
 */
public class C2_LocalVariable {
    static ThreadLocal<User> userThreadLocal = new ThreadLocal<>();
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            userThreadLocal.set(new User());
            User user = userThreadLocal.get();
//            User user = new User();
            for (int i = 0; i < 1000000; i++) {
                user.age++;
            }
            System.out.println(user.age);
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            userThreadLocal.set(new User());
            User user = userThreadLocal.get();
//            User user = new User();
            for (int i = 0; i < 1000000; i++) {
                user.age++;
            }
            System.out.println(user.age);
        });
        thread2.start();
    }

    static class User {
        public int age;
    }
}
