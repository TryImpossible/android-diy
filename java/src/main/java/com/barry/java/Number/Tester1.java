package com.barry.java.Number;

/// 一般地，当需要使用数字的时候，我们通常使用内置数据类型，如：byte、int、long、double 等。
/// Java 语言为每一个内置数据类型提供了对应的包装类。
/// 所有的包装类（Integer、Long、Byte、Double、Float、Short）都是抽象类 Number 的子类
public class Tester1 {
    public static void main(String args[]) {
        Integer x = 5;
        // 自动拆箱
        x = x + 10;
        System.out.println(x);

        /// xxxValue()
        System.out.println("byte 原生数据类型：" + x.byteValue());
        System.out.println("short 原生数据类型：" + x.shortValue());
        System.out.println("int 原生数据类型：" + x.intValue());
        System.out.println("long 原生数据类型：" + x.longValue());
        System.out.println("float 原生数据类型：" + x.floatValue());
        System.out.println("double 原生数据类型：" + x.doubleValue());

        /// compareTo()
        System.out.println(x.compareTo(3));
        System.out.println(x.compareTo(5));
        System.out.println(x.compareTo(15));

        /// equals()
        Integer y = 10;
        Integer z = 5;
        Integer a = 5;
        System.out.println(x.equals(y));
        System.out.println(x.equals(z));
        System.out.println(x.equals(a));

        /// valueOf()
        Integer x1 = Integer.valueOf(9);
        Double c1 = Double.valueOf(5);
        Float a1 = Float.valueOf("80");
        Integer b1 = Integer.valueOf("444", 16);
        System.out.println(x1);
        System.out.println(c1);
        System.out.println(a1);
        System.out.println(b1);
    }
}
