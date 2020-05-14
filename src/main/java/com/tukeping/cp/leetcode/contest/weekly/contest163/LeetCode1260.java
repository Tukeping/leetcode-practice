package com.tukeping.cp.leetcode.contest.weekly.contest163;

import com.tukeping.tools.ListHelper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/28
 **/
public class LeetCode1260 {

    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int n = grid.length, m = grid[0].length;
        List<List<Integer>> ans = new ArrayList<>();
        int[][] newGrid = new int[n][m];
        int x = 0, y = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                y = (j + k) % m;
                x = (i + (j + k) / m) % n;
                newGrid[x][y] = grid[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                row.add(newGrid[i][j]);
            }
            ans.add(row);
        }
        return ans;
    }

    /**
     * 输入：grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
     * 输出：[[9,1,2],[3,4,5],[6,7,8]]
     */
    @Test
    public void test1() {
        int[][] grid = {
                {1, 2, 3}, {4, 5, 6}, {7, 8, 9}
        };
        List<List<Integer>> ans = shiftGrid(grid, 1);
        int[][] actual = ListHelper.asTwoDimArray(ans);
        int[][] expect = {
                {9, 1, 2}, {3, 4, 5}, {6, 7, 8}
        };
        assertThat(actual, is(expect));
    }
}
