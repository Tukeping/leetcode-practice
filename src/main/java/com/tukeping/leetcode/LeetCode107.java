package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=107 lang=java
 *
 * [107] 二叉树的层次遍历 II
 *
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/description/
 *
 * algorithms
 * Easy (63.47%)
 * Likes:    194
 * Dislikes: 0
 * Total Accepted:    45.1K
 * Total Submissions: 69.9K
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 * ⁠   3
 * ⁠  / \
 * ⁠ 9  20
 * ⁠   /  \
 * ⁠  15   7
 *
 * 返回其自底向上的层次遍历为：
 *
 * [
 * ⁠ [15,7],
 * ⁠ [9,20],
 * ⁠ [3]
 * ]
 */

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.ListHelper;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * tree | breadth-first-search
 *
 * Unknown
 *
 * @author tukeping
 * @date 2020/3/10
 **/
public class LeetCode107 {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, 0, res);
        return res;
    }

    private void dfs(TreeNode root, int level, List<List<Integer>> res) {
        if (level == res.size()) res.add(0, new ArrayList<>());

        res.get(res.size() - 1 - level).add(root.val);

        if (root.left != null) dfs(root.left, level + 1, res);
        if (root.right != null) dfs(root.right, level + 1, res);
    }

    private List<List<Integer>> bfs(TreeNode root) {
        Queue<TreeNode> Q = new LinkedList<>();
        Q.add(root);

        List<List<Integer>> res = new ArrayList<>();
        while (!Q.isEmpty()) {
            List<Integer> sameLevel = new ArrayList<>();
            int nodeNum = Q.size();
            for (int i = 0; i < nodeNum; i++) {
                TreeNode node = Q.poll();
                if (node != null) {
                    sameLevel.add(node.val);
                    Q.add(node.left);
                    Q.add(node.right);
                }
            }
            if (!sameLevel.isEmpty()) res.add(0, sameLevel);
        }

        return res;
    }

    /**
     * 给定二叉树 [3,9,20,null,null,15,7],
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回其自底向上的层次遍历为：
     * [
     *   [15,7],
     *   [9,20],
     *   [3]
     * ]
     */
    @Test
    public void test1() {
        TreeNode root = TreeNodeHelper.build(3, 9, 20, null, null, 15, 7);
        List<List<Integer>> res = levelOrderBottom(root);
        int[][] actual = ListHelper.asTwoDimArray(res);
        int[][] expect = {
                {15, 7},
                {9, 20},
                {3}
        };
        assertThat(actual, is(expect));
    }
}
