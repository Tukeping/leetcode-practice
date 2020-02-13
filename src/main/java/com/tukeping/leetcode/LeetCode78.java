package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=78 lang=java
 *
 * [78] 子集
 *
 * https://leetcode-cn.com/problems/subsets/description/
 *
 * algorithms
 * Medium (75.68%)
 * Likes:    466
 * Dislikes: 0
 * Total Accepted:    59.7K
 * Total Submissions: 78.2K
 * Testcase Example:  '[1,2,3]'
 *
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 * ⁠ [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 *
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

/**
 * array | backtracking | bit-manipulation
 *
 * amazon | bloomberg | facebook | uber
 *
 * subsets
 *
 * @author tukeping
 * @date 2020/2/13
 **/
public class LeetCode78 {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        backtrack(nums, 0, new LinkedList<>(), res);
        return res;
    }

    private void backtrack(int[] nums, int start, LinkedList<Integer> track, List<List<Integer>> res) {
        res.add(new LinkedList<>(track));
        for (int i = start; i < nums.length; i++) {
            track.add(nums[i]);
            backtrack(nums, i + 1, track, res);
            track.removeLast();
        }
    }

    public List<List<Integer>> subsetsV2(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        output.add(new ArrayList<>());

        for (int num : nums) {
            List<List<Integer>> newSubsets = new ArrayList<>();
            for (List<Integer> curr : output) {
                newSubsets.add(new ArrayList<Integer>(curr) {{
                    add(num);
                }});
            }
            output.addAll(newSubsets);
        }

        return output;
    }

    /**
     * 输入: nums = [1,2,3]
     * 输出:
     * [
     *   [3],
     *   [1],
     *   [2],
     *   [1,2,3],
     *   [1,3],
     *   [2,3],
     *   [1,2],
     *   []
     * ]
     */
    @Test
    public void test() {
        List<List<Integer>> ret = subsets(new int[]{1, 2, 3});
        List<List<Integer>> expect = asList(
                emptyList(),
                asList(1),
                asList(2),
                asList(1, 2),
                asList(3),
                asList(1, 3),
                asList(2, 3),
                asList(1, 2, 3)
        );
        assertThat(ret, containsInAnyOrder(expect.toArray()));
    }
}
