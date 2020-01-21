package com.tukeping.cs.algorithms.backtracking;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * N皇后
 *
 * @author tukeping
 * @date 2020/1/21
 **/
public class NQueuePuzzle {

    public int backtracking(int n) {
        int[] grid = new int[n];
        AtomicInteger count = new AtomicInteger(0);
        findQueue(0, grid, n, count);
        return count.get();
    }

    private void findQueue(int row, int[] grid, int n, AtomicInteger count) {
        if (row == n) {
            count.getAndIncrement();
        }

        for (int col = 0; col < n; col++) {
            if (check(row, col, grid)) {
                grid[row] = col;
                findQueue(row + 1, grid, n, count);
            }
        }
    }

    private boolean check(int row, int col, int[] grid) {
        for (int k = 0; k < row; k++) {
            // 重点判断斜线!!! 由于斜线判断原理是"镜像" 所以 行等差等于列等差 row - row' = |col - col'|
            if (grid[k] == col || grid[k] - col == k - row || grid[k] - col == row - k) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        int[] answer = new int[]{1, 0, 0, 2, 10, 4, 40, 92, 352, 724, 2680, 14200, 73712, 365596};

        for (int n = 1; n <= 10; n++) {
            int count = backtracking(n);
            System.out.println(String.format("%dx%d棋盘, %d个皇后问题有%d个解。", n, n, n, count));
            assertThat(count, is(answer[n - 1]));
        }
    }
}
