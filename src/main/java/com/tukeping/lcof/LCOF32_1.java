package com.tukeping.lcof;

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/1
 **/
public class LCOF32_1 {

    private int size = 0;

    public int[] levelOrder(TreeNode root) {
        if (root == null) return new int[0];
        List<List<Integer>> res = new ArrayList<>();
        levelOrder(root, 0, res);
        int[] ans = new int[size];
        int idx = 0;
        for (List<Integer> nums : res) {
            for (int n : nums) {
                ans[idx++] = n;
            }
        }
        return ans;
    }

    private void levelOrder(TreeNode root, int level, List<List<Integer>> res) {
        if (res.size() == level) {
            res.add(new ArrayList<>());
        }
        size++;
        res.get(level).add(root.val);
        if (root.left != null)
            levelOrder(root.left, level + 1, res);
        if (root.right != null)
            levelOrder(root.right, level + 1, res);
    }

    /**
     * 例如:
     * 给定二叉树: [3,9,20,null,null,15,7],
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回：
     * [3,9,20,15,7]
     */
    @Test
    public void test1() {
        int[] ret = levelOrder(TreeNodeHelper.build(3, 9, 20, null, null, 15, 7));
        assertThat(ret, is(new int[]{3, 9, 20, 15, 7}));
    }
}
