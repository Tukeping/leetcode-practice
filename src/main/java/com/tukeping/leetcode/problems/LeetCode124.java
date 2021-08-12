package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=124 lang=java
 *
 * [124] 二叉树中的最大路径和
 *
 * https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/description/
 *
 * algorithms
 * Hard (38.39%)
 * Likes:    317
 * Dislikes: 0
 * Total Accepted:    24.8K
 * Total Submissions: 63.1K
 * Testcase Example:  '[1,2,3]'
 *
 * 给定一个非空二叉树，返回其最大路径和。
 *
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 * ⁠      1
 * ⁠     / \
 * ⁠    2   3
 * 输出: 6
 *
 * 示例 2:
 *
 * 输入: [-10,9,20,null,null,15,7]
 * -10
 * / \
 * 9  20
 * /  \
 * 15   7
 * 输出: 42
 */

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * tree | depth-first-search
 *
 * baidu | microsoft
 *
 * @author tukeping
 * @date 2020/3/20
 **/
public class LeetCode124 {

    int maxSum = Integer.MIN_VALUE;

    public int maxPathSumV2(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    //    a
    //  b   c
    // case1: b -> a -> c
    // case2: b -> a -> a' up
    // case3: c -> a -> a' up

    // case2 & case3 : Math.max(sumLeft, sumRight) 左右节点 选一个 配合 root.val
    // case1 : root + 贪心思想 是否值得 拿 sumLeft 或 sumRight
    // 递归返回值 一定要是 case2 & case3 的return
    // maxSum 是在 整个递归过程中 存在于 case1, case2, case3 其中一种情况下 所以每次都计算最大Sum值

    private int dfs(TreeNode root) {
        if (root == null) return 0;

        int sumLeft = dfs(root.left);
        int sumRight = dfs(root.right);

        int lmr = root.val + Math.max(0, sumLeft) + Math.max(0, sumRight);
        int ret = root.val + Math.max(0, Math.max(sumLeft, sumRight));
        maxSum = Math.max(maxSum, Math.max(lmr, ret));

        return ret;
    }

    private int maxValue = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        calcValue(root);
        return maxValue;
    }

    private int calcValue(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;

        int value = root.val;

        int vL = calcValue(root.left);
        if (vL > 0) value += vL;

        int vR = calcValue(root.right);
        if (vR > 0) value += vR;

        maxValue = Math.max(maxValue, max(value, vL, vR));

        return max(root.val,
                root.left == null ? vL : root.val + vL,
                root.right == null ? vR : root.val + vR);
    }

    private int max(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    /**
     * 输入: [1,2,3]
     *
     *        1
     *       / \
     *      2   3
     *
     * 输出: 6
     */
    @Test
    public void test1() {
        int n = maxPathSum(TreeNodeHelper.build(1, 2, 3));
        assertThat(n, is(6));
    }

    /**
     * 输入: [-10,9,20,null,null,15,7]
     *
     *    -10
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     * 输出: 42
     */
    @Test
    public void test2() {
        int n = maxPathSum(TreeNodeHelper.build(-10, 9, 20, null, null, 15, 7));
        assertThat(n, is(42));
    }

    @Test
    public void test3() {
        int n = maxPathSum(TreeNodeHelper.build(-10, -11, -12));
        assertThat(n, is(-10));
    }

    @Test
    public void test4() {
        int n = maxPathSum(TreeNodeHelper.build(-10));
        assertThat(n, is(-10));
    }

    @Test
    public void test5() {
        int n = maxPathSum(TreeNodeHelper.build(-10, -11, -12, -13, null, -14, 1));
        assertThat(n, is(1));
    }

    /**
     * 输入: [1,-2,-3,1,3,-2,null,-1]
     *       1
     *     /  \
     *   -2   -3
     *   / \  /
     *  1  3 -2
     *       /
     *      -1
     *
     * 输出: 3
     */
    @Test
    public void test6() {
        int n = maxPathSum(TreeNodeHelper.build(1, -2, -3, 1, 3, -2, null, null, null, null, null, -1));
        assertThat(n, is(3));
    }
}
