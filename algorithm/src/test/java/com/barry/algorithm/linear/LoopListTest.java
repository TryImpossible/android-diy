package com.barry.algorithm.linear;

public class LoopListTest {
    public static void main(String[] args) {
        // 构建结点
        Node<Integer> first = new Node<>(1, null);
        Node<Integer> second = new Node<>(2, null);
        Node<Integer> third = new Node<>(3, null);
        Node<Integer> fourth = new Node<>(4, null);
        Node<Integer> fifth = new Node<>(5, null);
        Node<Integer> six = new Node<>(6, null);
        Node<Integer> seven = new Node<>(7, null);

        // 构建单链表
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = six;
        six.next = seven;

        // 构建循环链表，让最后一个结点指向第一个结点
        seven.next = first;
    }

    /**
     * 结点类
     */
    private static class Node<T> {
        // 存储数据
        T item;
        // 下一个结点
        Node next;

        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }
}
