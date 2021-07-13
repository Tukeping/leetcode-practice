package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=69 lang=java
 *
 * [69] x 的平方根
 *
 * https://leetcode-cn.com/problems/sqrtx/description/
 *
 * algorithms
 * Easy (37.27%)
 * Likes:    276
 * Dislikes: 0
 * Total Accepted:    81.9K
 * Total Submissions: 219.5K
 * Testcase Example:  '4'
 *
 * 实现 int sqrt(int x) 函数。
 *
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 *
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 * 示例 1:
 *
 * 输入: 4
 * 输出: 2
 *
 *
 * 示例 2:
 *
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 * 由于返回类型是整数，小数部分将被舍去。
 */

import com.tukeping.tools.annotation.Cost;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * math | binary-search
 *
 * apple | bloomberg | facebook
 *
 * frequency 4
 *
 * @author tukeping
 * @date 2020/1/2
 **/
public class LeetCode69 {

    /*
     * base case:
     *
     * 0^(1/2) = 0
     * 1^(1/2) = 1
     * 2^(1/2) = 1.414
     * 3^(1/2) = 1.732
     * 4^(1/2) = 2
     * 5^(1/2) = 2.236
     * 6^(1/2) = 2.449
     * 7^(1/2) = 2.656
     * 8^(1/2) = 2.828
     * 9^(1/2) = 3
     * 10^(1/2) = 3.162
     */

    /** time: O(log n) O(1) **/
    public int mySqrt(int x) {
        int[] cache = new int[]{0, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3};
        if (x <= 10) return cache[x];

        int end = Math.min(x / 2, 46340); // 2147483647 => 46340

        return bsearch(3, end, x);
    }

    private int bsearch(int start, int end, int x) {
        int mid = Integer.MAX_VALUE, comp;
        while (start <= end) {
            mid = (start + end) / 2;
            comp = square(mid);
            if (comp == x) return mid;
            if (comp > x || comp < 0)
                end = mid - 1;
            else // mid < x
                start = mid + 1;
        }
        return Math.min(end, mid);
    }

    private int square(int x) {
        return x * x;
    }

    private int digits(int n) {
        int d = 0;
        while (true) {
            n = n / 10;
            if (n > 0) d++;
            else break;
        }
        return d;
    }

    private int loop(int x) {
        // 3 <= n <= x/2
        for (int i = 0; i < x / 2; i++) {
            if (square(i) == x) return i;
            if (square(i) > x && square(i - 1) < x) return i - 1;
        }
        return -1;
    }

    /**
     * 输入: 4
     * 输出: 2
     */
    @Test
    public void test1() {
        int n = mySqrt(4);
        assertThat(n, is(2));
    }

    /**
     * 输入: 8
     * 输出: 2
     * 说明: 8 的平方根是 2.82842...,
     *      由于返回类型是整数，小数部分将被舍去。
     */
    @Test
    public void test2() {
        int n = mySqrt(8);
        assertThat(n, is(2));
    }

    @Test
    public void test3() {
        int n = mySqrt(11); //3.3166
        assertThat(n, is(3));
    }

    @Test
    @Cost
    public void test4() {
        // 9988373 ^ (1/2) => 3160
        int n = mySqrt(9988373);
        assertThat(n, is(3160));
    }

    @Test
    public void test5() {
        int n = mySqrt(2147395599);
        assertThat(n, is(46339));
    }

    @Test
    public void test6() {
        int n = mySqrt(2147483647);
        assertThat(n, is(46340));
    }

    @Test
    public void test7() {
        int n = mySqrt(980857);
        assertThat(n, is(990));
    }

    @Test
    @Cost
    public void test() {
        for (int i = 11; i <= Integer.MAX_VALUE / 100; i++) {
            int a = (int) Math.floor(Math.sqrt(i));
            int b = mySqrt(i);
            try {
                assertThat(b, is(a));
            } catch (AssertionError e) {
                System.out.println(String.format("i = %d, right = %d, wrong = %d", i, a, b));
            }
        }
    }
}
