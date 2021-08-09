package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=53 lang=java
 *
 * [53] 最大子序和
 *
 * https://leetcode-cn.com/problems/maximum-subarray/description/
 *
 * algorithms
 * Easy (48.40%)
 * Likes:    1634
 * Dislikes: 0
 * Total Accepted:    162.2K
 * Total Submissions: 329.3K
 * Testcase Example:  '[-2,1,-3,4,-1,2,1,-5,4]'
 *
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 * 进阶:
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * array | divide-and-conquer | dynamic-programming
 *
 * bloomberg | linkedin | microsoft
 *
 * @author tukeping
 * @date 2020/2/21
 **/
public class LeetCode53 {

    public int maxSubArrayV5(int[] nums) {
        int n = nums.length;
        int preSum = nums[0], maxSum = nums[0];
        for (int i = 1; i < n; i++) {
            preSum = Math.max(0, preSum) + nums[i];
            maxSum = Math.max(maxSum, preSum);
        }
        return maxSum;
    }

    public int maxSubArrayV4(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(0, dp[i - 1]) + nums[i];
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] f = new int[n];
        f[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < n; i++) {
            f[i] = Math.max(f[i - 1], 0) + nums[i];
            max = Math.max(max, f[i]);
        }
        return max;
    }

    public int maxSubArray2(int[] nums) {
        int n = nums.length, maxSum = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i - 1] > 0) nums[i] += nums[i - 1];
            maxSum = Math.max(maxSum, nums[i]);
        }
        return maxSum;
    }

    public int maxSubArrayV3(int[] nums) {
        int n = nums.length;
        int ans = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i - 1] > 0) nums[i] += nums[i - 1];
            ans = Math.max(ans, nums[i]);
        }
        return ans;
    }

    /**
     * 输入: [-2,1,-3,4,-1,2,1,-5,4]
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     */
    @Test
    public void test1() {
        int n = maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        assertThat(n, is(6));
    }

    @Test
    public void test2() {
        int n = maxSubArray(new int[]{1});
        assertThat(n, is(1));
    }

    @Test
    public void test3() {
        int n = maxSubArray(new int[]{1, 2});
        assertThat(n, is(3));
    }
}
