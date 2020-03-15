package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=231 lang=java
 *
 * [231] 2的幂
 *
 * https://leetcode-cn.com/problems/power-of-two/description/
 *
 * algorithms
 * Easy (47.15%)
 * Likes:    168
 * Dislikes: 0
 * Total Accepted:    49.4K
 * Total Submissions: 103.3K
 * Testcase Example:  '1'
 *
 * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
 *
 * 示例 1:
 *
 * 输入: 1
 * 输出: true
 * 解释: 2^0 = 1
 *
 * 示例 2:
 *
 * 输入: 16
 * 输出: true
 * 解释: 2^4 = 16
 *
 * 示例 3:
 *
 * 输入: 218
 * 输出: false
 *
 */

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * math | bit-manipulation
 *
 * google
 *
 * @author tukeping
 * @date 2020/3/14
 **/
public class LeetCode231 {

    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    public boolean isPowerOfTwo2(int n) {
        if (n <= 0) return false;
        if (n == 1) return true;
        if (n % 2 == 1) return false;
        while ((n = n / 2) > 2) {
            if (n % 2 == 1) return false;
        }
        return true;
    }

    /**
     * 输入: 1
     * 输出: true
     * 解释: 20 = 1
     */
    @Test
    public void test1() {
        boolean b = isPowerOfTwo(1);
        assertTrue(b);
    }

    /**
     * 输入: 16
     * 输出: true
     * 解释: 24 = 16
     */
    @Test
    public void test2() {
        boolean b = isPowerOfTwo(16);
        assertTrue(b);
    }

    /**
     * 输入: 218
     * 输出: false
     */
    @Test
    public void test3() {
        boolean b = isPowerOfTwo(218);
        assertFalse(b);
    }
}
