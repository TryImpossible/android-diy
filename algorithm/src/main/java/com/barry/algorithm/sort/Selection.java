package com.barry.algorithm.sort;

import java.util.Arrays;

/**
 * 选择排序
 */
public class Selection {
    /**
     * 对数组a的元素进行排序
     *
     * @param a
     */
    public static void sort(Comparable[] a) {
        for (int i = 0; i <= a.length - 2; i++) {
//            System.out.println("第" + (a.length - i) + "次循环");
            // 定义一个变量，记录最小元素所有的索引，默认为参与选择排序的第一个元素所在的位置
            int minIndex = i;
            for (int j = i; j < a.length; j++) {
                // 需要比较最小索引minIndex处的值和j索引的值
                if (greater(a[minIndex], a[j])) {
                    minIndex = j;
                }
//                System.out.println("a is " + Arrays.toString(a));
            }
            // 交换最小元素所在索引minIndex处的值和索引i处的值
            exch(a, i, minIndex);
        }
    }

    /**
     * 比较v元素是否大于w元素
     *
     * @param v
     * @param w
     * @return
     */
    private static boolean greater(Comparable v, Comparable w) {
        return v.compareTo(w) > 0;
    }

    /**
     * 交换a数组中，索引i和索引j处的值
     *
     * @param a
     * @param i
     * @param j
     */
    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
