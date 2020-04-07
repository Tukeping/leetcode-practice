package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=29 lang=java
 *
 * [29] 两数相除
 *
 * https://leetcode-cn.com/problems/divide-two-integers/description/
 *
 * algorithms
 * Medium (19.38%)
 * Likes:    312
 * Dislikes: 0
 * Total Accepted:    41.4K
 * Total Submissions: 213.2K
 * Testcase Example:  '10\n3'
 *
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 *
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 *
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) =
 * -2
 *
 *
 *
 * 示例 1:
 *
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
 *
 * 示例 2:
 *
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 解释: 7/-3 = truncate(-2.33333..) = -2
 *
 *
 *
 * 提示：
 *
 *
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31,  2^31 − 1]。本题中，如果除法结果溢出，则返回 2^31 − 1。
 *
 *
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * math | binary-search
 *
 * @author tukeping
 * @date 2020/4/7
 **/
public class LeetCode29 {

    public int divide(int dividend, int divisor) {
        long dividendL = dividend;
        long divisorL = divisor;
        long maxValue = Integer.MAX_VALUE;
        maxValue++;

        boolean negative = false;

        if (dividendL < 0) {
            negative = true;
            dividendL = -dividendL;
        }
        if (divisorL < 0) {
            negative = !negative;
            divisorL = -divisorL;
        }

        if (dividendL == maxValue && divisorL == 1)
            return negative ? dividend : Integer.MAX_VALUE;

        if (dividendL == divisorL)
            return negative ? -1 : 1;

        if (dividendL < divisorL)
            return 0;

        if (divisorL == 1)
            return negative ? -dividend : dividend;

        int ans = guessAns(dividendL, divisorL, 0);
        return negative ? -ans : ans;
    }

    private int guessAns(long dividend, long divisor, int expectAns) {
        int moveBits = closerMoveBits(dividend, divisor, 0);
        expectAns += 1 << moveBits;
        dividend -= divisor << moveBits;
        if (dividend <= divisor) return expectAns;
        else return guessAns(dividend, divisor, expectAns);
    }

    private int closerMoveBits(long n, long target, int moveBits) {
        if (n <= target) {
            return n == target ? moveBits : moveBits - 1;
        }
        n >>= 1;
        return closerMoveBits(n, target, moveBits + 1);
    }

    /**
     * 输入: dividend = 10, divisor = 3
     * 输出: 3
     * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
     */
    @Test
    public void test1() {
        int n = divide(10, 3);
        assertThat(n, is(3));
    }

    /**
     * 输入: dividend = 7, divisor = -3
     * 输出: -2
     * 解释: 7/-3 = truncate(-2.33333..) = -2
     */
    @Test
    public void test2() {
        int n = divide(7, -3);
        assertThat(n, is(-2));
    }

    @Test
    public void test3() {
        int n = divide(1, 1);
        assertThat(n, is(1));
    }

    @Test
    public void test4() {
        int n = divide(-2147483648, -1);
        assertThat(n, is(2147483647));
    }

    @Test
    public void test5() {
        int n = divide(-2147483648, 1);
        assertThat(n, is(-2147483648));
    }

    @Test
    public void test6() {
        int n = divide(2147483647, 3);
        assertThat(n, is(715827882));
    }
}
