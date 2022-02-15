package com.barry.algorithm.sort;

import java.util.Arrays;

public class InsertionTest {
    public static void main(String[] args) {
        Integer[] arr = {4, 3, 2, 10, 12, 1, 5, 6};
        System.out.println("排序前:" + Arrays.toString(arr));
        Insertion.sort(arr);
        System.out.println("排序后:" + Arrays.toString(arr));
    }
}
