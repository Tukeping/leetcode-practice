package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=39 lang=java
 *
 * [39] 组合总和
 *
 * https://leetcode-cn.com/problems/combination-sum/description/
 *
 * algorithms
 * Medium (67.53%)
 * Likes:    527
 * Dislikes: 0
 * Total Accepted:    62.6K
 * Total Submissions: 91.6K
 * Testcase Example:  '[2,3,6,7]\n7'
 *
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 *
 *
 * 示例 1:
 *
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 * ⁠ [7],
 * ⁠ [2,2,3]
 * ]
 *
 *
 * 示例 2:
 *
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 *
 */

import com.tukeping.tools.ListHelper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * array | backtracking
 *
 * snapchat | uber
 *
 * @author tukeping
 * @date 2020/2/14
 **/
public class LeetCode39 {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        helper(candidates, candidates.length, 0, 0, target, new LinkedList<>(), res);
        return res;
    }

    private void helper(int[] nums, int len, int start, int sum, int target, LinkedList<Integer> track, List<List<Integer>> res) {
        if (sum == target) {
            res.add(new ArrayList<>(track));
            return;
        } else if (sum > target) {
            return;
        }

        for (int i = start; i < len && sum + nums[i] <= target; i++) {
            sum += nums[i];
            track.add(nums[i]);
            helper(nums, len, i, sum, target, track, res);
            sum -= nums[i];
            track.removeLast();
        }
    }

    /**
     * 输入: candidates = [2,3,6,7], target = 7,
     * 所求解集为:
     * [
     *   [7],
     *   [2,2,3]
     * ]
     */
    @Test
    public void test1() {
        int[][] actual = ListHelper.asTwoDimArray(combinationSum(new int[]{2, 3, 6, 7}, 7));
        int[][] expect = {
                {7, 0, 0},
                {2, 2, 3}
        };
        ListHelper.checkInAnyOrder(actual, expect);
    }

    /**
     * 输入: candidates = [2,3,5], target = 8,
     * 所求解集为:
     * [
     *   [2,2,2,2],
     *   [2,3,3],
     *   [3,5]
     * ]
     */
    @Test
    public void test2() {
        int[][] actual = ListHelper.asTwoDimArray(combinationSum(new int[]{2, 3, 5}, 8));
        int[][] expect = {
                {2, 2, 2, 2},
                {2, 3, 3, 0},
                {3, 5, 0, 0}
        };
        ListHelper.checkInAnyOrder(actual, expect);
    }

    @Test
    public void test3() {
        int[][] actual = ListHelper.asTwoDimArray(combinationSum(new int[]{2, 3, 6, 7}, 7));
        int[][] expect = {
                {2, 2, 3},
                {7, 0, 0}
        };
        ListHelper.checkInAnyOrder(actual, expect);
    }

    @Test
    public void test4() {
        int[][] actual = ListHelper.asTwoDimArray(combinationSum(new int[]{2, 3, 5}, 8));
        int[][] expect = {
                {2, 2, 2, 2},
                {2, 3, 3, 0},
                {3, 5, 0, 0}
        };
        ListHelper.checkInAnyOrder(actual, expect);
    }

    @Test
    public void test5() {
        int[][] actual = ListHelper.asTwoDimArray(combinationSum(new int[]{7, 3, 2}, 18));
        int[][] expect = {
                {2, 2, 2, 2, 2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2, 2, 3, 3, 0},
                {2, 2, 2, 2, 3, 7, 0, 0, 0},
                {2, 2, 2, 3, 3, 3, 3, 0, 0},
                {2, 2, 7, 7, 0, 0, 0, 0, 0},
                {2, 3, 3, 3, 7, 0, 0, 0, 0},
                {3, 3, 3, 3, 3, 3, 0, 0, 0}
        };
        ListHelper.checkInAnyOrder(actual, expect);
    }
}
