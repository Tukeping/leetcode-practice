package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=240 lang=java
 *
 * [240] 搜索二维矩阵 II
 *
 * https://leetcode-cn.com/problems/search-a-2d-matrix-ii/description/
 *
 * algorithms
 * Medium (39.40%)
 * Likes:    247
 * Dislikes: 0
 * Total Accepted:    46.9K
 * Total Submissions: 118.9K
 * Testcase Example:  '[[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]]\n' +
  '5'
 *
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
 *
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 *
 *
 * 示例:
 *
 * 现有矩阵 matrix 如下：
 *
 * [
 * ⁠ [1,   4,  7, 11, 15],
 * ⁠ [2,   5,  8, 12, 19],
 * ⁠ [3,   6,  9, 16, 22],
 * ⁠ [10, 13, 14, 17, 24],
 * ⁠ [18, 21, 23, 26, 30]
 * ]
 *
 *
 * 给定 target = 5，返回 true。
 *
 * 给定 target = 20，返回 false。
 *
 */

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * binary-search | divide-and-conquer
 *
 * amazon | apple | google
 *
 * @author tukeping
 * @date 2020/3/18
 **/
public class LeetCode240 {

    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0) return false;
        int n = matrix[0].length;
        int row = matrix.length - 1;
        int col = 0;
        while (row >= 0 && col <= n - 1) {
            if (matrix[row][col] > target) {
                row--;
            } else if (matrix[row][col] < target) {
                col++;
            } else {
                return true;
            }
        }
        return false;
    }

    private boolean found = false;

    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null) return false;
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;
        if (n == 0) return false;

        int row = findRow(matrix, 0, m - 1, target);
        if (found) return true;

        for (int i = 0; i <= row; i++) {
            if (findNum(matrix, i, 0, n - 1, target)) return true;
        }
        return false;
    }

    private boolean findNum(int[][] nums, int row, int start, int end, int target) {
        if (start > end) return false;
        int mid = (start + end) / 2;
        if (nums[row][mid] == target) return true;
        else if (nums[row][mid] > target) return findNum(nums, row, start, mid - 1, target);
        else return findNum(nums, row, mid + 1, end, target);
    }

    private int findRow(int[][] nums, int start, int end, int target) {
        if (start > end) return end;
        int mid = (start + end) / 2;
        if (nums[mid][0] == target) {
            found = true;
            return mid;
        } else if (nums[mid][0] > target) {
            return findRow(nums, start, mid - 1, target);
        } else {
            return findRow(nums, mid + 1, end, target);
        }
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
        assertTrue(searchMatrix(matrix, 5));
        assertFalse(searchMatrix(matrix, 20));
    }
}