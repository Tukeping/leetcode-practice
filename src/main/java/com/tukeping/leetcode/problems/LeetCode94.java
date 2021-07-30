package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=94 lang=java
 *
 * [94] 二叉树的中序遍历
 *
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/description/
 *
 * algorithms
 * Medium (69.29%)
 * Likes:    416
 * Dislikes: 0
 * Total Accepted:    116.8K
 * Total Submissions: 165.8K
 * Testcase Example:  '[1,null,2,3]'
 *
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 * ⁠  1
 * ⁠   \
 * ⁠    2
 * ⁠   /
 * ⁠  3
 *
 * 输出: [1,3,2]
 *
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 */

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.Arrays;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/10
 **/
public class LeetCode94 {

    List<Integer> ret = new ArrayList<>();

    public List<Integer> inorderTraversalV2(TreeNode root) {
        dfs(root);
        return ret;
    }

    private void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.left);
        ret.add(root.val);
        dfs(root.right);
    }

    public List<Integer> inorderTraversal1(TreeNode root) {
        return inorderTraversalMorris(root);
    }

    public List<Integer> inorderTraversalMorris(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode cur = root;
        TreeNode pre;
        while (cur != null) {
            if (cur.left == null) {
                res.add(cur.val);
                cur = cur.right; // move to next right node
            } else { // has a left subtree
                pre = cur.left;
                while (pre.right != null) { // find rightmost
                    pre = pre.right;
                }
                pre.right = cur; // put cur after the pre node
                TreeNode temp = cur; // store cur node
                cur = cur.left; // move cur to the top of the new tree
                temp.left = null; // original cur left be null, avoid infinite loops
            }
        }
        return res;
    }

    public List<Integer> inorderTraversalStack(TreeNode root) {
        if (root == null) return Collections.emptyList();
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            res.add(cur.val);
            cur = cur.right;
        }
        return res;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    private void inorder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }

    private void preorder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        preorder(root.left, res);
        preorder(root.right, res);
    }

    private void postorder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        postorder(root.left, res);
        postorder(root.right, res);
        res.add(root.val);
    }

    /**
     * 输入: [1,null,2,3]
     *    1
     *     \
     *      2
     *     /
     *    3
     * 输出: [1,3,2]
     */
    @Test
    public void test1() {
        List<Integer> res = inorderTraversal(TreeNodeHelper.build(1, null, 2, 3));
        int[] actual = Arrays.asArray(res);
        int[] expect = {1, 3, 2};
        assertThat(actual, is(expect));
    }
}
