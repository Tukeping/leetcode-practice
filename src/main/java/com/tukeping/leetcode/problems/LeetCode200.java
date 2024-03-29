package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=200 lang=java
 *
 * [200] 岛屿数量
 *
 * https://leetcode-cn.com/problems/number-of-islands/description/
 *
 * algorithms
 * Medium (46.33%)
 * Likes:    363
 * Dislikes: 0
 * Total Accepted:    54.7K
 * Total Submissions: 116.5K
 * Testcase Example:  '[["1","1","1","1","0"],["1","1","0","1","0"],["1","1","0","0","0"],["0","0","0","0","0"]]'
 *
 * 给定一个由 '1'（陆地）和
 * '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
 *
 * 示例 1:
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 *
 * 输出: 1
 *
 * 示例 2:
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 *
 * 输出: 3
 */

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/2/20
 **/
public class LeetCode200 {

    int[] dir = new int[]{1, 0, -1, 0, 1};
    int m, n;

    public int numIslandsV2(char[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    bfs(grid, i, j);
                    ans++;
                }
            }
        }
        return ans;
    }

    private void bfs(char[][] grid, int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        grid[i][j] = '0';
        while (!q.isEmpty()) {
            int[] node = q.poll();
            int r = node[0], c = node[1];
            for (int k = 0; k < 4; k++) {
                int dx = r + dir[k], dy = c + dir[k + 1];
                if (isValid(dx, dy) && grid[dx][dy] == '1') {
                    q.offer(new int[]{dx, dy});
                    grid[dx][dy] = '0';
                }
            }
        }
    }

    private boolean isValid(int r, int c) {
        return r >= 0 && r < m && c >= 0 && c < n;
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int rowLen = grid.length;
        int columnLen = grid[0].length;

        int islandCount = 0;

        for (int r = 0; r < rowLen; r++) {
            for (int c = 0; c < columnLen; c++) {
                if (grid[r][c] == '1') {
                    islandCount++;
                    dfs(grid, r, c, rowLen, columnLen);
                }
            }
        }

        return islandCount;
    }

    private void dfs(char[][] grid, int r, int c, int rowLen, int columnLen) {
        if (r < 0 || c < 0 || r >= rowLen || c >= columnLen || grid[r][c] == '0') {
            return;
        }

        grid[r][c] = '0';

        dfs(grid, r - 1, c, rowLen, columnLen);
        dfs(grid, r + 1, c, rowLen, columnLen);
        dfs(grid, r, c - 1, rowLen, columnLen);
        dfs(grid, r, c + 1, rowLen, columnLen);
    }

    @Test
    public void test1() {
        char[][] island = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        int n = numIslands(island);
        assertThat(n, is(1));
    }

    @Test
    public void test2() {
        char[][] island = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        int n = numIslands(island);
        assertThat(n, is(3));
    }

    @Test
    public void test3() {
        char[][] island = {
                {'1'},
                {'1'}
        };
        int n = numIslands(island);
        assertThat(n, is(1));
    }
}
