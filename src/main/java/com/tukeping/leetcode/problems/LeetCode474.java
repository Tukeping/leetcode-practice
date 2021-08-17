package com.tukeping.leetcode.problems;

/**
 * @author tukeping
 * @date 2021/8/12
 **/
public class LeetCode474 {

    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String str : strs) {
            int[] count = count(str);
            int count0 = count[0], count1 = count[1];
            for (int i = m; i >= count0; i--) {
                for (int j = n; j >= count1; j--) {
                    dp[i][j] = Math.max(dp[i][j], 1 + dp[i - count0][j - count1]);
                }
            }
        }
        return dp[m][n];
    }

    private int[] count(String s) {
        int count0 = s.length(), count1 = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                count1++;
                count0--;
            }
        }
        return new int[]{count0, count1};
    }
}
