package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=829 lang=java
 *
 * [829] 连续整数求和
 *
 * https://leetcode-cn.com/problems/consecutive-numbers-sum/description/
 *
 * algorithms
 * Hard (29.78%)
 * Likes:    56
 * Dislikes: 0
 * Total Accepted:    3.5K
 * Total Submissions: 11.3K
 * Testcase Example:  '5'
 *
 * 给定一个正整数 N，试求有多少组连续正整数满足所有数字之和为 N?
 *
 * 示例 1:
 *
 *
 * 输入: 5
 * 输出: 2
 * 解释: 5 = 5 = 2 + 3，共有两组连续整数([5],[2,3])求和后为 5。
 *
 * 示例 2:
 *
 *
 * 输入: 9
 * 输出: 3
 * 解释: 9 = 9 = 4 + 5 = 2 + 3 + 4
 *
 * 示例 3:
 *
 *
 * 输入: 15
 * 输出: 4
 * 解释: 15 = 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
 *
 * 说明: 1 <= N <= 10 ^ 9
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * hash-table
 *
 * Unknown
 *
 * @author tukeping
 * @date 2020/3/6
 **/
public class LeetCode829 {

    public int consecutiveNumbersSum(int N) {
        /*
         * 1个数时，必然有一个数可构成N
         * 2个数若要构成N，第2个数与第1个数差为1，N减掉这个1能整除2则能由商与商+1构成N
         * 3个数若要构成N，第2个数与第1个数差为1，第3个数与第1个数的差为2，N减掉1再减掉2能整除3则能由商、商+1与商+2构成N
         * 依次内推，当商即第1个数小于等于0时结束
         */
        int count = 0, i = 1;
        while (N > 0) {
            if (N % i == 0) count++;
            N -= i++;
        }
        return count;
    }

    public int consecutiveNumbersSum2(int N) {
        int i = 1, count = 0;
        while (N > 0) {
            N -= i++;
            if (N % i == 0) count++;
        }
        return count + 1;
    }

    /**
     * 输入: 5
     * 输出: 2
     * 解释: 5 = 5 = 2 + 3，共有两组连续整数([5],[2,3])求和后为 5。
     */
    @Test
    public void test1() {
        int n = consecutiveNumbersSum(5);
        assertThat(n, is(2));
    }

    /**
     * 输入: 9
     * 输出: 3
     * 解释: 9 = 9 = 4 + 5 = 2 + 3 + 4
     */
    @Test
    public void test2() {
        int n = consecutiveNumbersSum(9);
        assertThat(n, is(3));
    }

    /**
     * 输入: 15
     * 输出: 4
     * 解释: 15 = 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
     */
    @Test
    public void test3() {
        int n = consecutiveNumbersSum(15);
        assertThat(n, is(4));
    }
}
