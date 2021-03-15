package com.barry.java.StringBuffer;

import java.util.regex.Pattern;

/**
 * 它和 StringBuffer 之间的最大不同在于 StringBuilder 的方法不是线程安全的（不能同步访问）。
 * <p>
 * 由于 StringBuilder 相较于 StringBuffer 有速度优势，所以多数情况下建议使用 StringBuilder 类。
 * 然而在应用程序要求线程安全的情况下，则必须使用 StringBuffer 类
 */
public class Tester1 {
    public static void main(String args[]) {
        StringBuilder sButter = new StringBuilder("菜鸟教程官网");
        sButter.append("www");
        sButter.append(".runoob");
        sButter.append(".com");
        System.out.println(sButter);


        /// append() 追加字符
        /// reverse() 反转字符
        /// insert() 插入
        /// replace()
    }
}
