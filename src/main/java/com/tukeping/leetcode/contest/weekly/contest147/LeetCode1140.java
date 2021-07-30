package com.tukeping.leetcode.contest.weekly.contest147;

/**
 * @author tukeping
 * @date 2020/5/7
 **/
public class LeetCode1140 {

    public int stoneGameII(int[] a) {
        int[][] dp = new int[105][105];
        int[] s = new int[105];
        int n = a.length, i, j, k;
        for (i = 1; i <= n; i++) s[i] = s[i - 1] + a[i - 1];
        for (i = n; i >= 1; i--) {
            for (j = 1; j <= 100; j++) {
                for (k = 1; i + k - 1 <= n && k <= 2 * j; k++) {
                    dp[i][j] = Math.max(dp[i][j], s[n] - s[i - 1] - dp[i + k][Math.min(100, Math.max(j, k))]);
                }
            }
        }
        return dp[1][1];
    }
}
