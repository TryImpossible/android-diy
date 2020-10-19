package com.barry.designpartern.Singleton;

public class Singleton1 {
    private static Singleton1 instance = null;
    private Singleton1() {}

    public static Singleton1 getInstance() {
        if (instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }
}
