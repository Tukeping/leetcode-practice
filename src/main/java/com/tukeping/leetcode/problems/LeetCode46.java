package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=46 lang=java
 *
 * [46] 全排列
 *
 * https://leetcode-cn.com/problems/permutations/description/
 *
 * algorithms
 * Medium (73.10%)
 * Likes:    521
 * Dislikes: 0
 * Total Accepted:    76.1K
 * Total Submissions: 102.9K
 * Testcase Example:  '[1,2,3]'
 *
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 * ⁠ [1,2,3],
 * ⁠ [1,3,2],
 * ⁠ [2,1,3],
 * ⁠ [2,3,1],
 * ⁠ [3,1,2],
 * ⁠ [3,2,1]
 * ]
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * backtracking
 *
 * linkedin | microsoft
 *
 * frequency 4
 *
 * permutation
 *
 * @author tukeping
 * @date 2020/2/13
 **/
public class LeetCode46 {

    List<List<Integer>> retV2 = new ArrayList<>();

    public List<List<Integer>> permuteV3(int[] nums) {
        permuteV3(nums, new ArrayList<>());
        return retV2;
    }

    private void permuteV3(int[] nums, List<Integer> path) {
        if (path.size() == nums.length) {
            retV2.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (path.contains(nums[i])) continue;
            path.add(nums[i]);
            permuteV3(nums, path);
            path.remove(path.size() - 1);
        }
    }

    List<List<Integer>> ret = new ArrayList<>();

    public List<List<Integer>> permuteV2(int[] nums) {
        helper(nums, nums.length, new ArrayList<>());
        return ret;
    }

    private void helper(int[] nums, int k, List<Integer> path) {
        if (path.size() == k) {
            ret.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < k; i++) {
            if (path.contains(nums[i])) continue;
            path.add(nums[i]);
            helper(nums, k, path);
            path.remove(path.size() - 1);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        permute(nums, new LinkedList<>(), res);
        return res;
    }

    private void permute(int[] nums, LinkedList<Integer> track, List<List<Integer>> res) {
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }

        for (int num : nums) {
            if (track.contains(num)) continue;
            track.add(num);
            permute(nums, track, res);
            track.removeLast();
        }
    }

    /**
     * 输入: [1,2,3]
     * 输出:
     * [
     *   [1,2,3],
     *   [1,3,2],
     *   [2,1,3],
     *   [2,3,1],
     *   [3,1,2],
     *   [3,2,1]
     * ]
     */
    @Test
    public void test() {
        List<List<Integer>> ret = permute(new int[]{1, 2, 3});
        List<List<Integer>> expect = asList(
                asList(1, 2, 3),
                asList(1, 3, 2),
                asList(2, 1, 3),
                asList(2, 3, 1),
                asList(3, 1, 2),
                asList(3, 2, 1)
        );
        assertThat(ret, is(expect));
    }
}
