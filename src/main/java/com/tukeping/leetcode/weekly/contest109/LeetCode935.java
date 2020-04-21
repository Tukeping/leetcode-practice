package com.tukeping.leetcode.weekly.contest109;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/12
 **/
public class LeetCode935 {

    public int knightDialer4(int N) {
        if (N == 1) return 10;
        int MOD = 1_000_000_007;
        // 分别为状态A,B,C,D
        long[] nums = new long[]{1, 1, 1, 1};
        for (int i = 1; i < N; i++) {
            long[] clone = Arrays.copyOf(nums, 4);
            System.out.println(Arrays.toString(clone));
            nums[0] = (clone[1] + clone[2]) % MOD;
            nums[1] = (2 * clone[0]) % MOD;
            nums[2] = (2 * clone[0] + clone[3]) % MOD;
            nums[3] = (2 * clone[2]) % MOD;
        }
        System.out.println(Arrays.toString(nums));
        // 状态A有4个数字，B有2个数字，C有2个数字，D有1个数字
        long AB = ((4 * nums[0]) % MOD + (2 * nums[1]) % MOD) % MOD;
        long CD = ((2 * nums[2]) % MOD + (nums[3]) % MOD) % MOD;
        return (int) (AB + CD) % MOD;
    }

    public int knightDialer(int N) {
        if (N == 1) return 10;
        int MOD = 1_000_000_007;
        // 分别为状态A,B,C,D
        long A = 1, B = 1, C = 1, D = 1;
        for (int i = 1; i < N; i++) {
            long preA = A, preB = B % MOD, preC = C % MOD, preD = D;
            A = preB + preC;
            B = 2 * preA;
            C = 2 * preA + preD;
            D = 2 * preC;
        }
        // 状态A有4个数字，B有2个数字，C有2个数字，D有1个数字
        return (int) ((4 * A + 2 * B + 2 * C + D) % MOD);
    }

    private int steps;
    private int[][] grid = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
            {-1, 0, -1}
    };
    private int[][] knight = {
            {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2},
            {1, 2}, {2, 1}, {2, -1}, {1, -2}
    };
    private int[][][] memo;

    public int knightDialer3(int N) {
        if (N == 1) return 10;

        this.steps = N - 1;
        this.memo = new int[4][3][N];

        int MOD = 1_000_000_007;
        long ans = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == -1)
                    continue;

                ans += dfs(i, j, 0, 0);
                ans %= MOD;

                System.out.println("[" + i + ", " + j + "] = " + ans);
            }
        }
        return (int) ans % MOD;
    }

    private int dfs(int x, int y, int nowSteps, int ans) {
//        if (memo[x][y][nowSteps] > 0) {
//            return memo[x][y][nowSteps];
//        }
        if (nowSteps == steps) {
            return ans + 1;
        }

        for (int d = 0; d < 8; d++) {
            int sx = x + knight[d][0];
            int sy = y + knight[d][1];
            if (inGrid(sx, sy)) {
                ans = dfs(sx, sy, nowSteps + 1, ans);
//                memo[sx][sy][nowSteps + 1] = ans;
            }
        }
        return ans;
    }

    private boolean inGrid(int x, int y) {
        return 0 <= x && x < 4 && 0 <= y && y < 3 && !(x == 3 && y == 0) && !(x == 3 && y == 2);
    }

    public int knightDialer2(int N) {
        int MOD = 1_000_000_007;
        int[][] moves = new int[][]{
                {4, 6}, {6, 8}, {7, 9}, {4, 8}, {3, 9, 0},
                {}, {1, 7, 0}, {2, 6}, {1, 3}, {2, 4}};

        int[][] dp = new int[2][10];
        Arrays.fill(dp[0], 1);
        for (int hops = 0; hops < N - 1; ++hops) {
            Arrays.fill(dp[~hops & 1], 0);
            for (int node = 0; node < 10; ++node)
                for (int nei : moves[node]) {
                    dp[~hops & 1][nei] += dp[hops & 1][node];
                    dp[~hops & 1][nei] %= MOD;
                }
        }

        long ans = 0;
        for (int x : dp[~N & 1])
            ans += x;
        return (int) (ans % MOD);
    }

    /**
     * 输入：1
     * 输出：10
     */
    @Test
    public void test1() {
        int n = knightDialer(1);
        assertThat(n, is(10));
    }

    /**
     * 输入：2
     * 输出：20
     */
    @Test
    public void test2() {
        int n = knightDialer(2);
        assertThat(n, is(20));
    }

    /**
     * 输入：3
     * 输出：46
     */
    @Test
    public void test3() {
        int n = knightDialer(3);
        assertThat(n, is(46));
    }

    @Test
    public void test4() {
        int n = knightDialer(4);
        assertThat(n, is(104));
    }

    @Test
    public void test5() {
        int n = knightDialer(161);
        assertThat(n, is(533302150));
    }
}
