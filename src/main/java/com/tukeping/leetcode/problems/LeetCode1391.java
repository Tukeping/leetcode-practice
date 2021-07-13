package com.tukeping.leetcode.problems;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author tukeping
 * @date 2020/3/24
 **/
public class LeetCode1391 {

    public boolean hasValidPath2(int[][] a) {
        int n = a.length, m = a[0].length;

        int[][][] ds = {
                {{0, -1}, {0, 1}},
                {{-1, 0}, {1, 0}},
                {{0, -1}, {1, 0}},
                {{0, 1}, {1, 0}},
                {{0, -1}, {-1, 0}},
                {{0, 1}, {-1, 0}}
        };

        boolean[][] ved = new boolean[n][m];
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0});
        ved[0][0] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];
            int num = a[r][c] - 1;
            for (int[] d : ds[num]) {
                int nr = r + d[0], nc = c + d[1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && !ved[nr][nc]) {
                    boolean rok = false;
                    for (int[] ld : ds[a[nr][nc] - 1]) {
                        if (nr + ld[0] == r && nc + ld[1] == c) {
                            rok = true;
                        }
                    }
                    if (rok) {
                        ved[nr][nc] = true;
                        q.add(new int[]{nr, nc});
                    }
                }
            }
        }
        return ved[n - 1][m - 1];
    }

    /*
     *      0
     *      ^
     *      |
     * 3 <-- --> 1
     *      |
     *      v
     *      2
     */

    private final static int MAX_N = 300 * 300 + 5;
    private final static int[] PATTERNS = {0, 0b1010, 0b0101, 0b1100, 0b0110, 0b1001, 0b0011};
    private final static int[][] DIRS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int n, m;
    private int[][] grid;
    private DisjointSet ds = new DisjointSet();

    private static class DisjointSet {
        int[] f = new int[MAX_N];

        DisjointSet() {
            for (int i = 0; i < MAX_N; ++i) f[i] = i;
        }

        public int find(int x) {
            return x == f[x] ? x : (f[x] = find(f[x]));
        }

        public void merge(int x, int y) {
            f[find(x)] = find(y);
        }
    }

    private int flatId(int x, int y) {
        return x * m + y;
    }

    private void handler(int x, int y) {
        int pattern = PATTERNS[grid[x][y]];
        for (int i = 0; i < 4; ++i) {
            if ((pattern & (1 << i)) != 0) {
                int sx = x + DIRS[i][0];
                int sy = y + DIRS[i][1];
                if (inGrid(sx, sy) && (PATTERNS[grid[sx][sy]] & (1 << ((i + 2) % 4))) != 0) {
                    ds.merge(flatId(x, y), flatId(sx, sy));
                }
            }
        }
    }

    private boolean inGrid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    public boolean hasValidPath(int[][] grid) {
        this.grid = grid;
        this.n = grid.length;
        this.m = grid[0].length;

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                handler(i, j);
            }
        }

        return ds.find(flatId(0, 0)) == ds.find(flatId(n - 1, m - 1));
    }

    /**
     * 输入：grid = [[2,4,3],[6,5,2]]
     * 输出：true
     * 解释：如图所示，你可以从 (0, 0) 开始，访问网格中的所有单元格并到达 (m - 1, n - 1) 。
     */
    @Test
    public void test1() {
        int[][] grid = {
                {2, 4, 3},
                {6, 5, 2}
        };
        assertTrue(hasValidPath(grid));
    }

    /**
     * 输入：grid = [[1,2,1],[1,2,1]]
     * 输出：false
     * 解释：如图所示，单元格 (0, 0) 上的街道没有与任何其他单元格上的街道相连，你只会停在 (0, 0) 处。
     */
    @Test
    public void test2() {
        int[][] grid = {
                {1, 2, 1},
                {1, 2, 1}
        };
        assertFalse(hasValidPath(grid));
    }

    /**
     * 输入：grid = [[1,1,2]]
     * 输出：false
     * 解释：你会停在 (0, 1)，而且无法到达 (0, 2) 。
     */
    @Test
    public void test3() {
        int[][] grid = {
                {1, 1, 2}
        };
        assertFalse(hasValidPath(grid));
    }

    /**
     * 输入：grid = [[1,1,1,1,1,1,3]]
     * 输出：true
     */
    @Test
    public void test4() {
        int[][] grid = {
                {1, 1, 1, 1, 1, 1, 3}
        };
        assertTrue(hasValidPath(grid));
    }

    /**
     * 输入：grid = [[2],[2],[2],[2],[2],[2],[6]]
     * 输出：true
     */
    @Test
    public void test5() {
        int[][] grid = {
                {2},
                {2},
                {2},
                {2},
                {2},
                {2},
                {6}
        };
        assertTrue(hasValidPath(grid));
    }
}
