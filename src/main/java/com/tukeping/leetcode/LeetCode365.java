package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=365 lang=java
 *
 * [365] 水壶问题
 *
 * https://leetcode-cn.com/problems/water-and-jug-problem/description/
 *
 * algorithms
 * Medium (30.07%)
 * Likes:    91
 * Dislikes: 0
 * Total Accepted:    7.4K
 * Total Submissions: 23.5K
 * Testcase Example:  '3\n5\n4'
 *
 * 有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？
 *
 * 如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。
 *
 * 你允许：
 *
 *
 * 装满任意一个水壶
 * 清空任意一个水壶
 * 从一个水壶向另外一个水壶倒水，直到装满或者倒空
 *
 *
 * 示例 1: (From the famous "Die Hard" example)
 *
 * 输入: x = 3, y = 5, z = 4
 * 输出: True
 *
 *
 * 示例 2:
 *
 * 输入: x = 2, y = 6, z = 5
 * 输出: False
 *
 *
 */

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * math
 *
 * microsoft
 *
 * @author tukeping
 * @date 2020/3/21
 **/
public class LeetCode365 {

    public boolean canMeasureWater(int x, int y, int z) {
        if (x + y < z) return false;
        if (x == 0 || y == 0) return z == 0 || x + y == z;
        return z % gcd(x, y) == 0;
    }

    private int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }

    /**
     * 输入: x = 3, y = 5, z = 4
     * 输出: True
     */
    @Test
    public void test1() {
        assertTrue(canMeasureWater(3, 5, 4));
    }

    /**
     * 输入: x = 2, y = 6, z = 5
     * 输出: False
     */
    @Test
    public void test2() {
        assertFalse(canMeasureWater(2, 6, 5));
    }
}
