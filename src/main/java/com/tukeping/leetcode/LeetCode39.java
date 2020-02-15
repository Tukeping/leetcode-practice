package com.tukeping.leetcode;

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

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

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
        List<List<Integer>> res = new LinkedList<>();
        backtrack(candidates, 0, target, new LinkedList<>(), res);
        return res;
    }

    private void backtrack(int[] nums, int start, int target, LinkedList<Integer> track, List<List<Integer>> res) {
        if (sumTrack(track) == target) {
            res.add(new LinkedList<>(track));
        }

        for (int i = start; i < nums.length; i++) {
            track.add(nums[i]);
            backtrack(nums, i + 1, target, track, res);
            track.removeLast();
        }
    }

    private Integer sumTrack(LinkedList<Integer> track) {
        return track.stream().mapToInt(Integer::intValue).sum();
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
        List<List<Integer>> res = combinationSum(new int[]{2,3,6,7}, 7);
        List<List<Integer>> expect = asList(
                asList(7),
                asList(2,2,3)
        );
        assertThat(res, containsInAnyOrder(expect.toArray()));
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

    }
}
