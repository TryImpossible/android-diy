package com.barry.java.Stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Java 8 API添加了一个新的抽象称为流Stream，可以让你以一种声明的方式处理数据
 * 这种风格将要处理的元素集合看作一种流， 流在管道中传输， 并且可以在管道的节点上进行处理， 比如筛选， 排序，聚合等。
 */
public class Tester1 {

    public static void main(String args[]) {
        // stream() - 为集合创建串行流
        // parallelStream() - 为集合创建并行流


        // forEach
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);

        // map
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> squaresList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());

        // filter
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

        // limit
        Random random1 = new Random();
        random1.ints().limit(10).forEach(System.out::println);

        // sorted
        Random random2 = new Random();
        random2.ints().limit(10).sorted().forEach(System.out::println);

        // 并行（paraller）程序
        List<String> strings1 = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        long count = strings.parallelStream().filter(string -> string.isEmpty()).count();

        // Collectors
        List<String> strings2 = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<String> filtered2 = strings2.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

        System.out.println("筛选列表: " + filtered2);
        String mergedString = strings2.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);

        // 统计
        List<Integer> numbers2 = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        IntSummaryStatistics statis = numbers2.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println("列表中最大的数：" + statis.getMax());
        System.out.println("列表中最小的数：" + statis.getMin());
        System.out.println("所有数之和：" + statis.getSum());
        System.out.println("平均数：" + statis.getAverage());
    }
}
