package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=322 lang=java
 *
 * [322] 零钱兑换
 *
 * https://leetcode-cn.com/problems/coin-change/description/
 *
 * algorithms
 * Medium (36.56%)
 * Likes:    352
 * Dislikes: 0
 * Total Accepted:    39.4K
 * Total Submissions: 105.4K
 * Testcase Example:  '[1,2,5]\n11'
 *
 * 给定不同面额的硬币 coins 和一个总金额
 * amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 示例 1:
 *
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 *
 * 示例 2:
 *
 * 输入: coins = [2], amount = 3
 * 输出: -1
 *
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 *
 */

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * dynamic-programming
 *
 * @author tukeping
 * @date 2020/2/9
 **/
public class LeetCode322 {

    public int coinChangeV2(int[] coins, int amount) {
        if (coins.length == 0) return -1;

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 2);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i >= coin) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == amount + 2 ? -1 : dp[amount];
    }

    public int coinChange(int[] coins, int amount) {
        int[] f = new int[amount + 1];
        int min = Integer.MAX_VALUE;
        int cost, coinIndex, coin;

        for (int x = 1; x <= amount; x++) {
            cost = Integer.MAX_VALUE;
            coinIndex = coins.length - 1;
            while (coinIndex >= 0) {
                coin = coins[coinIndex];
                if (x - coin >= 0)
                    cost = Math.min(cost, f[x - coin] == -1 ? Integer.MAX_VALUE : f[x - coin] + 1);
                coinIndex--;
            }
            f[x] = (cost == Integer.MAX_VALUE) ? -1 : cost;
            min = Math.min(min, f[x]);
        }
        return f[amount];
    }

    /**
     * 输入: coins = [1, 2, 5], amount = 11
     * 输出: 3
     * 解释: 11 = 5 + 5 + 1
     */
    @Test
    public void test1() {
        int n = coinChange(new int[]{1, 2, 5}, 11);
        assertThat(n, is(3));
    }

    /**
     * 输入: coins = [2], amount = 3
     * 输出: -1
     */
    @Test
    public void test2() {
        int n = coinChange(new int[]{2}, 3);
        assertThat(n, is(-1));
    }

    /**
     * [186,419,83,408]
     * 6249
     */
    @Test
    public void test3() {
        int n = coinChange(new int[]{186, 419, 83, 408}, 6249);
        assertThat(n, is(20));
    }
}
