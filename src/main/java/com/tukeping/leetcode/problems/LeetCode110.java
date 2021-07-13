package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=110 lang=java
 *
 * [110] 平衡二叉树
 *
 * https://leetcode-cn.com/problems/balanced-binary-tree/description/
 *
 * algorithms
 * Easy (49.35%)
 * Likes:    251
 * Dislikes: 0
 * Total Accepted:    55.9K
 * Total Submissions: 110.4K
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 *
 *
 * 示例 1:
 *
 * 给定二叉树 [3,9,20,null,null,15,7]
 *
 * ⁠   3
 * ⁠  / \
 * ⁠ 9  20
 * ⁠   /  \
 * ⁠  15   7
 *
 * 返回 true 。
 *
 * 示例 2:
 *
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *
 * ⁠      1
 * ⁠     / \
 * ⁠    2   2
 * ⁠   / \
 * ⁠  3   3
 * ⁠ / \
 * ⁠4   4
 *
 *
 * 返回 false 。
 *
 *
 *
 */

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * tree | depth-first-search
 *
 * bloomberg
 *
 * @author tukeping
 * @date 2020/3/10
 **/
public class LeetCode110 {

    private boolean ans = true;

    public boolean isBalanced(TreeNode root) {
        if (root == null) return ans;
        if (root.left == null && root.right == null) return ans;
        depth(root, 0);
        return ans;
    }

    private int depth(TreeNode root, int level) {
        if (root == null) return level;
        int L = depth(root.left, level + 1);
        int R = depth(root.right, level + 1);
        if (ans) {
            int N = Math.abs(L - R);
            ans = (N == 0 || N == 1);
        }
        return Math.max(L, R);
    }

    /**
     * 给定二叉树 [3,9,20,null,null,15,7]
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回 true 。
     */
    @Test
    public void test1() {
        TreeNode root = TreeNodeHelper.build(3, 9, 20, null, null, 15, 7);
        boolean b = isBalanced(root);
        assertTrue(b);
    }

    /**
     * 给定二叉树 [1,2,2,3,3,null,null,4,4]
     *
     *        1
     *       / \
     *      2   2
     *     / \
     *    3   3
     *   / \
     *  4   4
     * 返回 false 。
     */
    @Test
    public void test2() {
        TreeNode root = TreeNodeHelper.build(1, 2, 2, 3, 3, null, null, 4, 4);
        boolean b = isBalanced(root);
        assertFalse(b);
    }
}
