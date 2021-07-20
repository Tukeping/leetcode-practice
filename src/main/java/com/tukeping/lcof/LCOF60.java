package com.tukeping.lcof;

/*
 * 面试题60. n个骰子的点数
 *
 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
 *
 * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
 *
 * 示例 1:
 * 输入: 1
 * 输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
 *
 * 示例 2:
 * 输入: 2
 * 输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
 *
 * 限制：
 *
 * 1 <= n <= 11
 */

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/27
 **/
public class LCOF60 {

    /**
     * @param n 1 <= n <= 11
     */
    public double[] twoSum(int n) {
        int[][] dp = new int[n + 1][n * 6 + 1];
        for (int i = 1; i <= 6; i++)
            dp[1][i] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = i; j <= 6 * i; j++) {
                for (int k = 1; k <= Math.min(j, 6); k++) {
                    dp[i][j] += dp[i - 1][j - k];
                }
            }
        }
        double total = Math.pow(6, n);
        double[] ans = new double[6 * n - n + 1];
        for (int i = n, idx = 0; i <= 6 * n; i++, idx++) {
            ans[idx] = round(dp[n][i] / total);
        }
        return ans;
    }

    private double round(double d) {
        return new BigDecimal(d).setScale(5, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 输入: 1
     * 输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
     */
    @Test
    public void test1() {
        double[] ans = twoSum(1);
        assertThat(ans, is(new double[]{0.16667, 0.16667, 0.16667, 0.16667, 0.16667, 0.16667}));
    }

    /**
     * 输入: 2
     * 输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
     */
    @Test
    public void test2() {
        double[] ans = twoSum(2);
        assertThat(ans, is(new double[]{0.02778, 0.05556, 0.08333, 0.11111, 0.13889, 0.16667, 0.13889, 0.11111, 0.08333, 0.05556, 0.02778}));
    }
}
