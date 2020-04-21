package com.tukeping.leetcode.weekly.contest135;

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

/**
 * @author tukeping
 * @date 2020/4/20
 **/
public class LeetCode1308 {

    int preSum = 0;

    public TreeNode bstToGst(TreeNode root) {
        // Right -> Node -> Left
        dfs(root);
        return root;
    }

    private void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.right);
        int rootVal = root.val;
        root.val += preSum;
        preSum += rootVal;
        dfs(root.left);
    }

    /**
     * 输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
     * 输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
     */
    @Test
    public void test1() {
        TreeNode root = TreeNodeHelper.build(4, 1, 6, 0, 2, 5, 7, null, null, null, 3, null, null, null, 8);
        TreeNode actual = bstToGst(root);
        TreeNode expect = TreeNodeHelper.build(30, 36, 21, 36, 35, 26, 15, null, null, null, 33, null, null, null, 8);
        TreeNodeHelper.check(actual, expect);
    }
}
