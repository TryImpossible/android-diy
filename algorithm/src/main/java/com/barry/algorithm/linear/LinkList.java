package com.barry.algorithm.linear;

import java.util.Iterator;

public class LinkList<T> implements Iterable<T> {
    // 记录头结点
    private Node head;
    // 记录链表长度
    private int N;

    // 结点类
    private class Node<T> {
        // 存储数据
        T item;
        // 下一个结点
        Node next;

        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public LinkList() {
        // 初始化头结点
        this.head = new Node(null, null);
        // 初始化元素个数
        this.N = 0;
    }

    // 清空链表
    public void clear() {
        head.next = null;
        this.N = 0;
    }

    // 获取链表长度
    public int length() {
        return N;
    }

    // 判断链表是否为空
    public boolean isEmpty() {
        return N == 0;
    }

    // 获取指定位置i处的元素
    public T get(int i) {
        // 通过循环，从头结点开始往后找，依次找i次，就可以找到对应的元素
        Node<T> n = head.next;
        for (int index = 0; index < i; index++) {
            n = n.next;
        }
        return n.item;
    }

    // 向链表中添加元素t
    public void insert(T t) {
        // 找到当前最后一个结点
        Node<T> n = head;
        while (n.next != null) {
            n = n.next;
        }
        // 创建新结点，保存元素t
        Node<T> newNode = new Node(t, null);
        // 让当前最后一个结点指向新结点
        n.next = newNode;
        // 元素的个数+1
        N++;
    }

    // 向指定位置i处，添加元素t
    public void insert(int i, T t) {
        // 找到i位置前一个结点
        Node<T> pre = head;
        for (int index = 0; index <= i - 1; index++) {
            pre = pre.next;
        }
        // 找到i位置的结点
        Node<T> curr = pre.next;
        // 创建新结点，并且新结点需要指向原来i位置的结点
        Node<T> newNode = new Node(t, curr);
        // 原来i位置的前一个结点指向新结点即可
        pre.next = newNode;
        // 元素的个数+1
        N--;
    }

    // 删除指定位置i处的元素，并返回被删除的元素
    public T remove(int i) {
        // 找到i位置的前一个结点
        Node<T> pre = head;
        for (int index = 0; index <= i - 1; index++) {
            pre = pre.next;
        }
        // 要找到i位置的结点
        Node<T> curr = pre.next;
        // 找到i位置的下一个结点
        Node<T> nextNode = curr.next;
        // 前一个结点指向下一个结点
        pre.next = nextNode;
        // 元素个数-1
        N--;
        return curr.item;
    }

    // 查找元素t在链表中第一次出现的位置
    public int indexOf(T t) {
        // 从头结点开始，依次找到每一个结点，取出item，和t比较，如果相同，就找到了
        Node<T> n = head;
        for (int i = 0; n.next != null; i++) {
            n = n.next;
            if (n.item.equals(t)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new LIterator();
    }

    private class LIterator implements Iterator<T> {
        private Node n;

        public LIterator() {
            this.n = head;
        }

        @Override
        public boolean hasNext() {
            return n.next != null;
        }

        @Override
        public T next() {
            n = n.next;
            return (T) n.item;
        }

        @Override
        public void remove() {
        }
    }
}
