package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=90 lang=java
 *
 * [90] 子集 II
 *
 * https://leetcode-cn.com/problems/subsets-ii/description/
 *
 * algorithms
 * Medium (57.91%)
 * Likes:    160
 * Dislikes: 0
 * Total Accepted:    21.5K
 * Total Submissions: 36.6K
 * Testcase Example:  '[1,2,2]'
 *
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: [1,2,2]
 * 输出:
 * [
 * ⁠ [2],
 * ⁠ [1],
 * ⁠ [1,2,2],
 * ⁠ [2,2],
 * ⁠ [1,2],
 * ⁠ []
 * ]
 *
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

/**
 * array | backtracking
 *
 * facebook
 *
 * subsets
 *
 * @author tukeping
 * @date 2020/2/13
 **/
public class LeetCode90 {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);
        backtrack(nums, 0, new LinkedList<>(), res);
        return res;
    }

    private void backtrack(int[] nums, int start, LinkedList<Integer> track, List<List<Integer>> res) {
        if (!res.contains(track))
            res.add(new LinkedList<>(track));

        for (int i = start; i < nums.length; i++) {
            track.add(nums[i]);
            backtrack(nums, i + 1, track, res);
            track.removeLast();
        }
    }

    public List<List<Integer>> subsetsWithDupV2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> each = new ArrayList<>();
        helper(res, each, 0, nums);
        return res;
    }

    public void helper(List<List<Integer>> res, List<Integer> each, int pos, int[] n) {
        if (pos <= n.length) {
            res.add(each);
        }
        int i = pos;
        while (i < n.length) {
            each.add(n[i]);
            helper(res, new ArrayList<>(each), i + 1, n);
            each.remove(each.size() - 1);
            i++;
            while (i < n.length && n[i] == n[i - 1]) {
                i++;
            }
        }
    }

    /**
     * 输入: [1,2,2]
     * 输出:
     * [
     *   [2],
     *   [1],
     *   [1,2,2],
     *   [2,2],
     *   [1,2],
     *   []
     * ]
     */
    @Test
    public void test1() {
        List<List<Integer>> ret = subsetsWithDup(new int[]{1, 2, 2});
        ret.forEach(a -> System.out.println(a.toString()));
        List<List<Integer>> expect = asList(
                asList(2),
                asList(1),
                asList(1, 2, 2),
                asList(2, 2),
                asList(1, 2),
                emptyList()
        );
        assertThat(ret, containsInAnyOrder(expect.toArray()));
    }

    /**
     * 输入: [4,4,4,1,4]
     * 输出: [
     *  [],
     *  [1],
     *  [1,4],
     *  [1,4,4],
     *  [1,4,4,4],
     *  [1,4,4,4,4],
     *  [4],
     *  [4,4],
     *  [4,4,4],
     *  [4,4,4,4]
     * ]
     */
    @Test
    public void test2() {
        List<List<Integer>> ret = subsetsWithDup(new int[]{4, 4, 4, 1, 4});
        ret.forEach(a -> System.out.println(a.toString()));
        List<List<Integer>> expect = asList(
                emptyList(),
                asList(1),
                asList(1, 4),
                asList(1, 4, 4),
                asList(1, 4, 4, 4),
                asList(1, 4, 4, 4, 4),
                asList(4),
                asList(4, 4),
                asList(4, 4, 4),
                asList(4, 4, 4, 4)
        );
        assertThat(ret, containsInAnyOrder(expect.toArray()));
    }
}
