package com.barry.algorithm.tree;

public class BinaryTree<Key extends Comparable<Key>, Value> {
    // 记录根结点
    private Node<Key, Value> root;
    // 记录树中元素的个数
    private int N;

    private class Node<Key, Value> {
        // 存储键
        public Key key;
        // 存储键
        private Value value;
        // 记录左子结点
        public Node<Key, Value> left;
        // 记录右子结点
        public Node<Key, Value> right;

        public Node(Key key, Value value, Node<Key, Value> left, Node<Key, Value> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    // 获取树中元素的个数
    public int size() {
        return N;
    }

    // 向树中添加元素key-value
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    // 向指定的树x中添加key-value,并返回添加元素后新的树
    public Node<Key, Value> put(Node<Key, Value> x, Key key, Value value) {
        // 如果x子树为空
        if (x == null) {
            N++;
            return new Node(key, value, null, null);
        }
        // 如果x子树不为空
        // 比较x结点的键和key的大小：
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            // 如果key大于x结点的键，则继续找x结点的右子树
            x.right = put(x.right, key, value);
        } else if (cmp < 0) {
            // 如果key小于x结点的键，则继续找x结点的左子树
            x.left = put(x.left, key, value);
        } else {
            // 如果key等于x结点的键，则替换x结点的值为value
            x.value = value;
        }
        return x;
    }

    // 查询树中指定key对应的value
    public Value get(Key key) {
        return get(root, key);
    }

    // 从指定的树中，查找key对应的value
    public Value get(Node<Key, Value> x, Key key) {
        // 如果x子树为空
        if (x == null) {
            return null;
        }
        // 如果x子树不为空
        // 比较key和x结点的键的大小
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            // 如果key大于x结点的键，则继续找x结点的右子树
            return get(x.right, key);
        } else if (cmp < 0) {
            // 如果key小于x结点的键，则继续找x结点的左子树
            return get(x.left, key);
        } else {
            // 如果key等于x结点的键，就找到了键为key的结点，只需要返回x结点的值即可
            return x.value;
        }
    }

    // 删除树中key对应的value
    public void delete(Key key) {
        delete(root, key);
    }

    // 删除指定树x中的key对应的value，并返回删除后的新树
    public Node<Key, Value> delete(Node<Key, Value> x, Key key) {
        // x树为null
        if (x == null) {
            return null;
        }
        // x树不为null
        // 比较key和x结点的键的大小
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            // 如果key大于x结点的键，则继续找x结点的右子树
            x.right = delete(x.right, key);
        } else if (cmp < 0) {
            // 如果key小于x结点的键，则继续找x结点的左子树
            x.left = delete(x.left, key);
        } else {
            // 如果key等于x结点的键，完成真正的删除结点动作，要删除的结点就是x

            // 让元素个数-1
            N--;

            // 得找到右子树中最小的结点
            if (x.right == null) {
                return x.left;
            }
            if (x.left == null) {
                return x.right;
            }

            Node minNode = x.right;
            while (minNode.left != null) {
                minNode = minNode.left;
            }

            // 删除右子树中最小的结点
            Node n = x.right;
            while (n.left != null) {
                if (n.left.left == null) {
                    n.left = null;
                } else {
                    // 变换n结点即可
                    n = n.left;
                }
            }

            // 让x结点的左子树成为minNode的左子树
            minNode.left = x.left;
            // 让x结点的右子树成为minNode的右子树
            minNode.right = x.right;
            // 让x结点的父结点指向minNode
            x = minNode;
        }
        return x;
    }
}
