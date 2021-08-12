package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=129 lang=java
 *
 * [129] 求根到叶子节点数字之和
 *
 * https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/description/
 *
 * algorithms
 * Medium (60.26%)
 * Likes:    110
 * Dislikes: 0
 * Total Accepted:    17.5K
 * Total Submissions: 28.5K
 * Testcase Example:  '[1,2,3]'
 *
 * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
 *
 * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
 *
 * 计算从根到叶子节点生成的所有数字之和。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 * ⁠   1
 * ⁠  / \
 * ⁠ 2   3
 * 输出: 25
 * 解释:
 * 从根到叶子节点路径 1->2 代表数字 12.
 * 从根到叶子节点路径 1->3 代表数字 13.
 * 因此，数字总和 = 12 + 13 = 25.
 *
 * 示例 2:
 *
 * 输入: [4,9,0,5,1]
 * ⁠   4
 * ⁠  / \
 * ⁠ 9   0
 * / \
 * 5   1
 * 输出: 1026
 * 解释:
 * 从根到叶子节点路径 4->9->5 代表数字 495.
 * 从根到叶子节点路径 4->9->1 代表数字 491.
 * 从根到叶子节点路径 4->0 代表数字 40.
 * 因此，数字总和 = 495 + 491 + 40 = 1026.
 *
 */

import com.tukeping.leetcode.structures.TreeNode;

/**
 * tree | depth-first-search
 *
 * Unknown
 *
 * frequency 4
 *
 * @author tukeping
 * @date 2020/2/16
 **/
public class LeetCode129 {

    int sum = 0;

    public int sumNumbers(TreeNode root) {
        // dfs + 回溯
        // 记录下所有路径？ 不需要，直接用sum变量做累加
        dfs(root, new StringBuilder());
        return sum;
    }

    // sum: 25
    // root: 3
    // path: [1, 3]
    private void dfs(TreeNode root, StringBuilder path) {
        if (root == null) return;
        path.append(root.val);
        if (isLeaf(root)) {
            int val = Integer.parseInt(path.toString());
            sum += val;
        }
        dfs(root.left, path);
        dfs(root.right, path);
        path.deleteCharAt(path.length() - 1);
    }

    private boolean isLeaf(TreeNode root) {
        return root.left == null && root.right == null;
    }
}
