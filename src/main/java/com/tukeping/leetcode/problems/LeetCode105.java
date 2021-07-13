package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=105 lang=java
 *
 * [105] 从前序与中序遍历序列构造二叉树
 *
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
 *
 * algorithms
 * Medium (63.15%)
 * Likes:    346
 * Dislikes: 0
 * Total Accepted:    43.6K
 * Total Submissions: 67.9K
 * Testcase Example:  '[3,9,20,15,7]\n[9,3,15,20,7]'
 *
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 *
 * 返回如下的二叉树：
 *
 * ⁠   3
 * ⁠  / \
 * ⁠ 9  20
 * ⁠   /  \
 * ⁠  15   7
 *
 */

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tukeping
 * @date 2020/2/21
 **/
public class LeetCode105 {

    private Map<Integer, Integer> inorderMap = new HashMap<>();
    private int[] preorder;
    private int preIdx;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;

        for (int i = 0; i < inorder.length; i++)
            inorderMap.put(inorder[i], i);

        return buildTree(0, inorder.length);
    }

    private TreeNode buildTree(int left, int right) {
        if (left == right) return null;

        int rootVal = preorder[preIdx++];
        TreeNode root = new TreeNode(rootVal);

        int midIdx = inorderMap.get(rootVal);
        root.left = buildTree(left, midIdx);
        root.right = buildTree(midIdx + 1, right);
        return root;
    }

    /**
     * 前序遍历 preorder = [3,9,20,15,7]
     * 中序遍历 inorder = [9,3,15,20,7]
     * 返回如下的二叉树：
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     */
    @Test
    public void test1() {
        TreeNode actual = buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        TreeNode expect = TreeNodeHelper.build(3, 9, 20, null, null, 15, 7);
        TreeNodeHelper.check(actual, expect);
    }
}
