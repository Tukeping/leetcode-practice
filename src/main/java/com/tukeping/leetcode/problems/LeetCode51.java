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

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tukeping
 * @date 2020/1/21
 **/
public class LeetCode51 {

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
