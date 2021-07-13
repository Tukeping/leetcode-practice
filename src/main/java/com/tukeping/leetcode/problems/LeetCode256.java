package com.tukeping.leetcode.problems;

/*
 * 粉刷房子
 *
 * 这里有n个房子在一列直线上，现在我们需要给房屋染色，分别有红色蓝色和绿色。每个房屋染不同的颜色费用也不同，你需要设计一种染色方案使得相邻的房屋颜色不同，并且费用最小，返回最小的费用。
 *
 * 费用通过一个nx3 的矩阵给出，比如cost[0][0]表示房屋0染红色的费用，cost[1][2]表示房屋1染绿色的费用。
 *
 * 所有费用都是正整数
 *
 * 样例 1:
 * 输入: [[14,2,11],[11,14,5],[14,3,10]]
 * 输出: 10
 * 解释: 第一个屋子染蓝色，第二个染绿色，第三个染蓝色，最小花费：2 + 5 + 3 = 10.
 *
 * 样例 2:
 * 输入: [[1,2,3],[1,4,6]]
 * 输出: 3
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/25
 **/
public class LeetCode256 {

    /**
     * @param costs: n x 3 cost matrix
     * @return An integer, the minimum cost to paint all houses
     */
    public int minCost(int[][] costs) {
        int n = costs.length;
        if (n == 0) return 0;

        int[][] dp = new int[n + 1][3];
        for (int i = 0; i < 3; i++) dp[0][i] = 0;

        for (int i = 1; i <= n; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i - 1][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i - 1][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i - 1][2];
        }

        return Math.min(dp[n][0], Math.min(dp[n][1], dp[n][2]));
    }

    /**
     * 输入: [[14,2,11],[11,14,5],[14,3,10]]
     * 输出: 10
     * 解释: 第一个屋子染蓝色，第二个染绿色，第三个染蓝色，最小花费：2 + 5 + 3 = 10.
     */
    @Test
    public void test1() {
        int[][] costs = {
                {14, 2, 11},
                {11, 14, 5},
                {14, 3, 10}
        };
        int n = minCost(costs);
        assertThat(n, is(10));
    }

    /**
     * 输入: [[1,2,3],[1,4,6]]
     * 输出: 3
     */
    @Test
    public void test2() {
        int[][] costs = {
                {1, 2, 3},
                {1, 4, 6}
        };
        int n = minCost(costs);
        assertThat(n, is(3));
    }
}
