package com.barry.algorithm.linear;

public class SequenceListTest2 {
    public static void main(String[] args) {
        // 测试扩容
        SequenceList<String> sl = new SequenceList<>(3);
        sl.insert("张三");
        sl.insert("李四");
        sl.insert("王五");
        sl.insert("赵六");
    }
}
