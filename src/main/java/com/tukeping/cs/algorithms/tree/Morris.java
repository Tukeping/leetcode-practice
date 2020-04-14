package com.tukeping.cs.algorithms.tree;

import com.tukeping.leetcode.structures.tree.Node;

/**
 * @author tukeping
 * @date 2020/4/2
 **/
public class Morris {

    /** time: O(n) space: O(h), h is tree height **/
    public static void morrisPreorder(Node head) {
        if (head == null) {
            return;
        }

        Node cur = head;
        Node mostRight;

        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    System.out.print(cur.val + " ");
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            } else {
                System.out.print(cur.val + " ");
            }
            cur = cur.right;
        }
        System.out.println();
    }

    public static void morrisInorder(Node head) {
        Node cur = head;
        Node mostRight;

        while (cur != null) {
            mostRight = cur.left;

            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur)
                    mostRight = mostRight.right;

                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }

            System.out.print(cur.val + " ");
            cur = cur.right;
        }
    }

    public static void morrisPostorder(Node head) {
        if (head == null) {
            return;
        }

        Node cur = head;
        Node mostRight;

        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        printEdge(head);
        System.out.println();
    }

    public static void printEdge(Node node) {
        Node tail = reverseEdge(node);
        Node cur = tail;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    public static Node reverseEdge(Node node) {
        Node pre = null;
        Node next;
        while (node != null) {
            next = node.right;
            node.right = pre;
            pre = node;
            node = next;
        }
        return pre;
    }
}
