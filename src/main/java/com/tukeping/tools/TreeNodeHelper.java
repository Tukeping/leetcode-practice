package com.tukeping.tools;

import com.tukeping.leetcode.structures.TreeNode;
import org.junit.Test;

import java.util.LinkedList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/2/18
 **/
public class TreeNodeHelper {

    public static TreeNode find(TreeNode root, Integer val) {
        if (root == null) return null;

        if (root.val == val) return root;

        TreeNode left = find(root.left, val);
        if (left != null) return left;

        TreeNode right = find(root.right, val);
        if (right != null) return right;

        return null;
    }

    public static TreeNode build(Integer... a) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(a[0]);
        queue.addLast(root);
        build0(a, 1, queue);
        return root;
    }

    private static void build0(Integer[] a, int idx, LinkedList<TreeNode> queue) {
        if (idx >= a.length) return;
        if (queue.isEmpty()) return;

        TreeNode root = queue.getFirst();

        root.left = (a[idx] == null) ? null : new TreeNode(a[idx]);

        if (idx + 1 == a.length) {
            root.right = null;
        } else {
            root.right = (a[idx + 1] == null) ? null : new TreeNode(a[idx + 1]);
        }

        queue.removeFirst();

        if (root.left != null)
            queue.addLast(root.left);

        if (root.right != null)
            queue.addLast(root.right);

        build0(a, idx + 2, queue);
    }

    public static void check(TreeNode root, Integer... a) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        assertThat(root.val, is(a[0]));
        queue.addLast(root);
        check0(a, 1, queue);
    }

    public static void check(TreeNode actual, TreeNode expect) {
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

    private static void check0(Integer[] a, int idx, LinkedList<TreeNode> queue) {
        if (idx >= a.length) return;
        if (queue.isEmpty()) return;

        TreeNode root = queue.getFirst();

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

    @Test
    public void test() {
        TreeNode root = build(3, 9, 20, null, null, 15, 7);
        check(root, 3, 9, 20, null, null, 15, 7);
    }
}
