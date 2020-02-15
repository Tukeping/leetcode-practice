package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=121 lang=java
 *
 * [121] 买卖股票的最佳时机
 *
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/description/
 *
 * algorithms
 * Easy (51.65%)
 * Likes:    734
 * Dislikes: 0
 * Total Accepted:    125.8K
 * Total Submissions: 240.5K
 * Testcase Example:  '[7,1,5,3,6,4]'
 *
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 *
 * 注意你不能在买入股票前卖出股票。
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * ⁠    注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 *
 *
 * 示例 2:
 *
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 *
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * array | dynamic-programming
 *
 * amazon | bloomberg | facebook | microsoft | uber
 *
 * stock problems
 *
 * @author tukeping
 * @date 2020/2/15
 **/
public class LeetCode121 {

    public int maxProfit(int[] prices) {
        return maxProfitLambda(prices);
    }

    /** time: O(n) space: O(n) **/
    private int dp(int[] prices) {
        int len = prices.length;
        int[][] f = new int[len][2];

        // dp[i][0] = max(dp[-1][0], dp[-1][1] + prices[i])
        //          = max(0, -infinity + prices[i]) = 0
        f[0][0] = 0;

        // dp[i][1] = max(dp[-1][1], dp[-1][0] - prices[i])
        //          = max(-infinity, 0 - prices[i])
        //          = -prices[i]
        f[0][1] = -prices[0];

        for (int x = 1; x < len; x++) {
            f[x][0] = Math.max(f[x - 1][0], f[x - 1][1] + prices[x]);
            f[x][1] = Math.max(f[x - 1][1], -prices[x]);
        }

        return f[len - 1][0];
    }

    private int dp2(int[] prices) {
        int len = prices.length;

        // base case: dp[-1][0] = 0, dp[-1][1] = -infinity
        int d_i_0 = 0, d_i_1 = Integer.MIN_VALUE;

        for (int x = 0; x < len; x++) {
            // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
            d_i_0 = Math.max(d_i_0, d_i_1 + prices[x]);
            // dp[i][1] = max(dp[i-1][1], -prices[i])
            d_i_1 = Math.max(d_i_1, -prices[x]);
        }

        return d_i_0;
    }

    /** 微积分 **/
    private int maxProfitLambda(int[] prices) {
        int last = 0, profit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            // 累计差值最大
            // prices[i + 1] - prices[i] 表示后面的股票-前面股票价格 求得的差值
            // last + diff[i] 表示之前的最大差值 + 新增的差值
            last = Math.max(0, last + prices[i + 1] - prices[i]);
            profit = Math.max(profit, last);
        }
        return profit;
    }

    private int maxProfitMinMax(int[] prices) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }

    /** time: O(n^2) space: O(1) **/
    private int loop(int[] prices) {
        int len = prices.length;
        int max = 0;
        for (int i = len - 1; i >= 0; i--)
            for (int j = i - 1; j >= 0; j--)
                if (prices[i] >= prices[j])
                    max = Math.max(max, prices[i] - prices[j]);
        return max;
    }

    /**
     * 输入: [7,1,5,3,6,4]
     * 输出: 5
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
     */
    @Test
    public void test1() {
        int n = maxProfit(new int[]{7, 1, 5, 3, 6, 4});
        assertThat(n, is(5));
    }

    /**
     * 输入: [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     */
    @Test
    public void test2() {
        int n = maxProfit(new int[]{7, 6, 4, 3, 1});
        assertThat(n, is(0));
    }
}
