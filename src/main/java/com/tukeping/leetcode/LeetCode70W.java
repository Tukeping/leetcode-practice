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
 *
 *
 */

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author tukeping
 * @date 2020/1/6
 **/
public class LeetCode70W {

    @Test
    public void test1() {
        int x = climbStairs(2);
        assertThat(x, is(2));
    }

    @Test
    public void test2() {
        int x = climbStairs(3);
        assertThat(x, is(3));
    }

    public int climbStairs(int n) {
        return climbStairs0(0, n);
    }

    public int climbStairs0(int cur, int n) {
        if (cur > n) {
            return 0;
        }
        if (cur == n) {
            return 1;
        }

        return climbStairs0(cur + 1, n) + climbStairs0(cur + 2, n);
    }
}
