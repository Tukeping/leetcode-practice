package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=994 lang=java
 *
 * [994] 腐烂的橘子
 *
 * https://leetcode-cn.com/problems/rotting-oranges/description/
 *
 * algorithms
 * Easy (46.23%)
 * Likes:    107
 * Dislikes: 0
 * Total Accepted:    10.8K
 * Total Submissions: 22.3K
 * Testcase Example:  '[[2,1,1],[1,1,0],[0,1,1]]'
 *
 * 在给定的网格中，每个单元格可以有以下三个值之一：
 *
 *
 * 值 0 代表空单元格；
 * 值 1 代表新鲜橘子；
 * 值 2 代表腐烂的橘子。
 *
 *
 * 每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。
 *
 * 返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：[[2,1,1],[1,1,0],[0,1,1]]
 * 输出：4
 *
 *
 * 示例 2：
 *
 * 输入：[[2,1,1],[0,1,1],[1,0,1]]
 * 输出：-1
 * 解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个正向上。
 *
 *
 * 示例 3：
 *
 * 输入：[[0,2]]
 * 输出：0
 * 解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= grid.length <= 10
 * 1 <= grid[0].length <= 10
 * grid[i][j] 仅为 0、1 或 2
 *
 *
 */

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * hash-table
 *
 * Unknown
 *
 * @author tukeping
 * @date 2020/3/4
 **/
public class LeetCode994 {

    int[] dr = new int[]{-1, 0, 1, 0};
    int[] dc = new int[]{0, -1, 0, 1};

    public int orangesRottingBFS(int[][] grid) {
        int R = grid.length, C = grid[0].length;

        // queue : all starting cells with rotten oranges
        Queue<Integer> queue = new ArrayDeque<>();
        Map<Integer, Integer> depth = new HashMap<>();
        for (int r = 0; r < R; ++r)
            for (int c = 0; c < C; ++c)
                if (grid[r][c] == 2) {
                    int code = r * C + c;
                    queue.add(code);
                    depth.put(code, 0);
                }

        int ans = 0;
        while (!queue.isEmpty()) {
            int code = queue.remove();
            int r = code / C, c = code % C;
            for (int k = 0; k < 4; ++k) {
                int nr = r + dr[k];
                int nc = c + dc[k];
                if (0 <= nr && nr < R && 0 <= nc && nc < C && grid[nr][nc] == 1) {
                    grid[nr][nc] = 2;
                    int ncode = nr * C + nc;
                    queue.add(ncode);
                    depth.put(ncode, depth.get(code) + 1);
                    ans = depth.get(ncode);
                }
            }
        }

        for (int[] row : grid)
            for (int v : row)
                if (v == 1)
                    return -1;

        return ans;
    }

    public int orangesRotting(int[][] grid) { // 2, 1, 0 => 2:bad, 1:good, 0:empty
        // corner case, -1
        if (grid == null || grid.length == 0) return -1;

        int row = grid.length;
        int column = grid[0].length;

        // corner case, -1
        if (column == 0) return -1;

        // sp case
        if (row == 1 && column == 1) return grid[0][0] == 1 ? -1 : 0;

        /*
         * newGrid =>
         * 0 0 0 0 0
         * 0 2 1 1 0
         * 0 1 1 0 0
         * 0 0 1 1 0
         * 0 0 0 0 0
         * 懒得判断数组越界, 采取网格四周都是空地的预处理, 其实没多大必要
         */
        int[][] newGrid = new int[row + 2][column + 2];
        for (int i = 0; i < row; i++) {
            System.arraycopy(grid[i], 0, newGrid[i + 1], 1, column);
        }

        int boomCountMin = 0, curBoomNum = 2, nextBoomNum = 3;
        boolean boomed = true, hasGoodOrange = false;

        while (boomed) {
            // 每个回合初始化状态
            boomed = false;
            hasGoodOrange = false;

            for (int i = 1; i <= row; i++) {
                for (int j = 1; j <= column; j++) {
                    // 有一个颗好橘子 上下左右都是空的 那么必然-1, 算是剪枝, 但效果不怎么好, 比如多个橘子被空地包围
                    if (newGrid[i][j] == 1
                            && newGrid[i - 1][j] == 0
                            && newGrid[i + 1][j] == 0
                            && newGrid[i][j - 1] == 0
                            && newGrid[i][j + 1] == 0) {
                        return -1;
                    }
                    // 核心逻辑1 => 引爆'坏'橘子
                    if (newGrid[i][j] == curBoomNum) {
                        boolean b = boomBadOrange(newGrid, i, j, nextBoomNum);
                        if (b) boomed = true;
                    }
                    // 核心逻辑2 => 判断是否还存在'好'橘子
                    if (newGrid[i][j] == 1) {
                        hasGoodOrange = true;
                    }
                }
            }

            if (boomed) {
                boomCountMin++;
                curBoomNum = nextBoomNum;
                nextBoomNum += 2;
            }
        }

        return hasGoodOrange ? -1 /*还有好橘子*/ : boomCountMin /*没有好橘子了*/;
    }

    private boolean boomBadOrange(int[][] grid, int i, int j, int nextBoomNum) {
        boolean b1 = boomRow(grid, i, j, nextBoomNum);
        boolean b2 = boomColumn(grid, i, j, nextBoomNum);
        return b1 || b2;
    }

    private boolean boomRow(int[][] grid, int i, int j, int nextBoomNum) {
        boolean willBoom = grid[i][j - 1] == 1 || grid[i][j + 1] == 1;
        if (grid[i][j - 1] == 1) grid[i][j - 1] = nextBoomNum;
        if (grid[i][j + 1] == 1) grid[i][j + 1] = nextBoomNum;
        return willBoom;
    }

    private boolean boomColumn(int[][] grid, int i, int j, int nextBoomNum) {
        boolean willBoom = grid[i - 1][j] == 1 || grid[i + 1][j] == 1;
        if (grid[i - 1][j] == 1) grid[i - 1][j] = nextBoomNum;
        if (grid[i + 1][j] == 1) grid[i + 1][j] = nextBoomNum;
        return willBoom;
    }

    /**
     * 输入：[[2,1,1],[1,1,0],[0,1,1]]
     * 输出：4
     */
    @Test
    public void test1() {
        int[][] grid = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };
        int n = orangesRotting(grid);
        assertThat(n, is(4));
    }

    /**
     * 输入：[[2,1,1],[0,1,1],[1,0,1]]
     * 输出：-1
     * 解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个正向上。
     */
    @Test
    public void test2() {
        int[][] grid = {
                {2, 1, 1},
                {0, 1, 1},
                {1, 0, 1}
        };
        int n = orangesRotting(grid);
        assertThat(n, is(-1));
    }

    /**
     * 输入：[[0,2]]
     * 输出：0
     * 解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
     */
    @Test
    public void test3() {
        int[][] grid = {
                {0, 2}
        };
        int n = orangesRotting(grid);
        assertThat(n, is(0));
    }

    @Test
    public void test4() {
        int[][] grid = {
                {1},
                {1},
                {1},
                {1}
        };
        int n = orangesRotting(grid);
        assertThat(n, is(-1));
    }

    @Test
    public void test5() {
        int[][] grid = {
                {2},
                {2},
                {1},
                {0},
                {1},
                {1}
        };
        int n = orangesRotting(grid);
        assertThat(n, is(-1));
    }
}
