package com.tukeping.leetcode.contest.weekly.contest163;

import com.tukeping.leetcode.structures.TreeNode;
import com.tukeping.tools.TreeNodeHelper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author tukeping
 * @date 2020/4/28
 **/
public class LeetCode1261 {

    class FindElements {

        int[] nums;
        int n = 0;

        public FindElements(TreeNode root) {
            List<List<Integer>> ret = new ArrayList<>();
            preorder(root, 0, 0, ret);

            this.nums = new int[n];

            int idx = 0;
            for (List<Integer> values : ret)
                for (Integer value : values)
                    nums[idx++] = value;
        }

        private void preorder(TreeNode root, int val, int level, List<List<Integer>> ret) {
            if (root == null) return;

            if (ret.size() == level) {
                ret.add(new ArrayList<>());
            }

            ret.get(level).add(val);
            n++;

            preorder(root.left, 2 * val + 1, level + 1, ret);
            preorder(root.right, 2 * val + 2, level + 1, ret);
        }

        public boolean find(int target) {
            return bsearch(0, nums.length - 1, target);
        }

        private boolean bsearch(int start, int end, int target) {
            if (start > end) return false;
            int mid = start + end >> 1;
            if (nums[mid] == target) return true;
            else if (nums[mid] < target) return bsearch(mid + 1, end, target);
            else return bsearch(start, mid - 1, target);
        }
    }

    /**
     * 输入：
     * ["FindElements","find","find"]
     * [[[-1,null,-1]],[1],[2]]
     * 输出：
     * [null,false,true]
     * 解释：
     * FindElements findElements = new FindElements([-1,null,-1]);
     * findElements.find(1); // return False
     * findElements.find(2); // return True
     */
    @Test
    public void test1() {
        TreeNode root = TreeNodeHelper.build(-1, null, -1);
        FindElements findElements = new FindElements(root);
        assertFalse(findElements.find(1));
        assertTrue(findElements.find(2));
    }

    /**
     * ["FindElements","find","find","find"]
     * [[[-1,-1,-1,-1,-1]],[1],[3],[5]]
     */
    @Test
    public void test2() {
        TreeNode root = TreeNodeHelper.build(-1, -1, -1, -1, -1);
        FindElements findElements = new FindElements(root);
        assertTrue(findElements.find(1));
        assertTrue(findElements.find(3));
        assertFalse(findElements.find(5));
    }
}
