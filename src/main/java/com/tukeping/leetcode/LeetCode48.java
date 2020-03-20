package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=48 lang=java
 *
 * [48] 旋转图像
 *
 * https://leetcode-cn.com/problems/rotate-image/description/
 *
 * algorithms
 * Medium (65.94%)
 * Likes:    386
 * Dislikes: 0
 * Total Accepted:    60.1K
 * Total Submissions: 89.3K
 * Testcase Example:  '[[1,2,3],[4,5,6],[7,8,9]]'
 *
 * 给定一个 n × n 的二维矩阵表示一个图像。
 *
 * 将图像顺时针旋转 90 度。
 *
 * 说明：
 *
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 *
 * 示例 1:
 *
 * 给定 matrix =
 * [
 * ⁠ [1,2,3],
 * ⁠ [4,5,6],
 * ⁠ [7,8,9]
 * ],
 *
 * 原地旋转输入矩阵，使其变为:
 * [
 * ⁠ [7,4,1],
 * ⁠ [8,5,2],
 * ⁠ [9,6,3]
 * ]
 *
 *
 * 示例 2:
 *
 * 给定 matrix =
 * [
 * ⁠ [ 5, 1, 9,11],
 * ⁠ [ 2, 4, 8,10],
 * ⁠ [13, 3, 6, 7],
 * ⁠ [15,14,12,16]
 * ],
 *
 * 原地旋转输入矩阵，使其变为:
 * [
 * ⁠ [15,13, 2, 5],
 * ⁠ [14, 3, 4, 1],
 * ⁠ [12, 6, 8, 9],
 * ⁠ [16, 7,10,11]
 * ]
 *
 *
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * array
 *
 * amazon | apple | microsoft
 *
 * @author tukeping
 * @date 2020/3/16
 **/
public class LeetCode48 {

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int cur = matrix[0][0], temp;
        int start = 0, end = n - 1, i = 0, j = 0;
        int round = end - start, cycle = n / 2, direction = 1;
        while (cycle > 0) {
            if (direction % 2 == 1) {
                j = (direction == 1) ? j + 1 : j - 1;
            } else {
                i = (direction == 2) ? i + 1 : i - 1;
            }
            temp = matrix[i][j];
            matrix[i][j] = cur;
            cur = temp;
            if (j == end && i == start
                    || j == end && i == end
                    || j == start && i == end) {
                direction = (direction + 1) % 4;
            } else if (j == i) {
                round--;
                if (round == 0) {
                    cycle--;
                    i++;
                    j++;
                    start++;
                    end--;
                    round = end - start;
                }
                direction = (direction + 1) % 4;
                cur = matrix[i][j];
            }
        }
    }

    /**
     * 给定 matrix =
     * [
     *   [1,2,3],
     *   [4,5,6],
     *   [7,8,9]
     * ],
     *
     * 原地旋转输入矩阵，使其变为:
     * [
     *   [7,4,1],
     *   [8,5,2],
     *   [9,6,3]
     * ]
     */
    @Test
    public void test1() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] expect = {
                {7, 4, 1},
                {8, 5, 2},
                {9, 6, 3}
        };
        rotate(matrix);
        assertThat(matrix, is(expect));
    }

    /**
     * 给定 matrix =
     * [
     *   [ 5, 1, 9,11],
     *   [ 2, 4, 8,10],
     *   [13, 3, 6, 7],
     *   [15,14,12,16]
     * ],
     *
     * 原地旋转输入矩阵，使其变为:
     * [
     *   [15,13, 2, 5],
     *   [14, 3, 4, 1],
     *   [12, 6, 8, 9],
     *   [16, 7,10,11]
     * ]
     */
    @Test
    public void test2() {
        int[][] matrix = {
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        };
        int[][] expect = {
                {15, 13, 2, 5},
                {14, 3, 4, 1},
                {12, 6, 8, 9},
                {16, 7, 10, 11}
        };
        rotate(matrix);
        assertThat(matrix, is(expect));
    }

    @Test
    public void test3() {
        int[][] matrix = {
                {1, 2},
                {3, 4}
        };
        int[][] expect = {
                {3, 1},
                {4, 2}
        };
        rotate(matrix);
        assertThat(matrix, is(expect));
    }
}
