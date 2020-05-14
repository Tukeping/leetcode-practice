package com.tukeping.cp.leetcode.contest.weekly.contest95;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author tukeping
 * @date 2020/4/27
 **/
public class LeetCode877 {

    public boolean stoneGame(int[] a) {
        int n = a.length;
        int[][] f = new int[n + 2][n + 2];
        for (int size = 1; size <= n; size++) {
            for (int L = 0, R = size - 1; R < n; L++, R++) {
                if ((L + R & 1) == 1) {
                    f[L + 1][R + 1] = Math.max(f[L + 2][R + 1] + a[L], f[L + 1][R] + a[R]);
                } else {
                    f[L + 1][R + 1] = Math.min(f[L + 2][R + 1] - a[L], f[L + 1][R] - a[R]);
                }
            }
        }
        return f[1][n] > 0;
    }

    @Test
    public void test1() {
        assertTrue(stoneGame(new int[]{5, 3, 4, 5}));
    }

    @Test
    public void test2() {
        assertFalse(stoneGame(new int[]{7, 20, 10, 5, 1, 2}));
    }

    @Test
    public void test3() {
        assertTrue(stoneGame(new int[]{3, 2, 10, 4}));
    }
}
