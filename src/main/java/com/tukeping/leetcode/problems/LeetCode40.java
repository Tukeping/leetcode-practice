package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=40 lang=java
 *
 * [40] 组合总和 II
 *
 * https://leetcode-cn.com/problems/combination-sum-ii/description/
 *
 * algorithms
 * Medium (58.84%)
 * Likes:    211
 * Dislikes: 0
 * Total Accepted:    41.3K
 * Total Submissions: 68.5K
 * Testcase Example:  '[10,1,2,7,6,1,5]\n8'
 *
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 *
 *
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 *
 *
 * 示例 1:
 *
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 * ⁠ [1, 7],
 * ⁠ [1, 2, 5],
 * ⁠ [2, 6],
 * ⁠ [1, 1, 6]
 * ]
 *
 *
 * 示例 2:
 *
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 * [1,2,2],
 * [5]
 * ]
 *
 */

import com.tukeping.tools.ListHelper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * array | backtracking
 *
 * snapchat
 *
 * @author tukeping
 * @date 2020/2/27
 **/
public class LeetCode40 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // corner case
        if (candidates == null) return null;
        int len = candidates.length;
        if (len == 0) return Collections.emptyList();

        Arrays.sort(candidates);

        List<List<Integer>> res = new ArrayList<>();
        helper(candidates, len, 0, 0, target, new LinkedList<>(), new boolean[len], res);
        return res;
    }

    private void helper(int[] nums, int len, int start, int sum, int target,
                        LinkedList<Integer> track, boolean[] used, List<List<Integer>> res) {
        if (sum == target) {
            if (!res.contains(track)) {
                res.add(new ArrayList<>(track));
            }
            return;
        }

        for (int i = start; i < len && sum + nums[i] <= target; i++) {
            if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            sum += nums[i];
            track.add(nums[i]);
            used[i] = true;
            helper(nums, len, i, sum, target, track, used, res);
            sum -= nums[i];
            track.removeLast();
            used[i] = false;
        }
    }

    /**
     * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
     * 所求解集为:
     * [
     *   [1, 7],
     *   [1, 2, 5],
     *   [2, 6],
     *   [1, 1, 6]
     * ]
     */
    @Test
    public void test1() {
        List<List<Integer>> res = combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
        int[][] actual = ListHelper.asTwoDimArray(res);
        int[][] expect = {
                {1, 7, 0},
                {1, 2, 5},
                {2, 6, 0},
                {1, 1, 6}
        };
        ListHelper.checkInAnyOrder(actual, expect);
    }
}
