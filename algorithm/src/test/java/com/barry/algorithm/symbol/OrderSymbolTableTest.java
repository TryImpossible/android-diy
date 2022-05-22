package com.barry.algorithm.symbol;

public class OrderSymbolTableTest {
    public static void main(String[] args) {
        // 创建有序符号表对象
        OrderSymbolTable<Integer, String> table = new OrderSymbolTable<>();

        // 测试put方法(插入，替换)
        table.put(1, "张三");
        table.put(2, "李四");
        table.put(4, "赵六");
        table.put(7, "田七");

        // Debug测试
        table.put(3, "王五");
    }
}
