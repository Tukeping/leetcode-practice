package com.tukeping.leetcode.problems;

import com.tukeping.tools.ListHelper;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * @author tukeping
 * @date 2021/7/27
 **/
public class LeetCode542 {

    public int[][] updateMatrixV3(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE - 1);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    dp[i][j] = 0;
                } else {
                    if (j > 0) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                    }
                    if (i > 0) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                    }
                }
            }
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (mat[i][j] != 0) {
                    if (j < n - 1) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][j + 1] + 1);
                    }
                    if (i < m - 1) {
                        dp[i][j] = Math.min(dp[i][j], dp[i + 1][j] + 1);
                    }
                }
            }
        }
        return dp;
    }

    int m = 0, n = 0;

    public int[][] updateMatrix(int[][] mat) {
        this.m = mat.length;
        this.n = mat[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    int dist = bfs(mat, i, j);
                    mat[i][j] = dist;
                }
            }
        }
        return mat;
    }

    int[] d = new int[]{1, 0, -1, 0, 1};

    private int bfs(int[][] mat, int r, int c) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{r, c, 0});
        boolean[][] visited = new boolean[m][n];
        while (!q.isEmpty()) {
            int[] node = q.poll();
            int x = node[0];
            int y = node[1];
            int deep = node[2];
            if (mat[x][y] == 0) return deep;
            if (visited[x][y]) continue;
            visited[x][y] = true;
            for (int i = 0; i < 4; i++) {
                int sx = x + d[i];
                int sy = y + d[i + 1];
                if (sx >= 0 && sx < m && sy >= 0 && sy < n) {
                    q.add(new int[]{sx, sy, deep + 1});
                }
            }
        }
        return 0;
    }

    @Test
    public void test() {
        int[][] mat = new int[][]{
                {0, 0, 0}, {0, 1, 0}, {0, 0, 0}
        };
        int[][] actual = updateMatrix(mat);
        int[][] expect = new int[][]{
                {0, 0, 0}, {0, 1, 0}, {0, 0, 0}
        };
        ListHelper.assertThatTwoDim(actual, expect);
    }

    @Test
    public void test2() {
        int[][] mat = new int[][]{
                {0, 0, 0}, {0, 1, 0}, {1, 1, 1}
        };
        int[][] actual = updateMatrix(mat);
        int[][] expect = new int[][]{
                {0, 0, 0}, {0, 1, 0}, {1, 2, 1}
        };
        ListHelper.assertThatTwoDim(actual, expect);
    }

    @Test
    public void test3() {
        int[][] mat = new int[][]{{1, 0, 1, 1, 0, 0, 1, 0, 0, 1}, {0, 1, 1, 0, 1, 0, 1, 0, 1, 1}, {0, 0, 1, 0, 1, 0, 0, 1, 0, 0}, {1, 0, 1, 0, 1, 1, 1, 1, 1, 1}, {0, 1, 0, 1, 1, 0, 0, 0, 0, 1}, {0, 0, 1, 0, 1, 1, 1, 0, 1, 0}, {0, 1, 0, 1, 0, 1, 0, 0, 1, 1}, {1, 0, 0, 0, 1, 1, 1, 1, 0, 1}, {1, 1, 1, 1, 1, 1, 1, 0, 1, 0}, {1, 1, 1, 1, 0, 1, 0, 0, 1, 1}};
        int[][] actual = updateMatrix(mat);
        int[][] expect = new int[][]{{1, 0, 1, 1, 0, 0, 1, 0, 0, 1}, {0, 1, 1, 0, 1, 0, 1, 0, 1, 1}, {0, 0, 1, 0, 1, 0, 0, 1, 0, 0}, {1, 0, 1, 0, 1, 1, 1, 1, 1, 1}, {0, 1, 0, 1, 1, 0, 0, 0, 0, 1}, {0, 0, 1, 0, 1, 1, 1, 0, 1, 0}, {0, 1, 0, 1, 0, 1, 0, 0, 1, 1}, {1, 0, 0, 0, 1, 2, 1, 1, 0, 1}, {2, 1, 1, 1, 1, 2, 1, 0, 1, 0}, {3, 2, 2, 1, 0, 1, 0, 0, 1, 1}};
        ListHelper.assertThatTwoDim(actual, expect);
    }
}
