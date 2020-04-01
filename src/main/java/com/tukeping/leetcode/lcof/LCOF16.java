package com.tukeping.leetcode.lcof;

/*
 * 面试题16. 数值的整数次方
 *
 * 实现函数double Power(double base, int exponent)，求base的exponent次方。不得使用库函数，同时不需要考虑大数问题。
 *
 * 示例 1:
 *
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 *
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例 3:
 *
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 *  
 *
 * 说明:
 *
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/26
 **/
public class LCOF16 {

    public double myPow(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }

        double ans = 1;
        double x1 = x;
        for (long i = N; i > 0; i = i / 2) {
            if (i % 2 == 1) {
                ans = ans * x1;
            }
            x1 = x1 * x1;
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

    @Test
    public void test4() {
        double n = myPow(1.00000D, 2147483647);
        assertThat(n, is(1D));
    }

    @Test
    public void test5() {
        double n = myPow(2.00000D, -2147483648);
        assertThat(n, is(0D));
    }
}
