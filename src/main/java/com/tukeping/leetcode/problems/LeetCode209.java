package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=209 lang=java
 *
 * [209] 长度最小的子数组
 *
 * https://leetcode-cn.com/problems/minimum-size-subarray-sum/description/
 *
 * algorithms
 * Medium (40.39%)
 * Likes:    197
 * Dislikes: 0
 * Total Accepted:    27.8K
 * Total Submissions: 67.4K
 * Testcase Example:  '7\n[2,3,1,2,4,3]'
 *
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。
 *
 * 示例: 
 *
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 *
 * 进阶:
 *
 * 如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * array | two-pointers | binary-search
 *
 * facebook
 *
 * @author tukeping
 * @date 2020/2/23
 **/
public class LeetCode209 {

    /*
     * 15/15 cases passed (2 ms)
     * Your runtime beats 89.78 % of java submissions
     * Your memory usage beats 5.05 % of java submissions (42.6 MB)
     */

    /**
     * 进阶:
     * 如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
     */

    public int minSubArrayLen(int s, int[] nums) {
        // 连续子数组 一般用滑动窗口来解决问题会更好
        int len = nums.length, min = nums.length, sum = 0, p1 = 0, p2 = 0;

        while (p2 < len) {
            if (sum + nums[p2] < s) {
                sum += nums[p2];
                p2++;
            } else {
                min = Math.min(min, p2 - p1 + 1);
                sum -= nums[p1];
                p1++;
            }
        }

        if (p1 == 0 && sum < s) {
            return 0;
        }

        return min;
    }

    /**
     * 输入: s = 7, nums = [2,3,1,2,4,3]
     * 输出: 2
     * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
     */
    @Test
    public void test1() {
        int n = minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3});
        assertThat(n, is(2));
    }

    @Test
    public void test2() {
        int n = minSubArrayLen(3, new int[]{1, 1});
        assertThat(n, is(0));
    }
}
