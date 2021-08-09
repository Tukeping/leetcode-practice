package com.tukeping.leetcode.problems;

/**
 * @author tukeping
 * @date 2021/8/2
 **/
public class LeetCode1955 {

    public int countSpecialSubsequences(int[] nums) {
        int n = nums.length;
        if (n < 3) return 0;

        long MOD = (int) 1e9 + 7;
        long[][] dp = new long[n + 1][3];
        for (int i = 1; i <= n; i++) {
            int v = nums[i - 1];
            for (int k = 0; k < 3; k++) {
                dp[i][k] = dp[i - 1][k];
            }
            if (v == 0) {
                dp[i][v] = (dp[i][v] + 1) % MOD;
            }
            dp[i][v] = (dp[i][v] + dp[i - 1][v]) % MOD;
            if (v > 0) {
                dp[i][v] = (dp[i][v] + dp[i - 1][v - 1]) % MOD;
            }
        }
        return (int) dp[n][2];
    }
}
