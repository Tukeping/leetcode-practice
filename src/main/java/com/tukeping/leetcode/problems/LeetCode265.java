package com.tukeping.leetcode.problems;

/*
 * 516. 房屋染色 II
 *
 * 这里有n个房子在一列直线上，现在我们需要给房屋染色，共有k种颜色。
 * 每个房屋染不同的颜色费用也不同，你需要设计一种染色方案使得相邻的房屋颜色不同，并且费用最小。
 *
 * 费用通过一个nxk 的矩阵给出，比如cost[0][0]表示房屋0染颜色0的费用，cost[1][2]表示房屋1染颜色2的费用。
 *
 * 所有费用都是正整数
 *
 * 样例1
 * 输入:
 * costs = [[14,2,11],[11,14,5],[14,3,10]]
 * 输出: 10
 * 说明:
 * 三个屋子分别使用第1,2,1种颜色，总花费是10。
 *
 * 样例2
 * 输入:
 * costs = [[5]]
 * 输出: 5
 *
 * 说明：
 * 只有一种颜色，一个房子，花费为5
 *
 * 挑战
 * 用O(nk)的时间复杂度解决
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/25
 **/
public class LeetCode265 {

    /**
     * @param costs: n x k cost matrix
     * @return an integer, the minimum cost to paint all houses
     */
    public int minCostII(int[][] costs) {
        int n = costs.length;
        if (n == 0) return 0;

        int k = costs[0].length;

        int[][] dp = new int[n + 1][k];
        for (int i = 0; i < k; i++) dp[0][i] = 0;

        int min;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < k; j++) {
                min = Integer.MAX_VALUE;
                for (int m = 0; m < k; m++) {
                    if (m == j) continue;
                    min = Math.min(min, dp[i - 1][m]);
                }
                dp[i][j] = (min == Integer.MAX_VALUE ? 0 : min) + costs[i - 1][j];
            }
        }

        min = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            min = Math.min(min, dp[n][i]);
        }
        return min;
    }

    /**
     * 输入:
     * costs = [[14,2,11],[11,14,5],[14,3,10]]
     * 输出: 10
     * 说明:
     * 三个屋子分别使用第1,2,1种颜色，总花费是10。
     */
    @Test
    public void test1() {
        int[][] costs = {
                {14, 2, 11},
                {11, 14, 5},
                {14, 3, 10}
        };
        int n = minCostII(costs);
        assertThat(n, is(10));
    }

    /**
     * 输入:
     * costs = [[5]]
     * 输出: 5
     * 说明：
     * 只有一种颜色，一个房子，花费为5
     */
    @Test
    public void test2() {
        int[][] costs = {
                {5}
        };
        int n = minCostII(costs);
        assertThat(n, is(5));
    }
}
