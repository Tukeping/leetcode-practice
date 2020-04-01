package com.tukeping.leetcode.lcof;

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tukeping
 * @date 2020/3/30
 **/
public class LCOF07 {

    int[] preorder;
    int preIdx;
    Map<Integer, Integer> inorderMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;

        int len = inorder.length;
        for (int i = 0; i < len; i++)
            inorderMap.put(inorder[i], i);

        return buildTree(0, len);
    }

    private TreeNode buildTree(int left, int right) {
        if (left == right) return null;

        int rootVal = preorder[preIdx++];
        TreeNode root = new TreeNode(rootVal);

        int mid = inorderMap.get(rootVal);
        root.left = buildTree(left, mid);
        root.right = buildTree(mid + 1, right);
        return root;
    }

    /**
     * Left -> Root -> Right
     */
    public TreeNode buildTreeInorder(int[] nums) {

        return null;
    }

    public TreeNode buildTreePreorder(int[] nums) {

        return null;
    }

    public TreeNode buildTreePostorder(int[] nums) {

        return null;
    }

    public int[] parseTreeInorder(TreeNode root) {

        return null;
    }

    public int[] parseTreePreorder(TreeNode root) {

        return null;
    }

    public int[] parseTreePostorder(TreeNode root) {

        return null;
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
