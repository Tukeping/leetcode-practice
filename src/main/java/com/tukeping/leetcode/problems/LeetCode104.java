package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=104 lang=java
 *
 * [104] 二叉树的最大深度
 *
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/description/
 *
 * algorithms
 * Easy (71.52%)
 * Likes:    460
 * Dislikes: 0
 * Total Accepted:    125.7K
 * Total Submissions: 174.1K
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 * ⁠   3
 * ⁠  / \
 * ⁠ 9  20
 * ⁠   /  \
 * ⁠  15   7
 *
 * 返回它的最大深度 3 。
 *
 */

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/2/20
 **/
public class LeetCode104 {

    int maxDeepV2 = 0;

    public int maxDepthV2(TreeNode root) {
        dfsV2(root, 1);
        return maxDeepV2;
    }

    private void dfsV2(TreeNode root, int deep) {
        if (root == null) return;
        if (deep > maxDeepV2) maxDeepV2 = deep;
        dfs(root.left, deep + 1);
        dfs(root.right, deep + 1);
    }

    int maxDepth = 0;

    public int maxDepth(TreeNode root) {
        dfs(root, 0);
        return maxDepth;
    }

    private void dfs(TreeNode root, int level) {
        if (root == null) return;

        level++;
        maxDepth = Math.max(maxDepth, level);

        dfs(root.left, level);
        dfs(root.right, level);
    }

    /**
     * 给定二叉树 [3,9,20,null,null,15,7]
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回它的最大深度 3 。
     */
    @Test
    public void test1() {
        TreeNode root = TreeNodeHelper.build(3, 9, 20, null, null, 15, 7);
        int n = maxDepth(root);
        assertThat(n, is(3));
    }
}
