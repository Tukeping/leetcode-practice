package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=7 lang=java
 *
 * [7] 整数反转
 *
 * https://leetcode-cn.com/problems/reverse-integer/description/
 *
 * algorithms
 * Easy (33.32%)
 * Likes:    1551
 * Dislikes: 0
 * Total Accepted:    243.6K
 * Total Submissions: 730.6K
 * Testcase Example:  '123'
 *
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 *
 * 输入: 123
 * 输出: 321
 *
 *
 * 示例 2:
 *
 * 输入: -123
 * 输出: -321
 *
 *
 * 示例 3:
 *
 * 输入: 120
 * 输出: 21
 *
 *
 * 注意:
 *
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回
 * 0。
 *
 */

import org.junit.Assert;
import org.junit.Test;

/**
 * @author tukeping
 * @since 2018/12/15
 **/
public class LeetCode7 {

    public int reverse(int x) {
        String str = String.valueOf(x);
        char[] array = str.toCharArray();
        char first = array[0];
        int step = 0;
        if (first == '-') {
            step = 1;
        }
        String r = "";
        for (int i = array.length - 1; i >= step; i--) {
            r += array[i];
        }
        if (step == 1) {
            r = first + r;
        }
        Long lr = Long.parseLong(r);
        if (lr > Integer.MAX_VALUE || lr < Integer.MIN_VALUE) {
            return 0;
        }
        return lr.intValue();
    }

    @Test
    public void test1() {
        int r = reverse(123);
        Assert.assertEquals(321, r);
    }

    @Test
    public void test2() {
        int r = reverse(-123);
        Assert.assertEquals(-321, r);
    }

    @Test
    public void test3() {
        int r = reverse(120);
        Assert.assertEquals(21, r);
    }

    @Test
    public void test4() {
        int r = reverse(1534236469);
        Assert.assertEquals(0, r);
    }
}
