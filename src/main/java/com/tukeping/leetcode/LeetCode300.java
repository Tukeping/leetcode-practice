package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=300 lang=java
 *
 * [300] 最长上升子序列
 *
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/description/
 *
 * algorithms
 * Medium (43.49%)
 * Likes:    420
 * Dislikes: 0
 * Total Accepted:    48K
 * Total Submissions: 109.2K
 * Testcase Example:  '[10,9,2,5,3,7,101,18]'
 *
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 *
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 *
 * 说明:
 *
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n^2) 。
 *
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * binary-search | dynamic-programming
 *
 * microsoft
 *
 * @author tukeping
 * @date 2020/2/15
 **/
public class LeetCode300 {

    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        int k = 0;
        int[] f = new int[n];
        f[0] = nums[0];

        for (int i = 1; i < n; i++) {
            if (nums[i] > f[k]) f[++k] = nums[i];
            else {
                int l = 0, r = k;
                while (l < r) {
                    int m = l + r >> 1;
                    if (f[m] < nums[i]) l = m + 1;
                    else r = m;
                }
                f[l] = nums[i];
            }
        }
        return k + 1;
    }

    /** time: O(n log n) space: O(n) **/
    public int lengthOfLIS2(int[] a) {
        // corner case
        if (a == null || a.length == 0) return 0;

        int len = a.length;
        int[] f = new int[len];
        f[0] = a[0];
        int fLen = 1;

        // time O(n) => for x in len
        for (int x = 1; x < len; x++) {
            if (a[x] > f[fLen - 1]) // 单调递增
                f[fLen++] = a[x];
            else // 考虑替换f数组中比a[x]大的数字
                bsearch(f, fLen, a[x]);
        }

        return fLen;
    }

    /** time O(log n) space: O(1) **/
    private void bsearch(int[] f, int fLen, int x) {
        int start = 0, end = fLen, mid;
        while (start < end) {
            mid = (start + end) / 2;
            if (f[mid] >= x)
                end = mid;
            else
                start = mid + 1;
        }
        f[end] = Math.min(f[end], x);
    }

    /**
     * 输入: [10,9,2,5,3,7,101,18]
     * 输出: 4
     * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
     */
    @Test
    public void test() {
        int n = lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18});
        assertThat(n, is(4));
    }

    /**
     * 输入: [2,2]
     * 输出: 1
     */
    @Test
    public void test4() {
        int n = lengthOfLIS(new int[]{2, 2});
        assertThat(n, is(1));
    }

    /**
     * 输入: [4,10,4,3,8,9]
     * 输出: 3
     */
    @Test
    public void test5() {
        int n = lengthOfLIS(new int[]{4, 10, 4, 3, 8, 9});
        assertThat(n, is(3));
    }

    /**
     * 输入: [1,3,6,7,9,4,10,5,6]
     * 输出: 6
     */
    @Test
    public void test6() {
        int n = lengthOfLIS(new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6});
        assertThat(n, is(6));
    }
}
