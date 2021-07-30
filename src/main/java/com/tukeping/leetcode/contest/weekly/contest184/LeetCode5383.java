package com.tukeping.leetcode.contest.weekly.contest184;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/12
 **/
public class LeetCode5383 {

    public int numOfWays(int n) {
        int MOD = 1000000007;
        long[] a = new long[n + 1];
        a[0] = 3;
        a[1] = 12;
        for (int i = 2; i <= n; i++) {
            a[i] = (5 * a[i - 1] - 2 * a[i - 2]) % MOD;
            System.out.println("a[" + i + "] = " + a[i]);
        }
        return (int) (a[n] % MOD + MOD) % MOD;
    }

    public int numOfWays6(int n) {
        int MOD = 1000000007;
        long[] base = new long[]{6, 6};
        for (int i = 1; i < n; i++) {
            long[] preBase = new long[]{base[0], base[1]};
            base[0] = (preBase[0] * 3 + preBase[1] * 2) % MOD;
            base[1] = (preBase[0] * 2 + preBase[1] * 2) % MOD;
        }
        return (int) (base[0] + base[1]) % MOD;
    }

    public int numOfWays5(int n) {
        int MOD = 1000000007;

        long[][] dp = new long[n][2]; // 0: ABA, 1: ABC
        dp[0][0] = 6;
        dp[0][1] = 6;

        for (int i = 1; i < n; i++) {
            dp[i][0] = (dp[i - 1][0] * 3 + dp[i - 1][1] * 2) % MOD;
            dp[i][1] = (dp[i - 1][0] * 2 + dp[i - 1][1] * 2) % MOD;
        }
        return (int) (dp[n - 1][0] + dp[n - 1][1]) % MOD;
    }

    public int numOfWays2(int n) {
        long x = 6, y = 6, MOD = 1000000007;
        for (int i = 1; i < n; i++) {
            long preX = x, preY = y;
            x = (3 * preX + 2 * preY) % MOD;
            y = (2 * preX + 2 * preY) % MOD;
        }
        return (int) ((x + y) % MOD);
    }

    public int numOfWays4(int n) {
        long[] dp = new long[27];
        int mod = 1000000007;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (i != j && j != k) {
                        dp[i * 9 + j * 3 + k]++;
                    }
                }
            }
        }

        for (int z = 1; z < n; z++) {
            long[] ndp = new long[27];

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        for (int ii = 0; ii < 3; ii++) {
                            for (int jj = 0; jj < 3; jj++) {
                                for (int kk = 0; kk < 3; kk++) {
                                    if (ii != jj && jj != kk &&
                                            i != ii && j != jj && k != kk) {
                                        ndp[ii * 9 + jj * 3 + kk] += dp[i * 9 + j * 3 + k];
                                    }
                                }
                            }
                        }
                    }
                }
            }

            for (int u = 0; u < 27; u++)
                ndp[u] %= mod;

            dp = ndp;
        }

        long ans = 0;
        for (long v : dp)
            ans += v;

        return (int) (ans % mod);
    }

    private int n, MOD = 1000000007;

    public int numOfWays3(int n) {
        this.n = n;
        return dp(0, new int[]{-1, -1, -1}) % MOD;
    }

    private int dp(int i, int[] pre) {
        if (i == n) return 1;
        int res = 0;
        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                for (int c = 0; c < 3; c++) {
                    if (a != b && b != c && a != pre[0] && b != pre[1] && c != pre[2]) {
                        res += dp(i + 1, new int[]{a, b, c});
                        res %= MOD;
                    }
                }
            }
        }
        return res;
    }

    /**
     * 输入：n = 1
     * 输出：12
     */
    @Test
    public void test1() {
        int n = numOfWays(1);
        assertThat(n, is(12));
    }

    /**
     * 输入：n = 2
     * 输出：54
     */
    @Test
    public void test2() {
        int n = numOfWays(2);
        assertThat(n, is(54));
    }

    /**
     * 输入：n = 3
     * 输出：246
     */
    @Test
    public void test3() {
        int n = numOfWays(3);
        assertThat(n, is(246));
    }

    /**
     * 输入：n = 7
     * 输出：106494
     */
    @Test
    public void test4() {
        int n = numOfWays(7);
        assertThat(n, is(106494));
    }

    /**
     * 输入：n = 5000
     * 输出：30228214
     */
    @Test
    public void test5() {
        int n = numOfWays(5000);
        assertThat(n, is(30228214));
    }
}
