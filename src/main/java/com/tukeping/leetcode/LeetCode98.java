package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=98 lang=java
 *
 * [98] 验证二叉搜索树
 *
 * https://leetcode-cn.com/problems/validate-binary-search-tree/description/
 *
 * algorithms
 * Medium (28.11%)
 * Likes:    407
 * Dislikes: 0
 * Total Accepted:    67.6K
 * Total Submissions: 233.9K
 * Testcase Example:  '[2,1,3]'
 *
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 *
 *
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 *
 * 示例 1:
 *
 * 输入:
 * ⁠   2
 * ⁠  / \
 * ⁠ 1   3
 * 输出: true
 *
 *
 * 示例 2:
 *
 * 输入:
 * ⁠   5
 * ⁠  / \
 * ⁠ 1   4
 * / \
 * 3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 * 根节点的值为 5 ，但是其右子节点值为 4 。
 *
 *
 */

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * tree | depth-first-search
 *
 * amazon | bloomberg | facebook | microsoft
 *
 * frequency 5
 *
 * @author tukeping
 * @date 2020/2/16
 **/
public class LeetCode98 {

    /*
     * 假设一个二叉搜索树具有如下特征：
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     */

    private long inorder = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        return root == null
                || isValidBST(root.left)
                && inorder < (inorder = root.val)
                && isValidBST(root.right);
    }

    private boolean dfsLoop(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        long inorder = -Long.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // If next element in inorder traversal
            // is smaller than the previous one
            // that's not BST.
            if (root.val <= inorder) return false;
            inorder = root.val;
            root = root.right;
        }

        return true;
    }

    private boolean dfsRecursion(TreeNode root) {
        if (root == null) return true;

        if (!dfsRecursion(root.left)) return false;

        // If next element in inorder traversal
        // is smaller than the previous one
        // that's not BST.
        if (root.val <= inorder) return false;

        inorder = root.val;

        return dfsRecursion(root.right);
    }

    /**
     * 输入:
     *     2
     *    / \
     *   1   3
     * 输出: true
     */
    @Test
    public void test1() {
        TreeNode root = TreeNodeHelper.build(2, 1, 3);
        boolean b = isValidBST(root);
        assertTrue(b);
    }

    /**
     * 输入:
     *     5
     *    / \
     *   1   4
     *      / \
     *     3   6
     * 输出: false
     * 解释: 输入为: [5,1,4,null,null,3,6]。
     *      根节点的值为 5 ，但是其右子节点值为 4 。
     */
    @Test
    public void test2() {
        TreeNode root = TreeNodeHelper.build(5, 1, 4, null, null, 3, 6);
        boolean b = isValidBST(root);
        assertFalse(b);
    }

    @Test
    public void test3() {
        TreeNode root = new TreeNode(0);
        boolean b = isValidBST(root);
        assertTrue(b);
    }

    @Test
    public void test4() {
        boolean b = isValidBST(null);
        assertTrue(b);
    }

    @Test
    public void test5() {
        TreeNode root = TreeNodeHelper.build(1, 1);
        boolean b = isValidBST(root);
        assertFalse(b);
    }

    @Test
    public void test6() {
        TreeNode root = TreeNodeHelper.build(1, null, 1);
        boolean b = isValidBST(root);
        assertFalse(b);
    }

    @Test
    public void test7() {
        TreeNode root = TreeNodeHelper.build(10, 5, 15, null, null, 6, 20);
        boolean b = isValidBST(root);
        assertFalse(b);
    }

    @Test
    public void test8() {
        TreeNode root = TreeNodeHelper.build(3, null, 30, 10, null, null, 15, null, 45);
        boolean b = isValidBST(root);
        assertFalse(b);
    }

    @Test
    public void test9() {
        TreeNode root = TreeNodeHelper.build(0, null, 1);
        boolean b = isValidBST(root);
        assertTrue(b);
    }

    @Test
    public void test10() {
        TreeNode root = TreeNodeHelper.build(3, null, 50, 10, null, null, 15, null, 45);
        boolean b = isValidBST(root);
        assertTrue(b);
    }
}
