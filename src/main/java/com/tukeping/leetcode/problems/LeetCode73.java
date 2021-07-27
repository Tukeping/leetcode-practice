package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=73 lang=java
 *
 * [73] 矩阵置零
 *
 * https://leetcode-cn.com/problems/set-matrix-zeroes/description/
 *
 * algorithms
 * Medium (54.70%)
 * Likes:    179
 * Dislikes: 0
 * Total Accepted:    29K
 * Total Submissions: 52.6K
 * Testcase Example:  '[[1,1,1],[1,0,1],[1,1,1]]'
 *
 * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
 *
 * 示例 1:
 *
 * 输入:
 * [
 * [1,1,1],
 * [1,0,1],
 * [1,1,1]
 * ]
 * 输出:
 * [
 * [1,0,1],
 * [0,0,0],
 * [1,0,1]
 * ]
 *
 * 示例 2:
 *
 * 输入:
 * [
 * [0,1,2,0],
 * [3,4,5,2],
 * [1,3,1,5]
 * ]
 * 输出:
 * [
 * [0,0,0,0],
 * [0,4,5,0],
 * [0,3,1,0]
 * ]
 *
 * 进阶:
 * 一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
 * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 * 你能想出一个常数空间的解决方案吗？
 */

import com.tukeping.tools.ListHelper;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * array
 *
 * amazon | microsoft
 *
 * frequency 5
 *
 * @author tukeping
 * @date 2020/2/16
 **/
public class LeetCode73 {

    public void setZeroes(int[][] matrix) {
        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        for (int row : rows) {
            for (int i = 0; i < n; i++) {
                matrix[row][i] = 0;
            }
        }
        for (int col : cols) {
            for (int i = 0; i < m; i++) {
                matrix[i][col] = 0;
            }
        }
    }

    @Test
    public void test1() {
        int[][] matrix = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        setZeroes(matrix);
        int[][] expect = {
                {1, 0, 1},
                {0, 0, 0},
                {1, 0, 1}
        };
        ListHelper.assertThatTwoDim(matrix, expect);
    }

    @Test
    public void test2() {
        int[][] matrix = {
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}
        };
        setZeroes(matrix);
        int[][] expect = {
                {0, 0, 0, 0},
                {0, 4, 5, 0},
                {0, 3, 1, 0}
        };
        ListHelper.assertThatTwoDim(matrix, expect);
    }

    @Test
    public void test3() {
        int[][] matrix = {
                {-1},
                {2},
                {3}
        };
        setZeroes(matrix);
        int[][] expect = {
                {-1},
                {2},
                {3}
        };
        ListHelper.assertThatTwoDim(matrix, expect);
    }
}
