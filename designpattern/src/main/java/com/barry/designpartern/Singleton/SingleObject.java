package com.barry.designpartern.Singleton;

public class SingleObject {
    // 创建一个SingleObject的对象
    private static SingleObject instance = new SingleObject();
    // 让构造函数为 private, 这样该类不会被实例化
    private SingleObject() {}
    // 获取唯一可用对象
    public static SingleObject getInstance() {
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello World");
    }
}
