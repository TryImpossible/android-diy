package com.barry.algorithm.sort;

import java.util.Arrays;

public class QuickTest {
    public static void main(String[] args) {
        Integer[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 8};
        System.out.println("排序前:" + Arrays.toString(arr));
        Merge.sort(arr);
        System.out.println("排序后:" + Arrays.toString(arr));
    }
}
