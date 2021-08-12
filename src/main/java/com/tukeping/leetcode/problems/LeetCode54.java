package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=54 lang=java
 *
 * [54] 螺旋矩阵
 *
 * https://leetcode-cn.com/problems/spiral-matrix/description/
 *
 * algorithms
 * Medium (39.14%)
 * Likes:    322
 * Dislikes: 0
 * Total Accepted:    47.9K
 * Total Submissions: 122.3K
 * Testcase Example:  '[[1,2,3],[4,5,6],[7,8,9]]'
 *
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 *
 * 示例 1:
 *
 * 输入:
 * [
 * ⁠[ 1, 2, 3 ],
 * ⁠[ 4, 5, 6 ],
 * ⁠[ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 *
 *
 * 示例 2:
 *
 * 输入:
 * [
 * ⁠ [1, 2, 3, 4],
 * ⁠ [5, 6, 7, 8],
 * ⁠ [9,10,11,12]
 * ]
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 *
 *
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * array
 *
 * google | microsoft | uber
 *
 * @author tukeping
 * @date 2020/3/24
 **/
public class LeetCode54 {

    int m, n;
    int rowStart, rowEnd;
    int colStart, colEnd;

    public List<Integer> spiralOrder(int[][] matrix) {
        this.m = matrix.length;
        this.n = matrix[0].length;
        this.rowStart = 0;
        this.rowEnd = m - 1;
        this.colStart = 0;
        this.colEnd = n - 1;

        List<Integer> ret = new ArrayList<>();
        int[][] dirs = new int[][]{
                {0, 1}, {1, 0}, {0, -1}, {-1, 0}
        };
        int[] dir;

        while (rowStart <= rowEnd && colStart <= colEnd) {
            dir = dirs[0];
            for (int i = rowStart, j = colStart; j <= colEnd; ) {
                ret.add(matrix[i][j]);
                i += dir[0];
                j += dir[1];
            }
            dir = dirs[1];
            for (int i = rowStart + 1, j = colEnd; i <= rowEnd; ) {
                ret.add(matrix[i][j]);
                i += dir[0];
                j += dir[1];
            }
            if (rowStart != rowEnd) {
                dir = dirs[2];
                for (int i = rowEnd, j = colEnd - 1; j >= colStart; ) {
                    ret.add(matrix[i][j]);
                    i += dir[0];
                    j += dir[1];
                }
            }
            if (colStart != colEnd) {
                dir = dirs[3];
                for (int i = rowEnd - 1, j = colStart; i >= rowStart + 1; ) {
                    ret.add(matrix[i][j]);
                    i += dir[0];
                    j += dir[1];
                }
            }
            rowStart++;
            rowEnd--;
            colStart++;
            colEnd--;
        }
        return ret;
    }

    public List<Integer> spiralOrderV2(int[][] matrix) {
        if (matrix.length == 0) return Collections.emptyList();

        int m = matrix.length, n = matrix[0].length;
        int[][] offset = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int x = 0, y = 0, d = 0;

        boolean[][] visited = new boolean[m][n];
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < m * n; i++) {
            res.add(matrix[x][y]);
            visited[x][y] = true;

            int offsetX = x + offset[d][0];
            int offsetY = y + offset[d][1];

            // 0 <= offsetX < m && 0 <= offsetY < n && !visited
            if (0 <= offsetX && offsetX < m
                    && 0 <= offsetY && offsetY < n
                    && !visited[offsetX][offsetY]) {
                x = offsetX;
                y = offsetY;
            } else {
                // adjust direction and (x, y)
                d = (d + 1) % 4;
                x += offset[d][0];
                y += offset[d][1];
            }
        }
        return res;
    }

    public List<Integer> spiralOrderV3(int[][] matrix) {
        if (matrix.length == 0) return Collections.emptyList();

        List<Integer> ans = new ArrayList<>();
        int rowStart = 0, rowEnd = matrix.length - 1;
        int colStart = 0, colEnd = matrix[0].length - 1;
        while (rowStart <= rowEnd && colStart <= colEnd) {
            for (int col = colStart; col <= colEnd; col++)
                ans.add(matrix[rowStart][col]);
            for (int row = rowStart + 1; row <= rowEnd; row++)
                ans.add(matrix[row][colEnd]);
            if (rowStart < rowEnd && colStart < colEnd) {
                for (int col = colEnd - 1; col > colStart; col--)
                    ans.add(matrix[rowEnd][col]);
                for (int row = rowEnd; row > rowStart; row--)
                    ans.add(matrix[row][colStart]);
            }
            rowStart++;
            rowEnd--;
            colStart++;
            colEnd--;
        }
        return ans;
    }

    @Test
    public void test1() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        List<Integer> res = spiralOrder(matrix);
        assertThat(res, is(Arrays.asList(1, 2, 3, 6, 9, 8, 7, 4, 5)));
    }

    @Test
    public void test2() {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        List<Integer> res = spiralOrder(matrix);
        assertThat(res, is(Arrays.asList(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7)));
    }

    @Test
    public void test3() {
        int[][] matrix = {
                {3, 2}
        };
        List<Integer> res = spiralOrder(matrix);
        assertThat(res, is(Arrays.asList(3, 2)));
    }

    @Test
    public void test4() {
        int[][] matrix = {{3}, {2}};
        List<Integer> res = spiralOrder(matrix);
        assertThat(res, is(Arrays.asList(3, 2)));
    }

    @Test
    public void test5() {
        int[][] matrix = {
                {6, 9, 7}
        };
        List<Integer> res = spiralOrder(matrix);
        assertThat(res, is(Arrays.asList(6, 9, 7)));
    }
}
