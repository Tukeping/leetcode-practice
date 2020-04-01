package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=343 lang=java
 *
 * [343] 整数拆分
 *
 * https://leetcode-cn.com/problems/integer-break/description/
 *
 * algorithms
 * Medium (55.09%)
 * Likes:    175
 * Dislikes: 0
 * Total Accepted:    21.1K
 * Total Submissions: 38.2K
 * Testcase Example:  '2'
 *
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 *
 * 示例 2:
 *
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 *
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 *
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * math | dynamic-programming
 *
 * @author tukeping
 * @date 2020/3/31
 **/
public class LeetCode343 {

    /** time: O(n) space: O(1) **/
    public int integerBreak(int n /* 2 <= n <= 58 **/) {
        if (n == 1) return 1;
        if (n == 2) return 1;
        if (n == 3) return 2;
        if (n == 4) return 4;
        if (n == 5) return 6;
        if (n == 6) return 9;

        int pre3 = 4, pre2 = 6, pre1 = 9, cur = 0;
        for (int i = 7; i <= n; i++) {
            cur = pre3 * 3;
            pre3 = pre2;
            pre2 = pre1;
            pre1 = cur;
        }
        return cur;
    }

    /**
     * 输入: 2
     * 输出: 1
     * 解释: 2 = 1 + 1, 1 × 1 = 1
     */
    @Test
    public void test1() {
        int n = integerBreak(2);
        assertThat(n, is(1));
    }

    /**
     * 输入: 10
     * 输出: 36
     * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
     */
    @Test
    public void test2() {
        int n = integerBreak(10);
        assertThat(n, is(36));
    }

    @Test
    public void test3() {
        int n = integerBreak(11);
        assertThat(n, is(54));
    }

    @Test
    public void test4() {
        int n = integerBreak(3);
        assertThat(n, is(2));
    }

    @Test
    public void test5() {
        int n = integerBreak(6);
        assertThat(n, is(9));
    }
}
