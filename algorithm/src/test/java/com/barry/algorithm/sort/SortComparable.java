package com.barry.algorithm.sort;

import java.util.ArrayList;

public class SortComparable {
    // 调用不同的测试方法，完成测试
    public static void main(String[] args) {
        // 1.创建一个ArrayList集合
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 100000; i > 0; i--) {
            list.add(Integer.valueOf(i));
        }
        // 2.把ArrayList集合转成数组
        Integer[] a = new Integer[list.size()];
        list.toArray(a);
        // 3.调用测试代码完成测试
//        testInsertion(a); // 17329毫秒
//        testShell(a); // 30毫秒
        testMerge(a); // 59毫秒
    }

    // 测试插入排序
    public static void testInsertion(Comparable[] a) {
        // 1.获取执行之前的时间
        long start = System.currentTimeMillis();
        // 2.执行算法代码
        Insertion.sort(a);
        // 3.获取执行之后的时间
        long end = System.currentTimeMillis();
        // 4.算出程序执行的时间并输出
        System.out.println("插入排序执行的时间为：" + (end - start) + "毫秒");
    }

    // 测试希尔排序
    public static void testShell(Comparable[] a) {
        // 1.获取执行之前的时间
        long start = System.currentTimeMillis();
        // 2.执行算法代码
        Shell.sort(a);
        // 3.获取执行之后的时间
        long end = System.currentTimeMillis();
        // 4.算出程序执行的时间并输出
        System.out.println("希尔排序执行的时间为：" + (end - start) + "毫秒");
    }

    // 测试归并排序
    public static void testMerge(Comparable[] a) {
        // 1.获取执行之前的时间
        long start = System.currentTimeMillis();
        // 2.执行算法代码
        Merge.sort(a);
        // 3.获取执行之后的时间
        long end = System.currentTimeMillis();
        // 4.算出程序执行的时间并输出
        System.out.println("归并排序执行的时间为：" + (end - start) + "毫秒");
    }
}
