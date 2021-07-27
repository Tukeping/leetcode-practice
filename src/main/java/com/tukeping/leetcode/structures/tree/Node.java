package com.tukeping.leetcode.structures.tree;

/**
 * @author tukeping
 * @date 2020/3/26
 **/
public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
