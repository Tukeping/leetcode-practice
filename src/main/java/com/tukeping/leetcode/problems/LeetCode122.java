package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=122 lang=java
 *
 * [122] 买卖股票的最佳时机 II
 *
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/description/
 *
 * algorithms
 * Easy (56.61%)
 * Likes:    588
 * Dislikes: 0
 * Total Accepted:    116.3K
 * Total Submissions: 202.5K
 * Testcase Example:  '[7,1,5,3,6,4]'
 *
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 * 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 *
 * 示例 2:
 *
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4
 * 。
 * 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 * 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 *
 * 示例 3:
 *
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * array | greedy
 *
 * bloomberg
 *
 * @author tukeping
 * @date 2020/2/16
 **/
public class LeetCode122 {

    public int maxProfitV2(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            // dp[i][0]=max{dp[i−1][0],dp[i−1][1]+prices[i]}
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // dp[i][1]=max{dp[i−1][1],dp[i−1][0]−prices[i]}
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
        }
        // for(int i = 0; i < n; i++) {
        //     System.out.println("dp["+i+"][0] = " + dp[i][0] + ", dp["+i+"][1] = " + dp[i][1]);
        // }
        return dp[n - 1][0];
    }

    /**
     * {@link LeetCode121}
     *
     * k = +infinity
     *
     * 如果 k 为正无穷，那么就可以认为 k 和 k - 1 是一样的。可以这样改写框架：
     *
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     *             = max(dp[i-1][k][1], dp[i-1][k][0] - prices[i])
     *
     * 我们发现数组中的 k 已经不会改变了，也就是说不需要记录 k 这个状态了：
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
     */

    public int maxProfit(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int dp_i_0_ = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, dp_i_0_ - prices[i]);
        }
        return dp_i_0;
    }

    /**
     * 输入: [7,1,5,3,6,4]
     * 输出: 7
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
     */
    @Test
    public void test1() {
        int n = maxProfit(new int[]{7, 1, 5, 3, 6, 4});
        assertThat(n, is(7));
    }

    /**
     * 输入: [1,2,3,4,5]
     * 输出: 4
     * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
     */
    @Test
    public void test2() {
        int n = maxProfit(new int[]{1, 2, 3, 4, 5});
        assertThat(n, is(4));
    }

    /**
     * 输入: [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     */
    @Test
    public void test3() {
        int n = maxProfit(new int[]{7, 6, 4, 3, 1});
        assertThat(n, is(0));
    }
}
