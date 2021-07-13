package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=9 lang=java
 *
 * [9] 回文数
 *
 * https://leetcode-cn.com/problems/palindrome-number/description/
 *
 * algorithms
 * Easy (56.71%)
 * Likes:    854
 * Dislikes: 0
 * Total Accepted:    219.5K
 * Total Submissions: 386.8K
 * Testcase Example:  '121'
 *
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 *
 * 输入: 121
 * 输出: true
 *
 *
 * 示例 2:
 *
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 *
 *
 * 示例 3:
 *
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 *
 *
 * 进阶:
 *
 * 你能不将整数转为字符串来解决这个问题吗？
 *
 */

import org.junit.Assert;
import org.junit.Test;

/**
 * @author tukeping
 * @since 2018/12/15
 **/
public class LeetCode9 {

    public boolean isPalindrome(int x) {
        char[] chars = String.valueOf(x).toCharArray();
        String result = "";
        for (int i = chars.length - 1; i >= 0; i--) {
            result += chars[i];
        }
        try {
            int y = Integer.parseInt(result);
            return x == y;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Test
    public void test1() {
        boolean result = isPalindrome(121);
        Assert.assertTrue(result);
    }

    @Test
    public void test2() {
        boolean result = isPalindrome(-121);
        Assert.assertFalse(result);
    }

    @Test
    public void test3() {
        boolean result = isPalindrome(10);
        Assert.assertFalse(result);
    }
}
