package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=114 lang=java
 *
 * [114] 二叉树展开为链表
 *
 * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/description/
 *
 * algorithms
 * Medium (66.54%)
 * Likes:    281
 * Dislikes: 0
 * Total Accepted:    30.1K
 * Total Submissions: 44.4K
 * Testcase Example:  '[1,2,5,3,4,null,6]'
 *
 * 给定一个二叉树，原地将它展开为链表。
 *
 * 例如，给定二叉树
 *
 * ⁠   1
 * ⁠  / \
 * ⁠ 2   5
 * ⁠/ \   \
 * 3   4   6
 *
 * 将其展开为：
 *
 * 1
 * ⁠\
 * ⁠ 2
 * ⁠  \
 * ⁠   3
 * ⁠    \
 * ⁠     4
 * ⁠      \
 * ⁠       5
 * ⁠        \
 * ⁠         6
 *
 */

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

/**
 * tree | depth-first-search
 *
 * microsoft
 *
 * @author tukeping
 * @date 2020/3/11
 **/
public class LeetCode114 {

    public void flatten(TreeNode root) {
        if (root == null) return;
        if (root.left != null) {
            TreeNode right = root.right;
            root.right = root.left;
            root.left = null;
            bottomRight(root.right).right = right;
        }
        flatten(root.right);
    }

    private TreeNode bottomRight(TreeNode root) {
        if (root.right == null) return root;
        else return bottomRight(root.right);
    }

    /**
     * 例如，给定二叉树
     *
     *     1
     *    / \
     *   2   5
     *  / \   \
     * 3   4   6
     * 将其展开为：
     * 1
     *  \
     *   2
     *    \
     *     3
     *      \
     *       4
     *        \
     *         5
     *          \
     *           6
     */
    @Test
    public void test1() {
        TreeNode root = TreeNodeHelper.build(1, 2, 5, 3, 4, null, 6);
        flatten(root);
        TreeNode expect = TreeNodeHelper.build(1, null, 2, null, 3, null, 4, null, 5, null, 6);
        TreeNodeHelper.check(root, expect);
    }
}
