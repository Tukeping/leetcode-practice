package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=108 lang=java
 *
 * [108] 将有序数组转换为二叉搜索树
 *
 * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/description/
 *
 * algorithms
 * Easy (68.13%)
 * Likes:    325
 * Dislikes: 0
 * Total Accepted:    49.2K
 * Total Submissions: 71.1K
 * Testcase Example:  '[-10,-3,0,5,9]'
 *
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定有序数组: [-10,-3,0,5,9],
 *
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *
 * ⁠     0
 * ⁠    / \
 * ⁠  -3   9
 * ⁠  /   /
 * ⁠-10  5
 */

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

/**
 * @author tukeping
 * @date 2020/2/21
 **/
public class LeetCode108 {

    public TreeNode sortedArrayToBST(int[] nums) {
        return inorder(nums, 0, nums.length - 1);
    }

    /**
     * 中序遍历(inorder), left -> root -> right !!!
     * 前序遍历(preorder), root -> left -> right
     * 后序遍历(postorder), left -> right -> root
     *
     * 32/32 cases passed (0 ms)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 5.02 % of java submissions (42.2 MB)
     */
    private TreeNode inorder(int[] nums, int left, int right) {
        if (left > right) return null;

        int mid = (left + right) / 2;

        TreeNode root = new TreeNode(nums[mid]);

        root.left = inorder(nums, left, mid - 1);
        root.right = inorder(nums, mid + 1, right);

        return root;
    }

    /**
     * 给定有序数组: [-10,-3,0,5,9]
     * 一个可能的答案是：[0,-3,9,-10,null,5] 它可以表示下面这个高度平衡二叉搜索树:
     *       0
     *      / \
     *    -3   9
     *    /   /
     *  -10  5
     */
    @Test
    public void test1() {
        TreeNode sortedRoot = sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
        TreeNodeHelper.check(sortedRoot, 0, -10, 5, null, -3, null, 9);
    }
}
