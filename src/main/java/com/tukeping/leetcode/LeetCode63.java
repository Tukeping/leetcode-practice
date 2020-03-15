package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=63 lang=java
 *
 * [63] 不同路径 II
 *
 * https://leetcode-cn.com/problems/unique-paths-ii/description/
 *
 * algorithms
 * Medium (32.05%)
 * Likes:    228
 * Dislikes: 0
 * Total Accepted:    41.3K
 * Total Submissions: 127.3K
 * Testcase Example:  '[[0,0,0],[0,1,0],[0,0,0]]'
 *
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 *
 *
 *
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 *
 * 说明：m 和 n 的值均不超过 100。
 *
 * 示例 1:
 *
 * 输入:
 * [
 * [0,0,0],
 * [0,1,0],
 * [0,0,0]
 * ]
 * 输出: 2
 * 解释:
 * 3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/2/21
 **/
public class LeetCode63 {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] f = new int[m][n];
        for (int column = 0; column < n; column++) {
            if (obstacleGrid[0][column] == 1) {
                f[0][column] = 0;
            } else if (column > 0) {
                f[0][column] = f[0][column - 1];
            } else {
                f[0][column] = 1;
            }
        }

        for (int row = 0; row < m; row++) {
            if (obstacleGrid[row][0] == 1) {
                f[row][0] = 0;
            } else if (row > 0) {
                f[row][0] = f[row - 1][0];
            } else {
                f[row][0] = 1;
            }
        }


        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    f[i][j] = 0;
                } else {
                    f[i][j] = f[i - 1][j] + f[i][j - 1];
                }
            }
        }

        return f[m - 1][n - 1];
    }

    /**
     * 输入:
     * [
     *   [0,0,0],
     *   [0,1,0],
     *   [0,0,0]
     * ]
     * 输出: 2
     * 解释:
     * 3x3 网格的正中间有一个障碍物。
     * 从左上角到右下角一共有 2 条不同的路径：
     * 1. 向右 -> 向右 -> 向下 -> 向下
     * 2. 向下 -> 向下 -> 向右 -> 向右
     */
    @Test
    public void test1() {
        int[][] grid = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        int n = uniquePathsWithObstacles(grid);
        assertThat(n, is(2));
    }

    @Test
    public void test2() {
        int[][] grid = {
                {1, 0}
        };
        int n = uniquePathsWithObstacles(grid);
        assertThat(n, is(0));
    }

    @Test
    public void test3() {
        int[][] grid = {
                {0},
                {1}
        };
        int n = uniquePathsWithObstacles(grid);
        assertThat(n, is(0));
    }
}
