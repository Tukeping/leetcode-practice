package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=59 lang=java
 *
 * [59] 螺旋矩阵 II
 *
 * https://leetcode-cn.com/problems/spiral-matrix-ii/description/
 *
 * algorithms
 * Medium (76.81%)
 * Likes:    168
 * Dislikes: 0
 * Total Accepted:    30.5K
 * Total Submissions: 39.7K
 * Testcase Example:  '3'
 *
 * 给定一个正整数 n，生成一个包含 1 到 n^2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 *
 * 示例:
 *
 * 输入: 3
 * 输出:
 * [
 * ⁠[ 1, 2, 3 ],
 * ⁠[ 8, 9, 4 ],
 * ⁠[ 7, 6, 5 ]
 * ]
 *
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/7
 **/
public class LeetCode59 {

    public int[][] generateMatrix(int n) {
        int[][] coord = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int d = 0, low = 0, high = n - 1, x = 0, y = 0, originX = 0, originY = 0, i = 1;
        int[][] ans = new int[n][n];
        while (validCoord(x, y, low, high)) {
            ans[x][y] = i++;
            int sx = x + coord[d][0];
            int sy = y + coord[d][1];
            if (validCoord(sx, sy, low, high)) {
                x = sx;
                y = sy;
            } else {
                d = (d + 1) % 4;
                x += coord[d][0];
                y += coord[d][1];
            }
            if (x == originX && y == originY) {
                x++;
                y++;
                originX++;
                originY++;
                low++;
                high--;
                d = (d + 1) % 4;
            }
        }
        return ans;
    }

    private boolean validCoord(int x, int y, int low, int high) {
        return low <= x && x <= high && low <= y && y <= high;
    }

    /**
     * 输入: 3
     * 输出:
     * [
     *  [ 1, 2, 3 ],
     *  [ 8, 9, 4 ],
     *  [ 7, 6, 5 ]
     * ]
     */
    @Test
    public void test1() {
        int[][] ans = generateMatrix(3);
        int[][] expect = {
                {1, 2, 3},
                {8, 9, 4},
                {7, 6, 5}
        };
        assertThat(ans, is(expect));
    }

    @Test
    public void test2() {
        int[][] ans = generateMatrix(4);
        int[][] expect = {
                {1, 2, 3, 4},
                {12, 13, 14, 5},
                {11, 16, 15, 6},
                {10, 9, 8, 7}
        };
        assertThat(ans, is(expect));
    }
}
