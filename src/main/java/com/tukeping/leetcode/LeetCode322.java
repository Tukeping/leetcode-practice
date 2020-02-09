package com.tukeping.leetcode;

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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/2/9
 **/
public class LeetCode322 {

    /**
     * 说明:
     * 你可以认为每种硬币的数量是无限的。
     */
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) return -1;
        if (amount == 0) return 0;

        List<Integer> coins2 = Arrays.stream(coins)
                .filter(coin -> coin <= amount)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        // 回溯 + 贪心
        return backtrackingGreedy(coins2, amount, 0);
    }

    public int backtrackingGreedy(List<Integer> coins, int amount, int index) {
        if (amount == 0) return 0;

        if (index < coins.size() && amount > 0) {
            int coin = coins.get(index);
            index++;
            if (amount >= coin) {
                int count = amount / coin;
                int rest = backtrackingGreedy(coins, amount % coin, index);
                if (rest == -1) {
                    return -1;
                } else {
                    return count + rest;
                }
            } else { // amount < coin
                return backtrackingGreedy(coins, amount, index);
            }
        }

        return -1;
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
