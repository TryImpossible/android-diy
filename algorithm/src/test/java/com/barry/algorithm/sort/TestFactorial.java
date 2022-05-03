package com.barry.algorithm.sort;

public class TestFactorial {
    public static void main(String[] args) {
        // 示N的阶乘
        long result = factorial(5);
        System.out.println("result = " + result);
    }

    // 求n的阶乘
    public static long factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}
