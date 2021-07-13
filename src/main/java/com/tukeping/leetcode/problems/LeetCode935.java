package com.tukeping.leetcode.problems;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/5/15
 **/
public class LeetCode935 {

    int[][] hops = {
            {-1, -2},
            {-2, -1},
            {-2, 1},
            {-1, 2},
            {1, 2},
            {2, 1},
            {2, -1},
            {1, -2}
    };

    int MOD = (int) 1e9 + 7;

    public int knightDialer(int n) {
        if (n == 1) return 10;

        long[][][] f = new long[4][3][n + 1];
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 3; j++)
                f[i][j][1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int x = 0; x < 4; x++) {
                for (int y = 0; y < 3; y++) {
                    for (int[] hop : hops) {
                        int sx = x + hop[0], sy = y + hop[1];
                        if (check(sx, sy)) {
                            f[x][y][i] = (f[x][y][i] + f[sx][sy][i - 1]) % MOD;
                        }
                    }
                }
            }
        }
        long ans = 0;
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 3; j++) {
                ans = (ans + f[i][j][n]) % MOD;
                System.out.println("f[" + i + "][" + j + "][" + n + "] = " + f[i][j][n] + ", ans = " + ans);
            }
        return (int) ans;
    }

    private boolean check(int x, int y) {
        return 0 <= x && x < 4 && 0 <= y && y < 3 && !(x == 3 && y == 0) && !(x == 3 && y == 2);
    }

    @Test
    public void test1() {
        int n = knightDialer(1);
        assertThat(n, is(10));
    }

    @Test
    public void test2() {
        int n = knightDialer(2);
        assertThat(n, is(20));
    }

    @Test
    public void test3() {
        int n = knightDialer(3);
        assertThat(n, is(46));
    }
}
