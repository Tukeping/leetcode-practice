package com.tukeping.tools.tree;

import com.tukeping.leetcode.structures.tree.Node;

import java.util.LinkedList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/26
 **/
public class NodeHelper {

    public static Node build(Integer... a) {
        LinkedList<Node> queue = new LinkedList<>();
        Node root = new Node(a[0]);
        queue.addLast(root);
        build0(a, 1, queue);
        return root;
    }

    private static void build0(Integer[] a, int idx, LinkedList<Node> queue) {
        if (idx >= a.length) return;
        if (queue.isEmpty()) return;

        Node root = queue.getFirst();

        root.left = (a[idx] == null) ? null : new Node(a[idx]);

        if (idx + 1 == a.length) {
            root.right = null;
        } else {
            root.right = (a[idx + 1] == null) ? null : new Node(a[idx + 1]);
        }

        queue.removeFirst();

        if (root.left != null)
            queue.addLast(root.left);

        if (root.right != null)
            queue.addLast(root.right);

        build0(a, idx + 2, queue);
    }

    public static void check(Node actual, Node expect) {
        if (actual == null && expect == null) return;

        if (actual == null) {
            throw new AssertionError(String.format("actual not match expect: %d", expect.val));
        } else if (expect == null) {
            throw new AssertionError(String.format("expect not match actual: %d", actual.val));
        }

        assertThat(actual.val, is(expect.val));

        check(actual.left, expect.left);
        check(actual.right, expect.right);
    }

    public static void check(Node root, Integer... a) {
        LinkedList<Node> queue = new LinkedList<>();
        assertThat(root.val, is(a[0]));
        queue.addLast(root);
        check0(a, 1, queue);
    }

    private static void check0(Integer[] a, int idx, LinkedList<Node> queue) {
        if (idx >= a.length) return;
        if (queue.isEmpty()) return;

        Node root = queue.getFirst();

        if (a[idx] == null) {
            assertNull(root.left);
        } else {
            assertThat(root.left.val, is(a[idx]));
        }

        if (a[idx + 1] == null) {
            assertNull(root.right);
        } else {
            assertThat(root.right.val, is(a[idx + 1]));
        }

        queue.removeFirst();

        if (root.left != null)
            queue.addLast(root.left);
        if (root.right != null)
            queue.addLast(root.right);

        check0(a, idx + 2, queue);
    }
}
