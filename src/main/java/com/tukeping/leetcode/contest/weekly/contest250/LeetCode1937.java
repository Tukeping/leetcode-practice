package com.tukeping.leetcode.contest.weekly.contest250;

/**
 * @author tukeping
 * @date 2021/7/19
 **/
public class LeetCode1937 {

    public long maxPoints(int[][] points) {
        int rowLen = points.length;
        int colLen = points[0].length;
        long[][] dp = new long[rowLen][colLen];
        for (int col = 0; col < colLen; col++) {
            dp[0][col] = points[0][col];
        }
        long inf = (long) 1e18;
        for (int i = 1; i < rowLen; i++) {
            long max = -inf;
            for (int j = 0; j < colLen; j++) {
                // for(int k = 0; k < colLen; k++) {
                //     // abs(c1 - c2)
                //     int losePoint = Math.abs(k - j);
                //     long sumPoint = dp[i-1][k] + points[i][j] - losePoint;
                //     dp[i][j] = Math.max(dp[i][j], sumPoint);
                // }
                max = Math.max(max, dp[i - 1][j] + j);
                dp[i][j] = Math.max(dp[i][j], points[i][j] + max - j);
            }
            max = -inf;
            for (int j = colLen - 1; j >= 0; j--) {
                max = Math.max(max, dp[i - 1][j] - j);
                dp[i][j] = Math.max(dp[i][j], points[i][j] + max + j);
            }
        }
        long max = 0;
        for (int i = 0; i < colLen; i++) {
            max = Math.max(max, dp[rowLen - 1][i]);
        }
        return max;
    }
}
