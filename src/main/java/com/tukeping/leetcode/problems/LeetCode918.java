package com.tukeping.leetcode.problems;

/**
 * @author tukeping
 * @date 2021/7/23
 **/
public class LeetCode918 {

    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;

        int[] dp = new int[n];
        dp[0] = nums[0];

        int max = nums[0];
        int sum = nums[0];
        for (int i = 1; i < n; i++) {
            sum += nums[i];
            dp[i] = Math.max(0, dp[i - 1]) + nums[i];
            max = Math.max(max, dp[i]);
        }

        int min = 0;
        for (int i = 1; i < n - 1; i++) {
            dp[i] = Math.min(0, dp[i - 1]) + nums[i];
            min = Math.min(min, dp[i]);
        }

        // System.out.println("sum = " + sum);
        // System.out.println("min = " + min);
        // System.out.println("sum - min = " + (sum - min));
        // System.out.println("max = " + max);

        return Math.max(sum - min, max);
    }
}
