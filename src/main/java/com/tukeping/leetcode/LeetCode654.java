package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=654 lang=java
 *
 * [654] 最大二叉树
 *
 * https://leetcode-cn.com/problems/maximum-binary-tree/description/
 *
 * algorithms
 * Medium (78.07%)
 * Likes:    114
 * Dislikes: 0
 * Total Accepted:    11.3K
 * Total Submissions: 14.2K
 * Testcase Example:  '[3,2,1,6,0,5]'
 *
 * 给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：
 *
 *
 * 二叉树的根是数组中的最大元素。
 * 左子树是通过数组中最大值左边部分构造出的最大二叉树。
 * 右子树是通过数组中最大值右边部分构造出的最大二叉树。
 *
 *
 * 通过给定的数组构建最大二叉树，并且输出这个树的根节点。
 *
 *
 *
 * 示例 ：
 *
 * 输入：[3,2,1,6,0,5]
 * 输出：返回下面这棵树的根节点：
 *
 * ⁠     6
 * ⁠   /   \
 * ⁠  3     5
 * ⁠   \    /
 * ⁠    2  0
 * ⁠      \
 * ⁠       1
 *
 *
 *
 *
 * 提示：
 *
 *
 * 给定的数组的大小在 [1, 1000] 之间。
 *
 *
 */

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

/**
 * tree
 *
 * microsoft
 *
 * @author tukeping
 * @date 2020/3/11
 **/
public class LeetCode654 {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return construct(nums, 0, nums.length);
    }

    private TreeNode construct(int[] nums, int start, int end) {
        if (start == end) return null;
        int max = max(nums, start, end);
        TreeNode root = new TreeNode(nums[max]);
        root.left = construct(nums, start, max);
        root.right = construct(nums, max + 1, end);
        return root;
    }

    private int max(int[] nums, int start, int end) {
        int max = start;
        for (int i = start; i < end; i++) {
            if (nums[max] < nums[i]) max = i;
        }
        return max;
    }

    /**
     * 输入：[3,2,1,6,0,5]
     * 输出：返回下面这棵树的根节点：
     *       6
     *     /   \
     *    3     5
     *     \    /
     *      2  0
     *        \
     *         1
     */
    @Test
    public void test1() {
        TreeNode root = constructMaximumBinaryTree(new int[]{3, 2, 1, 6, 0, 5});
        TreeNodeHelper.check(root, 6, 3, 5, null, 2, 0, null, null, 1);
    }
}
