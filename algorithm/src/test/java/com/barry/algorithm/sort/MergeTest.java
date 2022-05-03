package com.barry.algorithm.sort;

import java.util.Arrays;

public class MergeTest {
    public static void main(String[] args) {
        Integer[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        System.out.println("排序前:" + Arrays.toString(arr));
        Merge.sort(arr);
        System.out.println("排序后:" + Arrays.toString(arr));
    }
}
