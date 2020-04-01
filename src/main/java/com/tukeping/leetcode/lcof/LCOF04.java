package com.tukeping.leetcode.lcof;

/*
 * 面试题04. 二维数组中的查找
 *
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * 示例:
 *
 * 现有矩阵 matrix 如下：
 *
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 *
 * 给定 target = 20，返回 false。
 *
 * 限制：
 *
 * 0 <= n <= 1000
 * 0 <= m <= 1000
 */

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author tukeping
 * @date 2020/3/26
 **/
public class LCOF04 {

    private boolean found = false;

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;

        int n = matrix.length, m = matrix[0].length;
        int row = bsearch(matrix, 0, 0, n - 1, target);
        if (found) return true;

        for (int col = 0; col < m && row >= 0; ) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                row--;
            } else { // matrix[row][col] < target => continue column++
                col++;
            }
        }
        return false;
    }

    private int bsearch(int[][] matrix, int column, int start, int end, int target) {
        if (start > end) return end;

        int mid = (start + end) >> 1;
        if (matrix[mid][column] == target) {
            found = true;
            return mid;
        } else if (matrix[mid][column] > target) {
            return bsearch(matrix, column, start, mid - 1, target);
        } else {
            return bsearch(matrix, column, mid + 1, end, target);
        }
    }

    public boolean findNumberIn2DArray2(int[][] matrix, int target) {
        if (matrix.length == 0) return false;

        int n = matrix.length, m = matrix[0].length;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == target) {
                    return true;
                } else if (matrix[i][j] > target) {
                    break;
                } // matrix[i][j] < target => continue
            }
        }
        return false;
    }

    /**
     * [
     *   [1,   4,  7, 11, 15],
     *   [2,   5,  8, 12, 19],
     *   [3,   6,  9, 16, 22],
     *   [10, 13, 14, 17, 24],
     *   [18, 21, 23, 26, 30]
     * ]
     *
     * 给定 target = 5，返回 true。
     *
     * 给定 target = 20，返回 false。
     */
    @Test
    public void test1() {
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        assertTrue(findNumberIn2DArray(matrix, 5));
        assertFalse(findNumberIn2DArray(matrix, 20));
    }

    @Test
    public void test2() {
        int[][] matrix = {{}};
        assertFalse(findNumberIn2DArray(matrix, 1));
    }
}
