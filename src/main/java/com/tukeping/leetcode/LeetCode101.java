package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=101 lang=java
 *
 * [101] 对称二叉树
 *
 * https://leetcode-cn.com/problems/symmetric-tree/description/
 *
 * algorithms
 * Easy (49.23%)
 * Likes:    631
 * Dislikes: 0
 * Total Accepted:    99.7K
 * Total Submissions: 198.4K
 * Testcase Example:  '[1,2,2,3,4,4,3]'
 *
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 * ⁠   1
 * ⁠  / \
 * ⁠ 2   2
 * ⁠/ \ / \
 * 3  4 4  3
 *
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 * ⁠   1
 * ⁠  / \
 * ⁠ 2   2
 * ⁠  \   \
 * ⁠  3    3
 *
 *
 * 说明:
 *
 * 如果你可以运用递归和迭代两种方法解决这个问题，会很加分。
 *
 */

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author tukeping
 * @date 2020/2/29
 **/
public class LeetCode101 {

    /**
     * 说明:
     * 如果你可以运用递归和迭代两种方法解决这个问题，会很加分。
     */

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirrorLoop(root.left, root.right);
    }

    private boolean isMirrorLoop(TreeNode t1, TreeNode t2) {
        Queue<TreeNode> Q = new LinkedList<>();
        Q.add(t1);
        Q.add(t2);

        while (!Q.isEmpty()) {
            TreeNode x1 = Q.poll();
            TreeNode x2 = Q.poll();

            if (x1 == null && x2 == null) continue;
            if (x1 == null || x2 == null) return false;
            if (x1.val != x2.val) return false;

            Q.add(x1.left);
            Q.add(x2.right);

            Q.add(x1.right);
            Q.add(x2.left);
        }

        return true;
    }

    private boolean isMirrorRecursion(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;

        return (t1.val == t2.val)
                && isMirrorRecursion(t1.left, t2.right)
                && isMirrorRecursion(t1.right, t2.left);
    }

    /**
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     *     1
     *    / \
     *   2   2
     *  / \ / \
     * 3  4 4  3
     */
    @Test
    public void test1() {
        TreeNode root = TreeNodeHelper.build(1, 2, 2, 3, 4, 4, 3);
        boolean isSymmetric = isSymmetric(root);
        assertTrue(isSymmetric);
    }

    /**
     * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
     *     1
     *    / \
     *   2   2
     *    \   \
     *    3    3
     */
    @Test
    public void test2() {
        TreeNode root = TreeNodeHelper.build(1, 2, 2, null, 3, null, 3);
        boolean isSymmetric = isSymmetric(root);
        assertFalse(isSymmetric);
    }

    @Test
    public void test3() {
        TreeNode root = TreeNodeHelper.build(2, 3, 3, 4, 5, 5, 4, null, null, 8, 9, null, null, 9, 8);
        boolean isSymmetric = isSymmetric(root);
        assertFalse(isSymmetric);
    }
}
