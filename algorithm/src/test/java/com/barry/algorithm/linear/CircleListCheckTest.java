package com.barry.algorithm.linear;

public class CircleListCheckTest {
    public static void main(String[] args) {
        Node<String> first = new Node<>("aa", null);
        Node<String> second = new Node<>("bb", null);
        Node<String> third = new Node<>("cc", null);
        Node<String> fourth = new Node<>("dd", null);
        Node<String> fifth = new Node<>("ee", null);
        Node<String> six = new Node<>("ff", null);
        Node<String> seven = new Node<>("gg", null);

        // 完成结点之间的指向
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = six;
        six.next = seven;
        // 产生环
        seven.next = third;

        // 判断链表是否有环
        boolean circle = isCircle(first);
        System.out.println("first链表中是否有环：" + circle);
    }

    /**
     * 判断链表中是否有环
     *
     * @param first 链表中的首结点
     * @return true为有环，flase为无环
     */
    public static Boolean isCircle(Node<String> first) {
        // 定义两个指针
        Node<String> fast = first;
        Node<String> slow = first;
        // 遍历链表，如果快慢指针指向了同一个结点，那么证明有环
        while (fast != null && fast.next != null) {
            // 变换fast的值和slow的值
            fast = fast.next.next;
            slow = slow.next;
            if (fast.equals(slow)) {
                return true;
            }
        }
        return false;
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
