package com.barry.algorithm.sort;

import java.util.Arrays;

public class SelectionTest {
    public static void main(String[] args) {
        Integer[] arr = {4, 6, 8, 7, 9, 2, 10, 1};
        System.out.println("排序前:" + Arrays.toString(arr));
        Bubble.sort(arr);
        System.out.println("排序后:" + Arrays.toString(arr));
    }
}
