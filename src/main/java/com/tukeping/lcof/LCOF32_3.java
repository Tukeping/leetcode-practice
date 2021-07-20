package com.tukeping.lcof;

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.ListHelper;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/1
 **/
public class LCOF32_3 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<List<Integer>> res = new ArrayList<>();
        levelOrder(root, 0, true, res);
        return res;
    }

    private void levelOrder(TreeNode root, int level, boolean order, List<List<Integer>> res) {
        if (res.size() == level) {
            res.add(new ArrayList<>());
        }
        if (order) res.get(level).add(root.val);
        else res.get(level).add(0, root.val);
        if (root.left != null)
            levelOrder(root.left, level + 1, !order, res);
        if (root.right != null)
            levelOrder(root.right, level + 1, !order, res);
    }

    @Test
    public void test1() {
        List<List<Integer>> res = levelOrder(TreeNodeHelper.build(1, 2, 3, 4, 5));
        int[][] ans = ListHelper.asTwoDimArray(res);
        int[][] expect = {
                {1},
                {3, 2},
                {4, 5}
        };
        assertThat(ans, is(expect));
    }

    /**
     * 例如:
     * 给定二叉树: [3,9,20,null,null,15,7],
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回其层次遍历结果：
     * [
     *   [3],
     *   [20,9],
     *   [15,7]
     * ]
     */
    @Test
    public void test2() {
        List<List<Integer>> res = levelOrder(TreeNodeHelper.build(3, 9, 20, null, null, 15, 7));
        int[][] ans = ListHelper.asTwoDimArray(res);
        int[][] expect = {
                {3},
                {20, 9},
                {15, 7}
        };
        assertThat(ans, is(expect));
    }
}
