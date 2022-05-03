package com.barry.algorithm.sort;

import java.util.Arrays;

public class ShellTest {
    public static void main(String[] args) {
        Integer[] arr = {9, 1, 2, 5, 7, 4, 8, 6, 3, 5};
        System.out.println("排序前:" + Arrays.toString(arr));
        Shell.sort(arr);
        System.out.println("排序后:" + Arrays.toString(arr));
    }
}
