package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=538 lang=java
 *
 * [538] 把二叉搜索树转换为累加树
 *
 * https://leetcode-cn.com/problems/convert-bst-to-greater-tree/description/
 *
 * algorithms
 * Easy (57.54%)
 * Likes:    208
 * Dislikes: 0
 * Total Accepted:    17.5K
 * Total Submissions: 29.6K
 * Testcase Example:  '[5,2,13]'
 *
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater
 * Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 *
 * 例如：
 *
 *
 * 输入: 二叉搜索树:
 * ⁠             5
 * ⁠           /   \
 * ⁠          2     13
 *
 * 输出: 转换为累加树:
 * ⁠            18
 * ⁠           /   \
 * ⁠         20     13
 *
 *
 */

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

/**
 * tree
 *
 * amazon
 *
 * @author tukeping
 * @date 2020/3/3
 **/
public class LeetCode538 {

    private int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root == null)
            return null;

        convertBST(root.right);
        sum += root.val;
        root.val = sum;
        convertBST(root.left);

        return root;
    }

    /**
     * 输入: 二叉搜索树:
     *               5
     *             /   \
     *            2     13
     *
     * 输出: 转换为累加树:
     *              18
     *             /   \
     *           20     13
     */
    @Test
    public void test1() {
        TreeNode root = TreeNodeHelper.build(5, 2, 13);
        TreeNode ret = convertBST(root);
        TreeNodeHelper.check(ret, 18, 20, 13);
    }
}
