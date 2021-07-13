package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=64 lang=java
 *
 * [64] 最小路径和
 *
 * https://leetcode-cn.com/problems/minimum-path-sum/description/
 *
 * algorithms
 * Medium (63.60%)
 * Likes:    372
 * Dislikes: 0
 * Total Accepted:    55.1K
 * Total Submissions: 85.7K
 * Testcase Example:  '[[1,3,1],[1,5,1],[4,2,1]]'
 *
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 * 示例:
 *
 * 输入:
 * [
 * [1,3,1],
 * ⁠ [1,5,1],
 * ⁠ [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * array | dynamic-programming
 *
 * @author tukeping
 * @date 2020/2/5
 **/
public class LeetCode64 {

    private int[][] mem;

    public int minPathSum(int[][] grid) {
        int R = grid.length, C = grid[0].length;
        mem = new int[R][C];
        return dp(grid, R - 1, C - 1);
    }

    public int dp(int[][] grid, int r, int c) {
        if (r == 0 && c == 0) return grid[0][0];
        int len = Integer.MAX_VALUE;
        if (r - 1 >= 0) {
            if (mem[r - 1][c] != 0) {
                len = Math.min(len, mem[r - 1][c] + grid[r][c]);
            } else {
                int temp = dp(grid, r - 1, c);
                mem[r - 1][c] = temp;
                len = Math.min(len, mem[r - 1][c] + grid[r][c]);
            }
        }
        if (c - 1 >= 0) {
            if (mem[r][c - 1] != 0) {
                len = Math.min(len, mem[r][c - 1] + grid[r][c]);
            } else {
                int temp = dp(grid, r, c - 1);
                mem[r][c - 1] = temp;
                len = Math.min(len, mem[r][c - 1] + grid[r][c]);
            }
        }
        return len;
    }

    /** DP time: O(m*n) space: O(1) **/
    public int minPathSum4(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j > 0) grid[i][j] += grid[i][j - 1];
                else if (j == 0 && i > 0) grid[i][j] += grid[i - 1][j];
                else if (i > 0) grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        return grid[m - 1][n - 1];
    }

    /** DP time: O(m*n) space: O(m) **/
    public int minPathSum3(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j > 0) dp[j] = grid[i][j] + dp[j - 1];
                else if (j == 0 && i > 0) dp[j] = grid[i][j] + dp[j];
                else if (i > 0) dp[j] = grid[i][j] + Math.min(dp[j], dp[j - 1]);
                else dp[j] = grid[i][j];
            }
        }
        return dp[n - 1];
    }

    /** DP time: O(m*n) space: O(m*n) **/
    public int minPathSum2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            System.arraycopy(grid[i], 0, dp[i], 0, n);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j > 0) dp[i][j] += dp[i][j - 1];
                else if (j == 0 && i > 0) dp[i][j] += dp[i - 1][j];
                else if (i > 0) dp[i][j] += Math.min(dp[i][j - 1], dp[i - 1][j]);
            }
        }

        return dp[m - 1][n - 1];
    }

    /** BruteForce time: O(2^(m+n)) space: O(m+n) **/
    public int minPathSum(int m, int n, int[][] grid) {
        if (m == grid.length || n == grid[0].length) return Integer.MAX_VALUE;
        if (m == grid.length - 1 && n == grid[0].length - 1) return grid[m][n];
        return grid[m][n] + Math.min(
                minPathSum(m + 1, n, grid),
                minPathSum(m, n + 1, grid));
    }

    @Test
    public void test() {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}};

        // 1→3→1→1→1
        int n = minPathSum(grid);
        assertThat(n, is(7));
    }

    @Test
    public void test1() {
        int[][] grid = {
                {3, 8, 6, 0, 5, 9, 9, 6, 3, 4, 0, 5, 7, 3, 9, 3},
                {0, 9, 2, 5, 5, 4, 9, 1, 4, 6, 9, 5, 6, 7, 3, 2},
                {8, 2, 2, 3, 3, 3, 1, 6, 9, 1, 1, 6, 6, 2, 1, 9},
                {1, 3, 6, 9, 9, 5, 0, 3, 4, 9, 1, 0, 9, 6, 2, 7},
                {8, 6, 2, 2, 1, 3, 0, 0, 7, 2, 7, 5, 4, 8, 4, 8},
                {4, 1, 9, 5, 8, 9, 9, 2, 0, 2, 5, 1, 8, 7, 0, 9},
                {6, 2, 1, 7, 8, 1, 8, 5, 5, 7, 0, 2, 5, 7, 2, 1},
                {8, 1, 7, 6, 2, 8, 1, 2, 2, 6, 4, 0, 5, 4, 1, 3},
                {9, 2, 1, 7, 6, 1, 4, 3, 8, 6, 5, 5, 3, 9, 7, 3},
                {0, 6, 0, 2, 4, 3, 7, 6, 1, 3, 8, 6, 9, 0, 0, 8},
                {4, 3, 7, 2, 4, 3, 6, 4, 0, 3, 9, 5, 3, 6, 9, 3},
                {2, 1, 8, 8, 4, 5, 6, 5, 8, 7, 3, 7, 7, 5, 8, 3},
                {0, 7, 6, 6, 1, 2, 0, 3, 5, 0, 8, 0, 8, 7, 4, 3},
                {0, 4, 3, 4, 9, 0, 1, 9, 7, 7, 8, 6, 4, 6, 9, 5},
                {6, 5, 1, 9, 9, 2, 2, 7, 4, 2, 7, 2, 2, 3, 7, 2},
                {7, 1, 9, 6, 1, 2, 7, 0, 9, 6, 6, 4, 4, 5, 1, 0},
                {3, 4, 9, 2, 8, 3, 1, 2, 6, 9, 7, 0, 2, 4, 2, 0},
                {5, 1, 8, 8, 4, 6, 8, 5, 2, 4, 1, 6, 2, 2, 9, 7}
        };

        int n = minPathSum(grid);
        System.out.println(n);
        assertThat(n, is(83));
    }
}
