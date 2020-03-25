package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=66 lang=java
 *
 * [66] 加一
 *
 * https://leetcode-cn.com/problems/plus-one/description/
 *
 * algorithms
 * Easy (43.56%)
 * Likes:    444
 * Dislikes: 0
 * Total Accepted:    133.4K
 * Total Submissions: 306K
 * Testcase Example:  '[1,2,3]'
 *
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 *
 *
 * 示例 2:
 *
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 *
 *
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * array
 *
 * google
 *
 * @author tukeping
 * @date 2020/3/25
 **/
public class LeetCode66 {

    public int[] plusOne(int[] digits) {
        if (digits.length == 0) return digits;

        int up = 0, len = digits.length, ans;
        digits[len - 1] = digits[len - 1] + 1;

        for (int i = len - 1; i >= 0; i--) {
            ans = digits[i] + up;
            up = ans / 10;
            digits[i] = ans % 10;
        }

        if (up > 0) {
            int[] ret = new int[len + 1];
            ret[0] = up;
            System.arraycopy(digits, 0, ret, 1, len);
            return ret;
        } else {
            return digits;
        }
    }

    /**
     * 输入: [1,2,3]
     * 输出: [1,2,4]
     * 解释: 输入数组表示数字 123。
     */
    @Test
    public void test1() {
        int[] ret = plusOne(new int[]{1, 2, 3});
        assertThat(ret, is(new int[]{1, 2, 4}));
    }

    /**
     * 输入: [4,3,2,1]
     * 输出: [4,3,2,2]
     * 解释: 输入数组表示数字 4321。
     */
    @Test
    public void test2() {
        int[] ret = plusOne(new int[]{4, 3, 2, 1});
        assertThat(ret, is(new int[]{4, 3, 2, 2}));
    }

    @Test
    public void test3() {
        int[] ret = plusOne(new int[]{9});
        assertThat(ret, is(new int[]{1, 0}));
    }

    @Test
    public void test4() {
        int[] ret = plusOne(new int[]{9, 9});
        assertThat(ret, is(new int[]{1, 0, 0}));
    }

    @Test
    public void test5() {
        int[] ret = plusOne(new int[]{1, 9, 9});
        assertThat(ret, is(new int[]{2, 0, 0}));
    }
}
