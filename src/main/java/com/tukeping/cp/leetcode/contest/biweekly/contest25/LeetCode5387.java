package com.tukeping.cp.leetcode.contest.biweekly.contest25;

import com.tukeping.tools.ListHelper;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/5/3
 **/
public class LeetCode5387 {

    int M = 2049;
    int N = 50;
    int P = (int) (1e9) + 7;
    boolean[][] valid = new boolean[N][M];
    int[][] dp = new int[N][M];

    int inc(int x, long y) {
        return (int) (x + y) % P;
    }

    public int numberWays(List<List<Integer>> hats) {
        int n = hats.size();

        for (int i = 0; i < n; ++i) {
            for (int h : hats.get(i)) {
                valid[h][i] = true;
            }
        }

        dp[0][0] = 1;
        int m = 1 << n;

        for (int h = 1; h <= 40; ++h) {
            for (int mask = 0; mask < m; ++mask) {

                dp[h][mask] = inc(dp[h][mask], dp[h - 1][mask]);

                for (int i = 0; i < n; ++i) {
                    if ((mask & (1 << i)) != 0 && valid[h][i]) {
                        dp[h][mask] = inc(dp[h][mask], dp[h - 1][mask ^ (1 << i)]);
                    }
                }
            }
        }
        return dp[40][m - 1];
    }

    /**
     * 输入：hats = [[3,4],[4,5],[5]]
     * 输出：1
     * 解释：给定条件下只有一种方法选择帽子。
     * 第一个人选择帽子 3，第二个人选择帽子 4，最后一个人选择帽子 5。
     */
    @Test
    public void test1() {
        int[][] hats = {
                {3, 4}, {4, 5}, {5}
        };
        int n = numberWays(ListHelper.asIntegerList(hats));
        assertThat(n, is(1));
    }

    /**
     * 输入：hats = [[3,5,1],[3,5]]
     * 输出：4
     * 解释：总共有 4 种安排帽子的方法：
     * (3,5)，(5,3)，(1,3) 和 (1,5)
     */
    @Test
    public void test2() {
        int[][] hats = {
                {3, 5, 1}, {3, 5}
        };
        int n = numberWays(ListHelper.asIntegerList(hats));
        assertThat(n, is(4));
    }

    /**
     * 输入：hats = [[1,2,3,4],[1,2,3,4],[1,2,3,4],[1,2,3,4]]
     * 输出：24
     * 解释：每个人都可以从编号为 1 到 4 的帽子中选。
     * (1,2,3,4) 4 个帽子的排列方案数为 24 。
     */
    @Test
    public void test3() {
        int[][] hats = {
                {1, 2, 3, 4}, {1, 2, 3, 4}, {1, 2, 3, 4}, {1, 2, 3, 4}
        };
        int n = numberWays(ListHelper.asIntegerList(hats));
        assertThat(n, is(24));
    }

    /**
     * 输入：hats = [[1,2,3],[2,3,5,6],[1,3,7,9],[1,8,9],[2,5,7]]
     * 输出：111
     */
    @Test
    public void test4() {
        int[][] hats = {
                {1, 2, 3}, {2, 3, 5, 6}, {1, 3, 7, 9}, {1, 8, 9}, {2, 5, 7}
        };
        int n = numberWays(ListHelper.asIntegerList(hats));
        assertThat(n, is(111));
    }
}
