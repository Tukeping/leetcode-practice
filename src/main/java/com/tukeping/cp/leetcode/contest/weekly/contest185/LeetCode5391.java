package com.tukeping.cp.leetcode.contest.weekly.contest185;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/19
 **/
public class LeetCode5391 {

    private int MOD = 1000000007;

    private int addv(int x, int y) {
        x += y;
        if (x >= MOD) x -= MOD;
        return x;
    }

    private int mul(int x, int y) {
        return (int) (((long) x * y) % MOD);
    }

    public int numOfArrays(int n, int m, int k) {
        int[][][] dp = new int[n + 1][m + 1][k + 1];
        dp[0][0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int l = 1; l <= k; l++) {
                int sum = dp[i - 1][0][l - 1];
                for (int j = 1; j <= m; j++) {
                    dp[i][j][l] = mul(dp[i - 1][j][l], j);
                    dp[i][j][l] = addv(dp[i][j][l], sum);
                    sum = addv(sum, dp[i - 1][j][l - 1]);
                }
            }
        }

        int ans = 0;
        for (int i = 1; i <= m; i++) ans = addv(ans, dp[n][i][k]);

        return ans;
    }

    @Test
    public void test1() {
        int n = numOfArrays(2, 3, 1);
        assertThat(n, is(6));
    }

    @Test
    public void test2() {
        int n = numOfArrays(5, 2, 3);
        assertThat(n, is(0));
    }

    @Test
    public void test3() {
        int n = numOfArrays(9, 1, 1);
        assertThat(n, is(1));
    }

    @Test
    public void test4() {
        int n = numOfArrays(50, 100, 25);
        assertThat(n, is(34549172));
    }

    @Test
    public void test5() {
        int n = numOfArrays(37, 17, 7);
        assertThat(n, is(418930126));
    }
}
