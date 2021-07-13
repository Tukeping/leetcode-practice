package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=674 lang=java
 *
 * [674] 最长连续递增序列
 *
 * https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence/description/
 *
 * algorithms
 * Easy (43.31%)
 * Likes:    59
 * Dislikes: 0
 * Total Accepted:    16.4K
 * Total Submissions: 37.5K
 * Testcase Example:  '[1,3,5,4,7]'
 *
 * 给定一个未经排序的整数数组，找到最长且连续的的递增序列。
 *
 * 示例 1:
 *
 *
 * 输入: [1,3,5,4,7]
 * 输出: 3
 * 解释: 最长连续递增序列是 [1,3,5], 长度为3。
 * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为5和7在原数组里被4隔开。
 *
 *
 * 示例 2:
 *
 *
 * 输入: [2,2,2,2,2]
 * 输出: 1
 * 解释: 最长连续递增序列是 [2], 长度为1。
 *
 *
 * 注意：数组长度不会超过10000。
 *
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * array
 *
 * facebook
 *
 * @author tukeping
 * @date 2020/2/12
 **/
public class LeetCode674 {

    /**
     * 36/36 cases passed (3 ms)
     * Your runtime beats 10.84 % of java submissions
     * Your memory usage beats 5.04 % of java submissions (47.5 MB)
     */
    public int findLengthOfLCIS(int[] nums) {
        int count = 0, max = 0;

        for (int i = 0; i < nums.length; i++) {
            count++;
            max = Math.max(count, max);
            if (i + 1 < nums.length && nums[i] >= nums[i + 1]) { // make sure i+1 do not outOfIndex
                count = 0;
            }
        }

        return max;
    }

    /**
     * 输入: [1,3,5,4,7]
     * 输出: 3
     * 解释: 最长连续递增序列是 [1,3,5], 长度为3。
     * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为5和7在原数组里被4隔开。
     */
    @Test
    public void test1() {
        int n = findLengthOfLCIS(new int[]{1, 3, 5, 4, 7});
        assertThat(n, is(3));
    }

    /**
     * 输入: [2,2,2,2,2]
     * 输出: 1
     * 解释: 最长连续递增序列是 [2], 长度为1。
     */
    @Test
    public void test2() {
        int n = findLengthOfLCIS(new int[]{2, 2, 2, 2, 2});
        assertThat(n, is(1));
    }
}
