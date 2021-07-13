package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=60 lang=java
 *
 * [60] 第k个排列
 *
 * https://leetcode-cn.com/problems/permutation-sequence/description/
 *
 * algorithms
 * Medium (47.64%)
 * Likes:    173
 * Dislikes: 0
 * Total Accepted:    22.5K
 * Total Submissions: 46.7K
 * Testcase Example:  '3\n3'
 *
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 *
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 *
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 *
 * 给定 n 和 k，返回第 k 个排列。
 *
 * 说明：
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 *
 * 示例 1:
 * 输入: n = 3, k = 3
 * 输出: "213"
 *
 * 示例 2:
 * 输入: n = 4, k = 9
 * 输出: "2314"
 */

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * math | backtracking
 *
 * twitter
 *
 * permutation
 *
 * @author tukeping
 * @date 2020/2/13
 **/
public class LeetCode60 {

    /**
     * 200/200 cases passed (1792 ms)
     * Your runtime beats 5.01 % of java submissions
     * Your memory usage beats 5.28 % of java submissions (44.9 MB)
     */
    public String getPermutation(int n, int k) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++)
            nums[i] = i + 1;

        backtrack(nums, k, new LinkedList<>());

        return kThTrack == null ? null : listToString(kThTrack);
    }

    private String listToString(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        for (Integer e : list) sb.append(e);
        return sb.toString();
    }

    private int count = 0;
    private LinkedList<Integer> kThTrack;

    private void backtrack(int[] nums, int k, LinkedList<Integer> track) {
        if (track.size() == nums.length) {
            if ((++count) == k) {
                kThTrack = new LinkedList<>(track);
            }
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (track.contains(nums[i])) continue;
            track.add(nums[i]);
            backtrack(nums, k, track);
            if (kThTrack != null) return;
            track.removeLast();
        }
    }

    /**
     * 输入: n = 3, k = 3
     * 输出: "213"
     */
    @Test
    public void test1() {
        String s = getPermutation(3, 3);
        assertThat(s, is("213"));
    }

    /**
     * 输入: n = 4, k = 9
     * 输出: "2314"
     */
    @Test
    public void test2() {
        String s = getPermutation(4, 9);
        assertThat(s, is("2314"));
    }

    /**
     * 输入: n = 9, k = 305645
     * 输出: 856412937
     */
    @Test
    public void test3() {
        String s = getPermutation(9, 305645);
        assertThat(s, is("856412937"));
    }
}
