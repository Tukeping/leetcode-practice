package com.tukeping.leetcode.problems;

import java.util.Arrays;

/**
 * @author tukeping
 * @date 2021/8/15
 **/
public class LeetCode135 {

    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n - 1; i++) {
            if (ratings[i + 1] > ratings[i]) {
                dp[i + 1] = dp[i] + 1;
            }
        }
        for (int i = n - 1; i > 0; i--) {
            if (ratings[i - 1] > ratings[i] && dp[i - 1] <= dp[i]) {
                dp[i - 1] = dp[i] + 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) ans += dp[i];
        return ans;
    }
}
