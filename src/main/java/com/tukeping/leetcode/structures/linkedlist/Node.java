package com.tukeping.leetcode.structures.linkedlist;

/**
 * @author tukeping
 * @date 2020/3/26
 **/
public class Node {
    public int val;
    public Node next;
    public Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
