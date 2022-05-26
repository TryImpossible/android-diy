package com.barry.algorithm.tree;

import com.barry.algorithm.linear.Queue;

public class PagerFoldingTest {
    public static void main(String[] args) {
        // 模拟折纸问题，生产树
        Node<String> tree = createTree(3);

        // 遍历树，打印每个结点
        printTree(tree);
    }

    // 通过模拟对折N次纸，产生树
    public static Node<String> createTree(int N) {
        // 定义根结点
        Node<String> root = null;
        for (int i = 0; i < N; i++) {
            // 1.当前是第一次对折
            if (i == 0) {
                root = new Node<>("down", null, null);
                continue;
            }
            // 2.当前不是第一次对折
            // 定义一个辅助队列，通过层序遍历的思想，找到叶子结点，叶子结点添加子结点
            Queue<Node> queue = new Queue<>();
            queue.enqueue(root);

            // 循环遍历
            while (!queue.isEmpty()) {
                // 从队列中弹出一个结点
                Node<String> tmp = queue.dequeue();
                // 如果有左子结点，则把左子结点放入到队列中
                if (tmp.left != null) {
                    queue.enqueue(tmp.left);
                }
                // 如果有右子结点，则把右子结点放入到队列中
                if (tmp.right != null) {
                    queue.enqueue(tmp.right);
                }
                // 如果同时没有左子结点和右子结点，证明该结点是叶子结点，只需要给该结点添加左子结点和右子结点
                if (tmp.left == null && tmp.right == null) {
                    tmp.left = new Node<String>("down", null, null);
                    tmp.right = new Node<String>("up", null, null);
                }
            }

        }
        return root;
    }

    // 打印树中每个结点到控制台
    public static void printTree(Node<String> root) {
        // 需要使用中序遍历完成
        if (root == null) {
            return;
        }

        // 打印左子树的每个结点
        if (root.left != null) {
            printTree(root.left);
        }

        // 打印当前结点
        System.out.print(root.item + " ");

        // 打印右子树的每个结点
        if (root.right != null) {
            printTree(root.right);
        }
    }

    // 结点类
    public static class Node<T> {
        public T item; // 存储元素

        public Node left;
        public Node right;

        public Node(T item, Node left, Node right) {
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }
}
