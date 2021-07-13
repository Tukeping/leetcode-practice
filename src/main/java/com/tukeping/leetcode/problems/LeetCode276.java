package com.tukeping.leetcode.problems;

/*
 * 栅栏涂色
 *
 * 我们有一个栅栏，它有n个柱子，现在要给柱子染色，有k种颜色可以染。
 * 必须保证不存在超过2个相邻的柱子颜色相同，求有多少种染色方案。
 *
 * n和k都是非负整数
 *
 * 样例
 * 例 1:
 * 输入: n=3, k=2
 * 输出: 6
 * Explanation:
 *           post 1,   post 2, post 3
 *     way1    0         0       1
 *     way2    0         1       0
 *     way3    0         1       1
 *     way4    1         0       0
 *     way5    1         0       1
 *     way6    1         1       0
 *
 * 例 2:
 * 输入: n=2, k=2
 * 输出: 4
 * Explanation:
 *           post 1,   post 2
 *     way1    0         0
 *     way2    0         1
 *     way3    1         0
 *     way4    1         1
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/25
 **/
public class LeetCode276 {

    public int numWays(int n, int k) {
        if (n == 0) return 0;
        if (n == 1) return k;
        if (n == 2) return k * k;

        int pre = k, cur = k * k, tmp;
        for (int i = 3; i <= n; i++) {
            tmp = cur;
            cur = (pre + cur) * (k - 1);
            pre = tmp;
        }
        return cur;
    }

    public int numWays2(int n, int k) {
        if (n == 0) return 0;
        if (n == 1) return k;
        if (n == 2) return k * k;

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = k;
        dp[2] = k * k;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] * (k - 1) + dp[i - 2] * (k - 1);
        }
        return dp[n];
    }

    /**
     * 例 1:
     *
     * 输入: n=3, k=2
     * 输出: 6
     * Explanation:
     *           post 1,   post 2, post 3
     *     way1    0         0       1
     *     way2    0         1       0
     *     way3    0         1       1
     *     way4    1         0       0
     *     way5    1         0       1
     *     way6    1         1       0
     */
    @Test
    public void test1() {
        int n = numWays(3, 2);
        assertThat(n, is(6));
    }

    /**
     * 例 2:
     *
     * 输入: n=2, k=2
     * 输出: 4
     * Explanation:
     *           post 1,   post 2
     *     way1    0         0
     *     way2    0         1
     *     way3    1         0
     *     way4    1         1
     */
    @Test
    public void test2() {
        int n = numWays(2, 2);
        assertThat(n, is(4));
    }
}
