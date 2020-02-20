package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=111 lang=java
 *
 * [111] 二叉树的最小深度
 *
 * https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/description/
 *
 * algorithms
 * Easy (40.76%)
 * Likes:    213
 * Dislikes: 0
 * Total Accepted:    51.6K
 * Total Submissions: 124.1K
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 * ⁠   3
 * ⁠  / \
 * ⁠ 9  20
 * ⁠   /  \
 * ⁠  15   7
 *
 * 返回它的最小深度  2.
 */

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * tree | depth-first-search | breadth-first-search
 *
 * Unknown
 *
 * @author tukeping
 * @date 2020/2/20
 **/
public class LeetCode111 {

    /**
     * 41/41 cases passed (0 ms)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 5.06 % of java submissions (40 MB)
     */

    int minDepth = Integer.MAX_VALUE;

    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        dfs(root, 0);
        return minDepth;
    }

    private void dfs(TreeNode root, int level) {
        if (root == null)
            return;

        level++;

        if (isLeaf(root)) {
            minDepth = Math.min(minDepth, level);
        }

        dfs(root.left, level);
        dfs(root.right, level);
    }

    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }

    /**
     * 示例:
     * 给定二叉树 [3,9,20,null,null,15,7]
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回它的最小深度 2.
     */
    @Test
    public void test1() {
        TreeNode root = TreeNodeHelper.build(3, 9, 20, null, null, 15, 7);
        int n = minDepth(root);
        assertThat(n, is(2));
    }

    @Test
    public void test2() {
        TreeNode root = TreeNodeHelper.build(0);
        int n = minDepth(root);
        assertThat(n, is(1));
    }
}
