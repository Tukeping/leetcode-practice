package com.tukeping.leetcode.problems;

/**
 * @author tukeping
 * @date 2021/8/17
 **/
public class LeetCode1969 {

    int MOD = (int) 1e9 + 7;

    public int minNonZeroProduct(int p) {
        long ans = 1;
        long pw = 1L << p;
        ans = ans * qpow(pw - 2, pw / 2L - 1);
        ans = ans * ((pw - 1L) % MOD) % MOD;
        return (int) ans;
    }

    private long qpow(long v, long t) {
        long ret = 1;
        for (v %= MOD; t > 0; t >>= 1) {
            if ((t & 1) == 1) ret = ret * v % MOD;
            v = v * v % MOD;
        }
        return ret;
    }
}
