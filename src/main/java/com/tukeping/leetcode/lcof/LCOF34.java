package com.tukeping.leetcode.lcof;

/*
 * 面试题34. 二叉树中和为某一值的路径
 *
 * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
 *
 *  
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 *  
 *
 * 提示：
 *
 * 节点总数 <= 10000
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
 * @author tukeping
 * @date 2020/3/26
 **/
public class LCOF34 {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        findPathSum(root, 0, sum, new LinkedList<>(), res);
        return res;
    }

    private void findPathSum(TreeNode root, int sum, LinkedList<Integer> track, List<List<Integer>> res) {
        if (root == null) return;

        sum -= root.val;
        track.add(root.val);

        if (sum == 0 && isLeaf(root)) {
            res.add(new ArrayList<>(track));
            return;
        }

        if (root.left != null) {
            findPathSum(root.left, sum, track, res);
            track.removeLast();
        }

        if (root.right != null) {
            findPathSum(root.right, sum, track, res);
            track.removeLast();
        }
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
     * 给定如下二叉树，以及目标和 sum = 22，
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
