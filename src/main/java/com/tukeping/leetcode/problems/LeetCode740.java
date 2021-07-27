package com.tukeping.leetcode.problems;

/**
 * @author tukeping
 * @date 2021/7/21
 **/
public class LeetCode740 {

    public int deleteAndEarn(int[] nums) {
        int n = nums.length;
        int[] sum = new int[10001];
        for (int num : nums) {
            sum[num] += num;
        }
        int[][] dp = new int[10001][2];
        dp[0][0] = 0;
        dp[0][1] = sum[0];
        for (int i = 1; i < 10001; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = Math.max(dp[i - 1][0] + sum[i], dp[i - 1][1]);
        }
        return Math.max(dp[10000][0], dp[10000][1]);
    }
}
