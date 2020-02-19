package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=70 lang=java
 *
 * [70] 爬楼梯
 *
 * https://leetcode-cn.com/problems/climbing-stairs/description/
 *
 * algorithms
 * Easy (47.43%)
 * Likes:    773
 * Dislikes: 0
 * Total Accepted:    118K
 * Total Submissions: 248.5K
 * Testcase Example:  '2'
 *
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 *
 * 示例 2：
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 */

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * dynamic-programming
 *
 * adobe | apple
 *
 * frequency 5
 *
 * @author tukeping
 * @date 2020/1/6
 **/
public class LeetCode70 {

    /** time: O(n) space:O(n) **/
    public int climbStairs(int n) {
        int[] f = new int[n <= 2 ? 3 : n + 1];
        f[0] = 0;
        f[1] = 1;
        f[2] = 2;
        if (n <= 2) return f[n];

        int max = Integer.MIN_VALUE;

        for (int x = 3; x <= n; x++) {
            int cost = Integer.MIN_VALUE;
            if (x - 1 >= 0) cost = f[x - 1];
            if (x - 2 >= 0) cost += f[x - 2];
            f[x] = cost;
            System.out.println(String.format("f[%d] = %d", x, f[x]));
            max = Math.max(max, f[x]);
        }

        return max;
    }

    @Test
    public void test0() {
        int n = climbStairs(1);
        assertThat(n, is(1));
    }

    @Test
    public void test1() {
        int n = climbStairs(2);
        assertThat(n, is(2));
    }

    @Test
    public void test2() {
        int n = climbStairs(3);
        assertThat(n, is(3));
    }

    @Test
    public void test3() {
        int n = climbStairs(4);
        assertThat(n, is(5));
    }

    @Test
    public void test4() {
        int n = climbStairs(5);
        assertThat(n, is(8));
    }
}
