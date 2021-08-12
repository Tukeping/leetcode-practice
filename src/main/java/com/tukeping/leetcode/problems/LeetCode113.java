package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=113 lang=java
 *
 * [113] 路径总和 II
 *
 * https://leetcode-cn.com/problems/path-sum-ii/description/
 *
 * algorithms
 * Medium (59.02%)
 * Likes:    188
 * Dislikes: 0
 * Total Accepted:    37.5K
 * Total Submissions: 63.6K
 * Testcase Example:  '[5,4,8,11,null,13,4,7,2,null,null,5,1]\n22'
 *
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 * ⁠             5
 * ⁠            / \
 * ⁠           4   8
 * ⁠          /   / \
 * ⁠         11  13  4
 * ⁠        /  \    / \
 * ⁠       7    2  5   1
 *
 *
 * 返回:
 *
 * [
 * ⁠  [5,4,11,2],
 * ⁠  [5,8,4,5]
 * ]
 *
 *
 */

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.ListHelper;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * tree | depth-first-search
 *
 * bloomberg
 *
 * @author tukeping
 * @date 2020/3/26
 **/
public class LeetCode113 {

    List<List<Integer>> ret = new ArrayList<>();

    public List<List<Integer>> pathSumV2(TreeNode root, int targetSum) {
        dfs(root, targetSum, new ArrayList<>());
        return ret;
    }

    private void dfs(TreeNode root, int target, List<Integer> result) {
        if (root == null) return;
        target -= root.val;
        result.add(root.val);
        if (isLeafV2(root) && target == 0) {
            ret.add(new ArrayList<>(result));
        }
        dfs(root.left, target, result);
        dfs(root.right, target, result);
        if (result.size() > 0) {
            result.remove(result.size() - 1);
        }
    }

    private boolean isLeafV2(TreeNode root) {
        return root.left == null && root.right == null;
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        findPathSum(root, 0, sum, new LinkedList<>(), res);
        return res;
    }

    private void findPathSum(TreeNode root, int cur, int sum, LinkedList<Integer> track, List<List<Integer>> res) {
        if (root == null) return;

        cur += root.val;
        track.add(root.val);

        if (cur == sum && isLeaf(root)) {
            res.add(new ArrayList<>(track));
            return;
        }

        if (root.left != null) {
            findPathSum(root.left, cur, sum, track, res);
            track.removeLast();
        }

        if (root.right != null) {
            findPathSum(root.right, cur, sum, track, res);
            track.removeLast();
        }
    }

    private boolean isLeaf(TreeNode root) {
        return root.left == null && root.right == null;
    }

    /**
     * 示例:
     * 给定如下二叉树，以及目标和 sum = 22，
     *
     *               5
     *              / \
     *             4   8
     *            /   / \
     *           11  13  4
     *          /  \    / \
     *         7    2  5   1
     * 返回:
     * [
     *    [5,4,11,2],
     *    [5,8,4,5]
     * ]
     */
    @Test
    public void test1() {
        TreeNode root = TreeNodeHelper.build(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1);
        int[][] ret = ListHelper.asTwoDimArray(pathSum(root, 22));
        int[][] expect = {
                {5, 4, 11, 2},
                {5, 8, 4, 5}
        };
        assertThat(ret, is(expect));
    }

    @Test
    public void test2() {
        TreeNode root = TreeNodeHelper.build(-2, null, -3);
        int[][] ret = ListHelper.asTwoDimArray(pathSum(root, -5));
        int[][] expect = {
                {-2, -3}
        };
        assertThat(ret, is(expect));
    }

    @Test
    public void test3() {
        TreeNode root = TreeNodeHelper.build(1, -2, -3, 1, 3, -2, null, -1);
        int[][] ret = ListHelper.asTwoDimArray(pathSum(root, 2));
        int[][] expect = {
                {1, -2, 3}
        };
        assertThat(ret, is(expect));
    }
}
