package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=47 lang=java
 *
 * [47] 全排列 II
 *
 * https://leetcode-cn.com/problems/permutations-ii/description/
 *
 * algorithms
 * Medium (56.09%)
 * Likes:    217
 * Dislikes: 0
 * Total Accepted:    37.6K
 * Total Submissions: 66.1K
 * Testcase Example:  '[1,1,2]'
 *
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 示例:
 * 输入: [1,1,2]
 * 输出:
 * [
 * ⁠ [1,1,2],
 * ⁠ [1,2,1],
 * ⁠ [2,1,1]
 * ]
 */

import com.tukeping.tools.ListHelper;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * backtracking
 *
 * linkedin | microsoft
 *
 * permutation
 *
 * @author tukeping
 * @date 2020/2/13
 **/
public class LeetCode47 {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);
        backtrack(nums, new LinkedList<>(), res, new boolean[nums.length]);
        return res;
    }

    private void backtrack(int[] nums, LinkedList<Integer> track, List<List<Integer>> res, boolean[] used) {
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            used[i] = true;
            track.add(nums[i]);
            backtrack(nums, track, res, used);
            used[i] = false;
            track.removeLast();
        }
    }

    /**
     * 输入: [1,1,2]
     * 输出:
     * [
     *   [1,1,2],
     *   [1,2,1],
     *   [2,1,1]
     * ]
     */
    @Test
    public void test1() {
        List<List<Integer>> ret = permuteUnique(new int[]{1, 1, 2});
        ret.forEach(arr -> System.out.println(arr.toString()));
        int[][] actual = ListHelper.asTwoDimArray(ret);
        int[][] expect = {
                {1, 1, 2},
                {1, 2, 1},
                {2, 1, 1}
        };
        ListHelper.checkInAnyOrder(actual, expect);
    }

    @Test
    public void test2() {
        List<List<Integer>> ret = permuteUnique(new int[]{1, 1, 0, 0, 1, -1, -1, 1});
        ret.forEach(arr -> System.out.println(arr.toString()));
    }
}
