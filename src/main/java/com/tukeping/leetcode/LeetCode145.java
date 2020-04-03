package com.tukeping.leetcode;

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.Arrays;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/1
 **/
public class LeetCode145 {

    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode node = stack.peek();
            if (node.right != null) {
                cur = node.right;
                node.right = null;
            } else {
                res.add(stack.pop().val);
            }
        }
        return res;
    }

    /**
     * 输入: [1,null,2,3]
     *    1
     *     \
     *      2
     *     /
     *    3
     *
     * 输出: [3,2,1]
     */
    @Test
    public void test1() {
        List<Integer> ret = postorderTraversal(TreeNodeHelper.build(1, null, 2, 3));
        int[] actual = Arrays.asArray(ret);
        assertThat(actual, is(new int[]{3, 2, 1}));
    }

    @Test
    public void test2() {
        List<Integer> ret = postorderTraversal(TreeNodeHelper.build(1, 2, 3, null, null, 4, 5));
        int[] actual = Arrays.asArray(ret);
        assertThat(actual, is(new int[]{2, 4, 5, 3, 1}));
    }
}
