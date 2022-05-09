package com.barry.algorithm.linear;

import java.util.Iterator;

/**
 * 双向链表
 */
public class TwoWayLinkList<T> implements Iterable<T> {
    // 记录头结点
    private Node head;
    // 记录尾结点
    private Node last;
    // 记录链表长度
    private int N;

    // 结点类
    private class Node<T> {
        // 存储数据
        public T item;
        // 上一个结点
        public Node<T> pre;
        // 下一个结点
        public Node<T> next;

        public Node(T item, Node pre, Node next) {
            this.item = item;
            this.pre = pre;
            this.next = next;
        }
    }

    public TwoWayLinkList() {
        // 初始化头结点和尾结点
        this.head = new Node(null, null, null);
        this.last = null;
        // 初始化元素个数
        this.N = 0;
    }

    // 清空链表
    public void clear() {
        this.head.next = null;
        this.head.pre = null;
        this.head.item = null;
        this.last = null;
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

    // 获取第一个元素
    public T getFirst() {
        if (isEmpty()) {
            return null;
        }
        return (T) head.next.item;
    }

    // 获取最后一个元素
    public T getLast() {
        if (isEmpty()) {
            return null;
        }
        return (T) last.item;
    }

    // 向链表中添加元素t
    public void insert(T t) {
        if (isEmpty()) {
            // 如果链表为空

            // 创建新的结点
            Node<T> newNode = new Node(t, head, null);
            // 让新结点成为尾结点
            last = newNode;
            // 让头结点指向尾结点
            head.next = last;
        } else {
            // 如果链表不为空
            Node oldLast = last;
            // 创建新的结点
            Node<T> newNode = new Node(t, oldLast, null);
            // 让当前的尾结点指向新结点
            oldLast.next = newNode;
            // 让新结点成为尾结点
            last = newNode;
        }
        // 元素个数加1
        N++;
    }

    // 向指定位置i处，添加元素t
    public void insert(int i, T t) {
        // 找到i位置的前一个结点
        Node pre = head;
        for (int index = 0; index < i; index++) {
            pre = pre.next;
        }
        // 找到i位置的结点
        Node curr = pre.next;
        // 创建新结点
        Node newNode = new Node(t, pre, curr);
        // 找到i位置的前一个结点的下一个结点变成新结点
        pre.next = newNode;
        // 让i位置的前一个结点变为新结点
        curr.pre = newNode;
        // 元素个数加1
        N++;
    }

    // 获取指定位置i处的元素
    public T get(int i) {
        Node n = head.next;
        for (int index = 0; index < i; index++) {
            n = n.next;
        }
        return (T) n.item;
    }


    // 查找元素t在链表中第一次出现的位置
    public int indexOf(T t) {
        Node n = head;
        for (int i = 0; n.next != null; i++) {
            n = n.next;
            if (n.item.equals(t)) {
                return i;
            }
        }
        return -1;
    }

    // 删除指定位置i处的元素，并返回被删除的元素
    public T remove(int i) {
        // 找到i位置的前一个结点
        Node pre = head;
        for (int index = 0; index < i; index++) {
            pre = pre.next;
        }
        // 找到i位置的结点
        Node curr = pre.next;
        // 找到i位置的下一个结点
        Node nextNode = curr.next;
        // 让i位置的上一个结点的下一个结点变为i位置的下一个结点
        pre.next = nextNode;
        // 让i位置的下一个结点的上一个结点变为i位置的前一个结点
        nextNode.pre = pre;
        // 元素的个数减1
        N--;
        return (T) curr.item;
    }

    @Override
    public Iterator<T> iterator() {
        return new TWLIterator();
    }

    private class TWLIterator implements Iterator<T> {

        private Node n;

        public TWLIterator() {
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
