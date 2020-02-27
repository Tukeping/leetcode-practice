package com.tukeping.leetcode;

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
 *
 */

import org.junit.Test;

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

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        backtrack(nums, new LinkedList<>(), res);
        return res;
    }

    private void backtrack(int[] nums, LinkedList<Integer> track, List<List<Integer>> res) {
        // 触发结束条件
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }

        for (int num : nums) {
            // 排除不合法的选择
            if (track.contains(num)) continue;
            // 做选择
            track.add(num);
            // 进入下一层决策树
            backtrack(nums, track, res);
            // 取消选择
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
