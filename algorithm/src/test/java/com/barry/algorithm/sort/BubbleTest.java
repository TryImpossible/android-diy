package com.barry.algorithm.sort;

import java.util.Arrays;

public class BubbleTest {
    public static void main(String[] args) {
        Integer[] arr = {4, 5, 6, 3, 2, 1};
        System.out.println("排序前:" + Arrays.toString(arr));
        Bubble.sort(arr);
        System.out.println("排序后:" + Arrays.toString(arr));
    }
}
