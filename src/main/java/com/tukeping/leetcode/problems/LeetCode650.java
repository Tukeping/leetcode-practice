package com.tukeping.leetcode.problems;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/26
 **/
public class LeetCode650 {

    public int minStepsV2(int n) {
        int[] dp = new int[n + 1];
        int h = (int) Math.sqrt(n);
        for (int i = 2; i <= n; i++) {
            dp[i] = i;
            for (int j = 2; j <= h; j++) {
                if (i % j == 0) {
                    dp[i] = dp[j] + dp[i / j];
                    break;
                }
            }
        }
        return dp[n];
    }

    public int minSteps2(int n) {
        int ans = 0;
        int d = 2;
        while (n > 1) {
            while (n % d == 0) {
                ans += d;
                n /= d;
            }
            d += 1;
        }
        return ans;
    }

    public int minSteps(int n) {
        int[] f = new int[n + 1];
        int h = (int) Math.sqrt(n);
        for (int i = 2; i <= n; i++) {
            f[i] = i;
            for (int j = 2; j <= h; j++) {
                if (i % j == 0) {
                    f[i] = f[j] + f[i / j];
                    break;
                }
            }
        }
        return f[n];
    }

    @Test
    public void test1() {
        int n = minSteps(9);
        assertThat(n, is(6));
    }

    @Test
    public void test2() {
        int n = minSteps(3);
        assertThat(n, is(3));
    }

    @Test
    public void test3() {
        int n = minSteps(7);
        assertThat(n, is(7));
    }

    @Test
    public void test4() {
        int n = minSteps(10);
        assertThat(n, is(7));
    }
}
