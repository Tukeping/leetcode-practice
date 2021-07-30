package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=77 lang=java
 *
 * [77] 组合
 *
 * https://leetcode-cn.com/problems/combinations/description/
 *
 * algorithms
 * Medium (71.81%)
 * Likes:    215
 * Dislikes: 0
 * Total Accepted:    33.8K
 * Total Submissions: 46.5K
 * Testcase Example:  '4\n2'
 *
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * 示例:
 *
 * 输入: n = 4, k = 2
 * 输出:
 * [
 * ⁠ [2,4],
 * ⁠ [3,4],
 * ⁠ [2,3],
 * ⁠ [1,2],
 * ⁠ [1,3],
 * ⁠ [1,4],
 * ]
 *
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

/**
 * backtracking
 *
 * frequency 4
 *
 * @author tukeping
 * @date 2020/2/16
 **/
public class LeetCode77 {

    List<List<Integer>> ret = new ArrayList<>();

    public List<List<Integer>> combineV2(int n, int k) {
        helper(n, k, 1, new ArrayList<>());
        return ret;
    }

    private void helper(int n, int k, int start, List<Integer> path) {
        if (path.size() == k) {
            ret.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i <= n; i++) {
            path.add(i);
            helper(n, k, i + 1, path);
            path.remove(path.size() - 1);
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        int[] nums = new int[n];
        for (int i = 1; i <= n; i++) {
            nums[i - 1] = i;
        }
        List<List<Integer>> res = new ArrayList<>();
        combine(nums, n, k, 0, new ArrayList<>(), res);
        return res;
    }

    private void combine(int[] nums, int n, int k, int start, List<Integer> track, List<List<Integer>> res) {
        if (track.size() == k) {
            res.add(new ArrayList<>(track));
            return;
        }
        for (int i = start; i < n; i++) {
            if (track.contains(nums[i])) continue;
            track.add(nums[i]);
            combine(nums, n, k, i + 1, track, res);
            track.remove(track.size() - 1);
        }
    }

    /**
     * 输入: n = 4, k = 2
     * 输出:
     * [
     *   [2,4],
     *   [3,4],
     *   [2,3],
     *   [1,2],
     *   [1,3],
     *   [1,4],
     * ]
     */
    @Test
    public void test1() {
        List<List<Integer>> ans = combine(4, 2);
        List<List<Integer>> expect = Arrays.asList(
                Arrays.asList(2, 4),
                Arrays.asList(3, 4),
                Arrays.asList(2, 3),
                Arrays.asList(1, 2),
                Arrays.asList(1, 3),
                Arrays.asList(1, 4)
        );
        assertThat(ans, containsInAnyOrder(expect.toArray()));
    }
}
