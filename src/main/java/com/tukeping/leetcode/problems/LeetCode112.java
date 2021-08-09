package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=112 lang=java
 *
 * [112] 路径总和
 *
 * https://leetcode-cn.com/problems/path-sum/description/
 *
 * algorithms
 * Easy (48.38%)
 * Likes:    240
 * Dislikes: 0
 * Total Accepted:    51.9K
 * Total Submissions: 105.8K
 * Testcase Example:  '[5,4,8,11,null,13,4,7,2,null,null,null,1]\n22'
 *
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例: 
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 * ⁠             5
 * ⁠            / \
 * ⁠           4   8
 * ⁠          /   / \
 * ⁠         11  13  4
 * ⁠        /  \      \
 * ⁠       7    2      1
 *
 *
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 *
 */

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author tukeping
 * @date 2020/2/21
 **/
public class LeetCode112 {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        return dfs(root, targetSum);
    }

    private boolean dfs(TreeNode root, int target) {
        if (root == null) return false;

        target -= root.val;
        if (target == 0 && isLeaf(root)) {
            return true;
        }

        if (dfs(root.left, target)) return true;
        if (dfs(root.right, target)) return true;

        return false;
    }

    private boolean isLeaf(TreeNode root) {
        return root.left == null && root.right == null;
    }

    public boolean hasPathSumV2(TreeNode root, int sum) {
        return hasPathSum(root, 0, sum);
    }

    private boolean hasPathSum(TreeNode root, int cur, int sum) {
        if (root == null) return false;
        cur += root.val;
        if (cur == sum && isLeafV2(root)) return true;
        else if (hasPathSum(root.left, cur, sum)) return true;
        else return hasPathSum(root.right, cur, sum);
    }

    private boolean isLeafV2(TreeNode root) {
        return root.left == null && root.right == null;
    }

    @Test
    public void test1() {
        TreeNode root = TreeNodeHelper.build(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1);
        boolean b = hasPathSum(root, 22);
        assertTrue(b);
    }

    @Test
    public void test2() {
        TreeNode root = TreeNodeHelper.build(1, 2);
        boolean b = hasPathSum(root, 1);
        assertFalse(b);
    }

    @Test
    public void test3() {
        TreeNode root = TreeNodeHelper.build(1, 2);
        boolean b = hasPathSum(root, 2);
        assertFalse(b);
    }

    @Test
    public void test4() {
        TreeNode root = TreeNodeHelper.build(-2, null, -3);
        boolean b = hasPathSum(root, -5);
        assertTrue(b);
    }

    @Test
    public void test5() {
        TreeNode root = TreeNodeHelper.build(1, -2, -3, 1, 3, -2, null, -1);
        boolean b = hasPathSum(root, -1);
        assertTrue(b);
    }
}
