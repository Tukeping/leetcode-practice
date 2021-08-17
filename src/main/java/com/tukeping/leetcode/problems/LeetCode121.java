package com.tukeping.leetcode.problems;

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
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * ⁠    注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 *
 * 示例 2:
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
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

    public int maxProfitV5(int[] prices) {
        int profit = 0, buy = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            buy = Math.max(buy, -prices[i]);
            profit = Math.max(profit, buy + prices[i]);
        }
        return profit;
    }

    public int maxProfitV4(int[] prices) {
        int n = prices.length;
        int ans = 0, min = prices[0];
        for (int i = 1; i < n; i++) {
            min = Math.min(min, prices[i]);
            ans = Math.max(ans, prices[i] - min);
        }
        return ans;
    }

    public int maxProfitV3(int[] prices) {
        int n = prices.length;
        int minPrice = prices[0];
        int maxPrice = 0;
        for (int i = 1; i < n; i++) {
            if (prices[i] <= minPrice) {
                minPrice = prices[i]; // 买入
            } else {
                // 尝试每一天都作为卖出日, 在迭代过程中找到最大利润
                maxPrice = Math.max(maxPrice, prices[i] - minPrice);
            }
        }
        return maxPrice;
    }

    public int maxProfitV2(int[] prices) {
        int max = 0;
        int n = prices.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int profit = prices[j] - prices[i];
                max = Math.max(max, profit);
            }
        }
        return max;
    }

    /**
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     *               max(   选择 rest  ,           选择 sell      )
     *
     * 解释：今天我没有持有股票，有两种可能：
     * 要么是我昨天就没有持有，然后今天选择 rest，所以我今天还是没有持有；
     * 要么是我昨天持有股票，但是今天我 sell 了，所以我今天没有持有股票了。
     *
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     *               max(   选择 rest  ,           选择 buy         )
     *
     * 解释：今天我持有着股票，有两种可能：
     * 要么我昨天就持有着股票，然后今天选择 rest，所以我今天还持有着股票；
     * 要么我昨天本没有持有，但今天我选择 buy，所以今天我就持有股票了。
     *
     * dp[-1][k][0] = 0
     * 解释：因为 i 是从 0 开始的，所以 i = -1 意味着还没有开始，这时候的利润当然是 0 。
     * dp[-1][k][1] = -infinity
     * 解释：还没开始的时候，是不可能持有股票的，用负无穷表示这种不可能。
     * dp[i][0][0] = 0
     * 解释：因为 k 是从 1 开始的，所以 k = 0 意味着根本不允许交易，这时候利润当然是 0 。
     * dp[i][0][1] = -infinity
     * 解释：不允许交易的情况下，是不可能持有股票的，用负无穷表示这种不可能。
     *
     * base case：
     * dp[-1][k][0] = dp[i][0][0] = 0
     * dp[-1][k][1] = dp[i][0][1] = -infinity
     *
     * 状态转移方程：
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     *
     * k = 1
     *
     * 直接套状态转移方程，根据 base case，可以做一些化简：
     *
     * dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
     * dp[i][1][1] = max(dp[i-1][1][1], dp[i-1][0][0] - prices[i])
     *             = max(dp[i-1][1][1], -prices[i])
     * 解释：k = 0 的 base case，所以 dp[i-1][0][0] = 0。
     *
     * 现在发现 k 都是 1，不会改变，即 k 对状态转移已经没有影响了。
     * 可以进行进一步化简去掉所有 k：
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][1], -prices[i])
     */

    public int maxProfit(int[] prices) {
        return dp(prices);
    }

    /** time: O(n) space: O(n) **/
    private int dp(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;

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

    @Test
    public void test0() {
        int n;

        n = maxProfit(null);
        assertThat(n, is(0));

        n = maxProfit(new int[0]);
        assertThat(n, is(0));

        n = maxProfit(new int[]{1});
        assertThat(n, is(0));
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
