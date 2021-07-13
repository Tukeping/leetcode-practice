package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=50 lang=java
 *
 * [50] Pow(x, n)
 *
 * https://leetcode-cn.com/problems/powx-n/description/
 *
 * algorithms
 * Medium (33.52%)
 * Likes:    260
 * Dislikes: 0
 * Total Accepted:    51.7K
 * Total Submissions: 152.7K
 * Testcase Example:  '2.00000\n10'
 *
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 *
 * 示例 1:
 *
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 *
 *
 * 示例 2:
 *
 * 输入: 2.10000, 3
 * 输出: 9.26100
 *
 *
 * 示例 3:
 *
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2^-2 = 1/2^2 = 1/4 = 0.25
 *
 * 说明:
 *
 *
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1] 。
 *
 *
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * math | binary-search
 *
 * bloomberg | facebook | google | linkedin
 *
 * @author tukeping
 * @date 2020/2/22
 **/
public class LeetCode50 {

    public double myPow1(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        double ans = 1;
        double current_product = x;
        for (long i = N; i > 0; i /= 2) {
            if ((i % 2) == 1) {
                ans = ans * current_product;
            }
            current_product = current_product * current_product;
        }
        return ans;
    }

    public double myPow(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }

        double ans = 1;
        double curProduct = x;
        for (long i = N; i > 0; i /= 2) {
            if (i % 2 == 1) {
                ans *= curProduct;
            }
            curProduct *= curProduct;
        }

        return ans;
    }

    /**
     * 输入: 2.00000, 10
     * 输出: 1024.00000
     */
    @Test
    public void test1() {
        double n = myPow(2.00000D, 10);
        assertThat(n, is(1024.00000D));
    }

    /**
     * 输入: 2.10000, 3
     * 输出: 9.26100
     */
    @Test
    public void test2() {
        double n = myPow(2.10000D, 3);
        assertThat(n, is(9.261000000000001D));
    }

    /**
     * 输入: 2.00000, -2
     * 输出: 0.25000
     * 解释: 2-2 = 1/22 = 1/4 = 0.25
     */
    @Test
    public void test3() {
        double n = myPow(2.00000D, -2);
        assertThat(n, is(0.25000D));
    }
}
