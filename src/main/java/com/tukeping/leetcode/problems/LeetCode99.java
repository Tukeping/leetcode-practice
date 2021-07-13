package com.tukeping.leetcode.problems;


import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author tukeping
 * @date 2020/4/11
 **/
public class LeetCode99 {

    private TreeNode x, y, pre;

    public void recoverTree3(TreeNode root) {
        inorder(root);
        swap(x, y);
    }

    private void inorder(TreeNode root) {
        if (root == null) return;

        inorder(root.left);
        if (pre != null && pre.val > root.val) {
            x = root;
            if (y == null) y = pre;
            else return;
        }
        pre = root;
        inorder(root.right);
    }

    public void recoverTree2(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode x = null, y = null, pred = null;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }
            root = stack.removeLast();
            if (pred != null && root.val < pred.val) {
                y = root;
                if (x == null) x = pred;
                else break;
            }
            pred = root;
            root = root.right;
        }

        swap(x, y);
    }

    private static void morrisInorder(TreeNode root) {
        TreeNode cur = root;
        TreeNode mostRight;

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

    public static void morrisInOrder(TreeNode root) {
        TreeNode cur = root;
        TreeNode pre;
        while (cur != null) {
            if (cur.left == null) {
                System.out.println(cur.val);
                cur = cur.right; // move to next right node
            } else {  // has a left subtree
                pre = cur.left;
                while (pre.right != null) {  // find rightmost
                    pre = pre.right;
                }

                pre.right = cur;  // put cur after the pre node
                TreeNode temp = cur;  // store cur node
                cur = cur.left;  // move cur to the top of the new tree
                temp.left = null;   // original cur left be null, avoid infinite loops
            }
        }
    }

    public static void morrisPreorder(TreeNode root) {
        TreeNode cur = root;
        TreeNode mostRight;

        while (cur != null) {
            mostRight = cur.left;

            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur)
                    mostRight = mostRight.right;

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
    }

    public static void morrisPostorder(TreeNode root) {
        TreeNode cur = root;
        TreeNode mostRight;

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

        printEdge(root);
    }

    private static void printEdge(TreeNode node) {
        TreeNode tail = reverseEdge(node);
        TreeNode cur = tail;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    private static TreeNode reverseEdge(TreeNode node) {
        TreeNode pre = null;
        TreeNode next;
        while (node != null) {
            next = node.right;
            node.right = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

    private void morrisPure(TreeNode root) {
        TreeNode predecessor;

        while (root != null) {
            if (root.left != null) {
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root)
                    predecessor = predecessor.right;

                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                } else {
                    predecessor.right = null;
                    root = root.right;
                }
            } else { // root.right
                root = root.right;
            }
        }
    }

    public void recoverTree(TreeNode root) {
        morris(root);
        swap(x, y);
    }

    private void morris(TreeNode root) {
        TreeNode pred = null, predecessor;

        while (root != null) {
            if (root.left != null) {
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root)
                    predecessor = predecessor.right;

                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                } else {
                    if (pred != null && root.val < pred.val) {
                        y = root;
                        if (x == null) x = pred;
                    }
                    pred = root;

                    predecessor.right = null;
                    root = root.right;
                }
            } else {
                if (pred != null && root.val < pred.val) {
                    y = root;
                    if (x == null) x = pred;
                }
                pred = root;

                root = root.right;
            }
        }
    }

    public void swap(TreeNode a, TreeNode b) {
        int tmp = a.val;
        a.val = b.val;
        b.val = tmp;
    }

    /**
     * 输入: [1,3,null,null,2]
     *
     *    1
     *   /
     *  3
     *   \
     *    2
     * 输出: [3,1,null,null,2]
     *
     *    3
     *   /
     *  1
     *   \
     *    2
     */
    @Test
    public void test1() {
        TreeNode root = TreeNodeHelper.build(1, 3, null, null, 2);
        recoverTree(root);
        TreeNodeHelper.check(root, 3, 1, null, null, 2);
    }

    /**
     * 输入: [3,1,4,null,null,2]
     *   3
     *  / \
     * 1   4
     *    /
     *   2
     * 输出: [2,1,4,null,null,3]
     *   2
     *  / \
     * 1   4
     *    /
     *   3
     */
    @Test
    public void test2() {
        TreeNode root = TreeNodeHelper.build(3, 1, 4, null, null, 2);
        recoverTree(root);
        TreeNode expect = TreeNodeHelper.build(2, 1, 4, null, null, 3);
        TreeNodeHelper.check(root, expect);
    }
}
