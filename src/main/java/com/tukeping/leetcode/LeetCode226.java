package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=226 lang=java
 *
 * [226] 翻转二叉树
 *
 * https://leetcode-cn.com/problems/invert-binary-tree/description/
 *
 * algorithms
 * Easy (72.95%)
 * Likes:    374
 * Dislikes: 0
 * Total Accepted:    56.8K
 * Total Submissions: 76.6K
 * Testcase Example:  '[4,2,7,1,3,6,9]'
 *
 * 翻转一棵二叉树。
 *
 * 示例：
 *
 * 输入：
 *
 * ⁠    4
 * ⁠  /   \
 * ⁠ 2     7
 * ⁠/ \   / \
 * 1   3 6   9
 *
 * 输出：
 *
 * ⁠    4
 * ⁠  /   \
 * ⁠ 7     2
 * ⁠/ \   / \
 * 9   6 3   1
 *
 * 备注:
 * 这个问题是受到 Max Howell 的 原问题 启发的 ：
 *
 * 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
 */

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * tree
 *
 * Unknown
 *
 * @author tukeping
 * @date 2020/3/2
 **/
public class LeetCode226 {

    public TreeNode invertTree(TreeNode root) {
        return bfs(root);
    }

    private TreeNode dfs(TreeNode root) {
        if (root == null) return null;

        TreeNode right = dfs(root.right);
        TreeNode left = dfs(root.left);

        root.left = right;
        root.right = left;

        return root;
    }

    private TreeNode bfs(TreeNode root) {
        if (root == null) return null;

        Queue<TreeNode> Q = new LinkedList<>();
        Q.add(root);

        while (!Q.isEmpty()) {
            TreeNode R = Q.poll();
            TreeNode temp = R.left;
            R.left = R.right;
            R.right = temp;

            if (R.left != null)
                Q.add(R.left);

            if (R.right != null)
                Q.add(R.right);
        }

        return root;
    }

    /**
     * 输入：
     *      4
     *    /   \
     *   2     7
     *  / \   / \
     * 1   3 6   9
     * 输出：
     *      4
     *    /   \
     *   7     2
     *  / \   / \
     * 9   6 3   1
     */
    @Test
    public void test1() {
        TreeNode root = TreeNodeHelper.build(4, 2, 7, 1, 3, 6, 9);
        TreeNode ret = invertTree(root);
        TreeNodeHelper.check(ret, 4, 7, 2, 9, 6, 3, 1);
    }
}
