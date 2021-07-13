package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=279 lang=java
 *
 * [279] 完全平方数
 *
 * https://leetcode-cn.com/problems/perfect-squares/description/
 *
 * algorithms
 * Medium (54.71%)
 * Likes:    335
 * Dislikes: 0
 * Total Accepted:    43.2K
 * Total Submissions: 79K
 * Testcase Example:  '12'
 *
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 示例 1:
 *
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 *
 * 示例 2:
 *
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 */

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * math | dynamic-programming | breadth-first-search
 *
 * google
 *
 * @author tukeping
 * @date 2020/3/18
 **/
public class LeetCode279 {

    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    public int numSquaresBFS(int n) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        boolean[] visited = new boolean[n];
        int level = 0;
        while (queue.size() > 0) {
            // 层序遍历
            level++;
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                int cur = queue.poll();
                for (int j = 1; j * j <= cur; j++) {
                    int tmp = cur - j * j;
                    // 找到答案
                    if (tmp == 0) {
                        return level;
                    }
                    if (!visited[tmp]) {
                        queue.add(tmp);
                        visited[tmp] = true;
                    }
                }
            }
        }
        return level;
    }

    public int numSquaresMath(int n) {
        if (Math.pow(Math.floor(Math.sqrt(n)), 2) == n) return 1;
        while (n % 4 == 0) {
            n = n / 4;
        }
        if ((n - 7) % 8 == 0) {
            return 4;
        }
        for (int y, x = 1; x * x < n; x++) {
            y = (int) Math.floor(Math.sqrt(n - x * x));
            if (x * x + y * y == n) return 2;
        }
        return 3;
    }

    public int numSquaresMathAndBit(int n) {
        if (Math.pow(Math.floor(Math.sqrt(n)), 2) == n) return 1;
        while ((n & 3) == 0) {
            n >>= 2;
        }
        if ((n & 7) == 7) {
            return 4;
        }
        for (int y, x = 1; x * x < n; x++) {
            y = (int) Math.floor(Math.sqrt(n - x * x));
            if (x * x + y * y == n) return 2;
        }
        return 3;
    }

    /**
     * 输入: n = 12
     * 输出: 3
     * 解释: 12 = 4 + 4 + 4.
     */
    @Test
    public void test1() {
        int n = numSquares(12);
        assertThat(n, is(3));
    }

    /**
     * 输入: n = 13
     * 输出: 2
     * 解释: 13 = 4 + 9.
     */
    @Test
    public void test2() {
        int n = numSquares(13);
        assertThat(n, is(2));
    }
}
