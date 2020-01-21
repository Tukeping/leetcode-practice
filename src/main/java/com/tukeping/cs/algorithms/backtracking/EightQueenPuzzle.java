package com.tukeping.cs.algorithms.backtracking;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * 八皇后
 *
 * @author tukeping
 * @date 2020/1/21
 **/
public class EightQueenPuzzle {

    public int backtracking() {
        int n = 8;
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
            if (grid[k] == col || grid[k] - col == k - row || grid[k] - col == row - k) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        int count = backtracking();
        System.out.println(count);
        assertThat(count, is(92));
    }
}
