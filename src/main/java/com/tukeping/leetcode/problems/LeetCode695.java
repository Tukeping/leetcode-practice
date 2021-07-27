package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=695 lang=java
 *
 * [695] 岛屿的最大面积
 *
 * https://leetcode-cn.com/problems/max-area-of-island/description/
 *
 * algorithms
 * Medium (58.76%)
 * Likes:    168
 * Dislikes: 0
 * Total Accepted:    16.6K
 * Total Submissions: 28K
 * Testcase Example:  '[[1,1,0,0,0],[1,1,0,0,0],[0,0,0,1,1],[0,0,0,1,1]]'
 *
 * 给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地)
 * 构成的组合。你可以假设二维矩阵的四个边缘都被水包围着。
 *
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)
 *
 * 示例 1:
 *
 *
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 * ⁠[0,0,0,0,0,0,0,1,1,1,0,0,0],
 * ⁠[0,1,1,0,1,0,0,0,0,0,0,0,0],
 * ⁠[0,1,0,0,1,1,0,0,1,0,1,0,0],
 * ⁠[0,1,0,0,1,1,0,0,1,1,1,0,0],
 * ⁠[0,0,0,0,0,0,0,0,0,0,1,0,0],
 * ⁠[0,0,0,0,0,0,0,1,1,1,0,0,0],
 * ⁠[0,0,0,0,0,0,0,1,1,0,0,0,0]]
 *
 *
 * 对于上面这个给定矩阵应返回 6。注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的‘1’。
 *
 * 示例 2:
 *
 *
 * [[0,0,0,0,0,0,0,0]]
 *
 * 对于上面这个给定的矩阵, 返回 0。
 *
 * 注意: 给定的矩阵grid 的长度和宽度都不超过 50。
 *
 */

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * array | depth-first-search
 *
 * Unknown
 *
 * @author tukeping
 * @date 2020/2/12
 **/
public class LeetCode695 {

    int[] d = new int[]{1, 0, -1, 0, 1};
    int m = 0, n = 0;

    public int maxAreaOfIslandV2(int[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;

        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    max = Math.max(max, bfs(grid, i, j));
                }
            }
        }
        return max;
    }

    private int bfs(int[][] grid, int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x, y});
        int ans = 0;
        while (!q.isEmpty()) {
            int[] node = q.poll();
            int r = node[0];
            int c = node[1];
            if (grid[r][c] == 0) continue;
            grid[r][c] = 0;
            ans++;
            for (int i = 0; i < 4; i++) {
                int sr = r + d[i];
                int sc = c + d[i + 1];
                if (sr >= 0 && sr < m && sc >= 0 && sc < n && grid[sr][sc] == 1) {
                    q.add(new int[]{sr, sc});
                }
            }
        }
        return ans;
    }

    public int maxAreaOfIsland(int[][] grid) {
        int nr = grid.length;
        int cr = grid[0].length;

        AtomicInteger areaN = new AtomicInteger(0);
        int maxAreaN = 0;
        for (int r = 0; r < nr; r++) {
            for (int c = 0; c < cr; c++) {
                if (grid[r][c] == 1) {
                    areaN.set(0);
                    dfs(grid, r, c, nr, cr, areaN);
                    maxAreaN = Math.max(maxAreaN, areaN.get());
                }
            }
        }

        return maxAreaN;
    }

    private void dfs(int[][] grid, int r, int c, int nr, int cr, AtomicInteger N) {
        if (r < 0 || c < 0 || r >= nr || c >= cr || grid[r][c] == 0) {
            return;
        }

        grid[r][c] = 0;
        N.getAndIncrement();

        dfs(grid, r - 1, c, nr, cr, N);
        dfs(grid, r + 1, c, nr, cr, N);
        dfs(grid, r, c - 1, nr, cr, N);
        dfs(grid, r, c + 1, nr, cr, N);
    }

    /**
     * 示例 1:
     *
     * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
     * ⁠[0,0,0,0,0,0,0,1,1,1,0,0,0],
     * ⁠[0,1,1,0,1,0,0,0,0,0,0,0,0],
     * ⁠[0,1,0,0,1,1,0,0,1,0,1,0,0],
     * ⁠[0,1,0,0,1,1,0,0,1,1,1,0,0],
     * ⁠[0,0,0,0,0,0,0,0,0,0,1,0,0],
     * ⁠[0,0,0,0,0,0,0,1,1,1,0,0,0],
     * ⁠[0,0,0,0,0,0,0,1,1,0,0,0,0]]
     *
     * 对于上面这个给定矩阵应返回 6。注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的‘1’。
     */
    @Test
    public void test1() {
        int[][] grid = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };
        int n = maxAreaOfIsland(grid);
        assertThat(n, is(6));
    }

    /**
     * 示例 2:
     *
     * [[0,0,0,0,0,0,0,0]]
     *
     * 对于上面这个给定的矩阵, 返回 0。
     */
    @Test
    public void test2() {
        int[][] grid = {
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        int n = maxAreaOfIsland(grid);
        assertThat(n, is(0));
    }
}
