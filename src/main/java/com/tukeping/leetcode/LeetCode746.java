package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=746 lang=java
 *
 * [746] 使用最小花费爬楼梯
 *
 * https://leetcode-cn.com/problems/min-cost-climbing-stairs/description/
 *
 * algorithms
 * Easy (47.37%)
 * Likes:    245
 * Dislikes: 0
 * Total Accepted:    26K
 * Total Submissions: 54.8K
 * Testcase Example:  '[0,0,0,0]'
 *
 * 数组的每个索引做为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。
 *
 * 每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。
 *
 * 您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。
 *
 * 示例 1:
 *
 *
 * 输入: cost = [10, 15, 20]
 * 输出: 15
 * 解释: 最低花费是从cost[1]开始，然后走两步即可到阶梯顶，一共花费15。
 *
 *
 * 示例 2:
 *
 *
 * 输入: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 * 输出: 6
 * 解释: 最低花费方式是从cost[0]开始，逐个经过那些1，跳过cost[3]，一共花费6。
 *
 *
 * 注意：
 *
 *
 * cost 的长度将会在 [2, 1000]。
 * 每一个 cost[i] 将会是一个Integer类型，范围为 [0, 999]。
 *
 *
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * trie
 *
 * @author tukeping
 * @date 2020/3/23
 **/
public class LeetCode746 {

    public int minCostClimbingStairs(int[] cost) {
        int f1 = 0, f2 = 0;
        for (int i = cost.length - 1; i >= 0; --i) {
            int f0 = cost[i] + Math.min(f1, f2);
            f2 = f1;
            f1 = f0;
        }
        return Math.min(f1, f2);
    }

    public int minCostClimbingStairs2(int[] cost) {
        int len = cost.length;

        if (len == 0) return 0;
        if (len == 1) return cost[0];
        if (len == 2) return Math.min(cost[0], cost[1]);

        int[] dp = new int[len];
        dp[0] = cost[0];
        dp[1] = cost[1];

        int min = Integer.MAX_VALUE;
        for (int i = 2; i < len; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i], dp[i - 2] + cost[i]);
            min = Math.min(dp[i - 1], dp[i]);
        }

        return min;
    }

    /**
     * 输入: cost = [10, 15, 20]
     * 输出: 15
     * 解释: 最低花费是从cost[1]开始，然后走两步即可到阶梯顶，一共花费15。
     */
    @Test
    public void test1() {
        int n = minCostClimbingStairs(new int[]{10, 15, 20});
        assertThat(n, is(15));
    }

    /**
     * 输入: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
     * 输出: 6
     * 解释: 最低花费方式是从cost[0]开始，逐个经过那些1，跳过cost[3]，一共花费6。
     */
    @Test
    public void test2() {
        int n = minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1});
        assertThat(n, is(6));
    }
}
