package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=437 lang=java
 *
 * [437] 路径总和 III
 *
 * https://leetcode-cn.com/problems/path-sum-iii/description/
 *
 * algorithms
 * Easy (52.99%)
 * Likes:    303
 * Dislikes: 0
 * Total Accepted:    22.8K
 * Total Submissions: 42K
 * Testcase Example:  '[10,5,-3,3,2,null,11,3,-2,null,1]\n8'
 *
 * 给定一个二叉树，它的每个结点都存放着一个整数值。
 *
 * 找出路径和等于给定数值的路径总数。
 *
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 *
 * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
 *
 * 示例：
 *
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *
 * ⁠     10
 * ⁠    /  \
 * ⁠   5   -3
 * ⁠  / \    \
 * ⁠ 3   2   11
 * ⁠/ \   \
 * 3  -2   1
 *
 * 返回 3。和等于 8 的路径有:
 *
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3.  -3 -> 11
 */

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * tree
 *
 * Unknown
 *
 * @author tukeping
 * @date 2020/3/3
 **/
public class LeetCode437 {

    public int pathSum(TreeNode root, int sum) {
        return pathSum(root, sum, new int[1000], 0);
    }

    private int pathSum(TreeNode root, int sum, int[] arr, int p) {
        if (root == null) return 0;

        int temp = root.val;
        int n = root.val == sum ? 1 : 0;
        for (int i = p - 1; i >= 0; i--) {
            temp += arr[i];
            if (temp == sum) {
                n++;
            }
        }

        arr[p] = root.val;

        int n1 = pathSum(root.left, sum, arr, p + 1);
        int n2 = pathSum(root.right, sum, arr, p + 1);
        return n + n1 + n2;
    }

    /**
     * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
     *       10
     *      /  \
     *     5   -3
     *    / \    \
     *   3   2   11
     *  / \   \
     * 3  -2   1
     *
     * 返回 3。和等于 8 的路径有:
     *
     * 1.  5 -> 3
     * 2.  5 -> 2 -> 1
     * 3.  -3 -> 11
     */
    @Test
    public void test1() {
        TreeNode root = TreeNodeHelper.build(10, 5, -3, 3, 2, null, 11, 3, -2, null, 1);
        int n = pathSum(root, 8);
        assertThat(n, is(3));
    }
}
