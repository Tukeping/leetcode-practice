package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=52 lang=java
 *
 * [52] N皇后 II
 *
 * https://leetcode-cn.com/problems/n-queens-ii/description/
 *
 * algorithms
 * Hard (76.49%)
 * Likes:    93
 * Dislikes: 0
 * Total Accepted:    14.3K
 * Total Submissions: 18.5K
 * Testcase Example:  '4'
 *
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 *
 *
 * 上图为 8 皇后问题的一种解法。
 *
 * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
 *
 * 示例:
 *
 * 输入: 4
 * 输出: 2
 * 解释: 4 皇后问题存在如下两个不同的解法。
 * [
 * [".Q..",  // 解法 1
 * "...Q",
 * "Q...",
 * "..Q."],
 *
 * ["..Q.",  // 解法 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 *
 *
 */

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/1/22
 **/
public class LeetCode52 {

    @Test
    public void test() {
        int n = totalNQueens(4);
        assertThat(n, is(2));

        n = totalNQueens(8);
        assertThat(n, is(92));
    }

    public int totalNQueens(int n) {
        AtomicInteger total = new AtomicInteger(0);
        int[] grid = new int[n];
        findQueues(0, grid, n, total);
        return total.get();
    }

    private void findQueues(int row, int[] grid, int n, AtomicInteger total) {
        if (row == n) {
            total.getAndIncrement();
            return;
        }
        for (int col = 0; col < n; col++) {
            if (check(row, col, grid)) {
                grid[row] = col;
                findQueues(row + 1, grid, n, total);
            }
        }
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
