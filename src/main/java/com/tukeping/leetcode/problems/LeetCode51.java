package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=51 lang=java
 *
 * [51] N皇后
 *
 * https://leetcode-cn.com/problems/n-queens/description/
 *
 * algorithms
 * Hard (67.05%)
 * Likes:    309
 * Dislikes: 0
 * Total Accepted:    23.2K
 * Total Submissions: 34.4K
 * Testcase Example:  '4'
 *
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 *
 *
 * 上图为 8 皇后问题的一种解法。
 *
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 *
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * 示例:
 *
 * 输入: 4
 * 输出: [
 * ⁠[".Q..",  // 解法 1
 * ⁠ "...Q",
 * ⁠ "Q...",
 * ⁠ "..Q."],
 *
 * ⁠["..Q.",  // 解法 2
 * ⁠ "Q...",
 * ⁠ "...Q",
 * ⁠ ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 *
 *
 */

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author tukeping
 * @date 2020/1/21
 **/
public class LeetCode51 {

    public List<Integer> spiralOrder(int[][] matrix) {
        // edge case
        List<Integer> order = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length, cols = matrix[0].length;
        int left = 0, right = cols - 1, top = 0, bottom = rows - 1;
        while (left <= right && top <= bottom) {
            // 行不动, 列向右移动
            for (int i = left; i <= right; i++) {
                order.add(matrix[top][i]);
            }
            // 列不动, 行向下移动
            for (int i = top + 1; i <= bottom; i++) {
                order.add(matrix[i][right]);
            }
            if (top < bottom) {
                // 行不动, 列向左移动
                for (int i = right - 1; i >= left; i--) {
                    order.add(matrix[bottom][i]);
                }
            }
            if (left < right) {
                // 列不动, 行向上移动
                for (int i = bottom - 1; i >= top + 1; i--) {
                    order.add(matrix[i][left]);
                }
            }
            // 矩形四个点坐标 缩小一圈
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }

    @Test
    public void test1() {
        int[][] input = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        List<Integer> expect = Arrays.asList(1, 2, 3, 6, 9, 8, 7, 4, 5);
        List<Integer> actual = spiralOrder(input);
        Assert.assertEquals(expect, actual);
    }

    @Test
    public void test2() {
        // [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
        int[][] input = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        // [1,2,3,4,8,12,11,10,9,5,6,7]
        List<Integer> expect = Arrays.asList(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7);
        List<Integer> actual = spiralOrder(input);
        Assert.assertEquals(expect, actual);
    }

    @Test
    public void test() {
        List<List<String>> result = solveNQueens(4);
        for (List<String> solution : result) {
            for (String line : solution) {
                System.out.println(line);
            }
            System.out.println();
        }
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        int[] grid = new int[n];
        findQueues(0, grid, n, result);
        return result;
    }

    private void findQueues(int row, int[] grid, int n, List<List<String>> result) {
        if (row == n) {
            result.add(queenPosition(grid));
            return;
        }
        for (int col = 0; col < n; col++) {
            if (check(row, col, grid)) {
                grid[row] = col;
                findQueues(row + 1, grid, n, result);
            }
        }
    }

    private List<String> queenPosition(int[] grid) {
        List<String> solution = new ArrayList<>();
        int n = grid.length;
        for (int col : grid) {
            solution.add(queenPositionLine(col, n));
        }
        return solution;
    }

    private String queenPositionLine(int col, int n) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (col == i) {
                line.append("Q");
            } else {
                line.append(".");
            }
        }
        return line.toString();
    }

    private boolean check(int row, int col, int[] grid) {
        for (int k = 0; k < row; k++) {
            if (grid[k] == col || grid[k] - col == row - k || grid[k] - col == k - row) {
                return false;
            }
        }
        return true;
    }
}
