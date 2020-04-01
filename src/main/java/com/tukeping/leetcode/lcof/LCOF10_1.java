package com.tukeping.leetcode.lcof;

/*
 * 面试题10- I. 斐波那契数列
 *
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：
 *
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：1
 * 示例 2：
 *
 * 输入：n = 5
 * 输出：5
 *  
 *
 * 提示：
 *
 * 0 <= n <= 100
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/27
 **/
public class LCOF10_1 {

    public int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        int MOD = 1000000007;
        int pp = 0;
        int p = 1;
        int cur = 0;
        for (int i = 2; i <= n; i++) {
            cur = (pp + p) % MOD;
            pp = p;
            p = cur;
        }
        return cur;
    }

    /**
     * 输入：n = 2
     * 输出：1
     */
    @Test
    public void test1() {
        int n = fib(2);
        assertThat(n, is(1));
    }

    /**
     * 输入：n = 5
     * 输出：5
     */
    @Test
    public void test2() {
        int n = fib(5);
        assertThat(n, is(5));
    }
}
