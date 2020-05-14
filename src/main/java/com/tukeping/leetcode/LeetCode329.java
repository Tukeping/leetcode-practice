package com.tukeping.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/5/12
 **/
public class LeetCode329 {
    class Node {
        int x;
        int y;
        int val;

        public Node(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }

    public int longestIncreasingPath(int[][] g) {
        if (g.length == 0) return 0;

        int n = g.length, m = g[0].length;

        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                nodes.add(new Node(i, j, g[i][j]));
            }
        }

        nodes.sort((o1, o2) -> o1.val - o2.val);

        int[][] f = new int[n][m];
        for (int i = 0; i < n; i++) Arrays.fill(f[i], 1);

        int ans = 0;
        int[] dirs = {0, 1, 0, -1, 0};
        for (Node node : nodes) {
            int x = node.x;
            int y = node.y;
            int val = node.val;

            for (int i = 0; i < 4; i++) {
                int sx = x + dirs[i], sy = y + dirs[i + 1];
                if (0 <= sx && sx < n && 0 <= sy && sy < m && g[sx][sy] < val) {
                    f[x][y] = Math.max(f[x][y], f[sx][sy] + 1);
                }
            }
            ans = Math.max(ans, f[x][y]);
        }
        return ans;
    }

    @Test
    public void test1() {
        int[][] g = {
                {9, 9, 4},
                {6, 6, 8},
                {2, 1, 1}
        };
        int n = longestIncreasingPath(g);
        assertThat(n, is(4));
    }

    @Test
    public void test2() {
        int[][] g = {
                {7, 8, 9},
                {9, 7, 6},
                {7, 2, 3}
        };
        int n = longestIncreasingPath(g);
        assertThat(n, is(6));
    }

    @Test
    public void test3() {
        int[][] g = {
                {3, 4, 5},
                {3, 2, 6},
                {2, 2, 1}
        };
        int n = longestIncreasingPath(g);
        assertThat(n, is(4));
    }

    @Test
    public void test4() {
        int[][] g = {
                {7, 6, 1, 1},
                {2, 7, 6, 0},
                {1, 3, 5, 1},
                {6, 6, 3, 2}
        };
        int n = longestIncreasingPath(g);
        assertThat(n, is(7));
    }

    @Test
    public void test5() {
        int[][] g = {{19, 2, 8, 6, 4, 14, 1, 0, 17}, {0, 1, 9, 10, 11, 4, 12, 14, 5}, {14, 12, 16, 0, 15, 8, 5, 2, 8}, {5, 4, 1, 17, 9, 18, 8, 5, 2}, {9, 5, 4, 8, 16, 7, 11, 5, 0}, {5, 7, 14, 18, 10, 0, 14, 14, 0}, {9, 14, 4, 13, 18, 16, 9, 12, 10}, {18, 13, 9, 18, 11, 4, 12, 10, 10}, {7, 14, 16, 19, 10, 19, 11, 6, 4}, {16, 2, 3, 7, 15, 9, 7, 1, 1}, {1, 6, 16, 15, 18, 6, 6, 1, 14}, {9, 5, 2, 9, 8, 3, 2, 3, 10}, {2, 3, 16, 8, 7, 7, 0, 18, 16}, {11, 0, 16, 8, 13, 13, 11, 3, 8}, {17, 11, 0, 12, 11, 15, 12, 17, 0}};
        int n = longestIncreasingPath(g);
        assertThat(n, is(8));
    }
}
