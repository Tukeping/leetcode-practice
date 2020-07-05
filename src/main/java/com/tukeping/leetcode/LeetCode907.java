package com.tukeping.leetcode;

/**
 * @author tukeping
 * @date 2020/5/28
 **/
public class LeetCode907 {

    public int sumSubarrayMins(int[] a) {
        int n = a.length;
        int MOD = (int) 1e9 + 7;

        int[][] f = new int[n][n];

        for(int i = 0; i < n; i++) {
            for(int len = 0; i + len < n; len++) {
                if(len == 0) f[i][len] = a[i];
                else f[i][len] = Math.min(f[i][len - 1], a[i + len]);
            }
        }

        int ans = 0;
        for(int i = 0; i < n; i++)
            for(int len = 0; len < n; len++)
                ans = (ans + f[i][len]) % MOD;
        return ans;
    }
}
