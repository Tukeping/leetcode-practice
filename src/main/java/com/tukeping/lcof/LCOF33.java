package com.tukeping.lcof;

import com.tukeping.leetcode.structures.TreeNode;
import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author tukeping
 * @date 2020/4/2
 **/
public class LCOF33 {

    public boolean verifyPostorder(int[] postorder) {
        Stack<Integer> stack = new Stack<>();
        int root = Integer.MAX_VALUE;
        for (int i = postorder.length - 1; i >= 0; i--) {
            if (postorder[i] > root) return false;
            while (!stack.isEmpty() && stack.peek() > postorder[i])
                root = stack.pop();
            stack.add(postorder[i]);
        }
        return true;
    }

    public boolean verifyPostorder2(int[] postorder) {
        return recur(postorder, 0, postorder.length - 1);
    }

    private boolean recur(int[] po, int i, int j) {
        if (i >= j) return true;
        int l = i;
        while (po[l] < po[j]) l++;
        int m = l;
        while (po[l] > po[j]) l++;
        return l == j && recur(po, i, m - 1) && recur(po, m, j - 1);
    }

    public boolean verifyPostorder3(int[] postorder) {
        int len = postorder.length;
        if (len <= 2) return true;
        return verify(postorder, 0, len - 1);
    }

    private boolean verify(int[] nums, int start, int end) {
        if (start >= end) return true;
        int L = start;
        while (nums[L] < nums[end]) L++;
        int m = L;
        while (nums[L] > nums[end]) L++;
        return L == end
                && verify(nums, start, m - 1)
                && verify(nums, m, end - 1);
    }

    private static TreeNode build(int[] postorder, int start, int end) {
        TreeNode root = new TreeNode(postorder[end]);
        if (end - start < 2)
            return root;
        if (postorder[start] < root.val)
            root.left = build(postorder, start, end - 1);
        return root;
    }

    private static void postorder(TreeNode root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.val + " ");
    }

    /**
     * 输入: [1,6,3,2,5]
     * 输出: false
     */
    @Test
    public void test1() {
        assertFalse(verifyPostorder(new int[]{1, 6, 3, 2, 5}));
    }

    /**
     * 输入: [1,3,2,6,5]
     * 输出: true
     */
    @Test
    public void test2() {
        assertTrue(verifyPostorder(new int[]{1, 3, 2, 6, 5}));
    }
}
