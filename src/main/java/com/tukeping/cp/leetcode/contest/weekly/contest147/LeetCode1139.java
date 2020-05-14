package com.tukeping.cp.leetcode.contest.weekly.contest147;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/5/7
 **/
public class LeetCode1139 {

    public int largest1BorderedSquare(int[][] grid) {
        int n = grid.length, m = grid[0].length;

        int[][] sx = new int[n + 1][m + 1];
        int[][] sy = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                sx[i][j] = sx[i][j - 1] + grid[i - 1][j - 1];
                sy[i][j] = sy[i - 1][j] + grid[i - 1][j - 1];
            }
        }

        int maxLen = 0;
        for (int i = 1; i < sx.length; i++) {
            for (int l = 1; l < sx[i].length; l++) {
                for (int r = sx[i].length - 1; r >= l; r--) {
                    int len = r - l + 1;
                    // 只关注最大的正方形
                    if (len <= maxLen) {
                        break;
                    }
                    // 排除超出grid范围
                    if (i + r - l >= sx.length || i + r - l >= sy.length) {
                        continue;
                    }
                    // 四条边判断都是1，采用行列的前缀和 与 len值 是否一致即可判断是否行列上都是1
                    if (sx[i][r] - sx[i][l - 1] != len) {
                        continue;
                    }
                    if (sx[i + r - l][r] - sx[i + r - l][l - 1] != len) {
                        continue;
                    }
                    if (sy[i + r - l][l] - sy[i - 1][l] != len) {
                        continue;
                    }
                    if (sy[i + r - l][r] - sy[i - 1][r] != len) {
                        continue;
                    }
                    maxLen = len;
                }
            }
        }
        return maxLen * maxLen;
    }

    @Test
    public void test1() {
        int[][] grid = {
                {1, 1, 1, 1, 1, 1}, {1, 0, 1, 0, 1, 1}, {1, 1, 1, 0, 1, 1}, {1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 0}
        };
        int n = largest1BorderedSquare(grid);
        assertThat(n, is(25));
    }

    /**
     * 输入：grid = [[1,1,1],[1,0,1],[1,1,1]]
     * 输出：9
     */
    @Test
    public void test2() {
        int[][] grid = {
                {1, 1, 1}, {1, 0, 1}, {1, 1, 1}
        };
        int n = largest1BorderedSquare(grid);
        assertThat(n, is(9));
    }

    /**
     * 输入：grid = [[1,1,0,0]]
     * 输出：1
     */
    @Test
    public void test3() {
        int[][] grid = {
                {1, 1, 0, 0}
        };
        int n = largest1BorderedSquare(grid);
        assertThat(n, is(1));
    }

    @Test
    public void test4() {
        int[][] grid = {
                {0, 1}, {0, 1}
        };
        int n = largest1BorderedSquare(grid);
        assertThat(n, is(1));
    }

    @Test
    public void test5() {
        int[][] grid = {
                {1, 1, 1}, {1, 1, 0}, {1, 1, 1}, {0, 1, 1}, {1, 1, 1}
        };
        int n = largest1BorderedSquare(grid);
        assertThat(n, is(4));
    }

    @Test
    public void test6() {
        int[][] grid = {
                {1, 0, 1, 0, 1, 1}, {1, 1, 0, 1, 0, 1}, {0, 1, 1, 1, 1, 1}, {0, 0, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 0}, {1, 1, 1, 1, 1, 1}, {1, 0, 0, 1, 0, 0}, {0, 1, 1, 0, 1, 1}
        };
        int n = largest1BorderedSquare(grid);
        assertThat(n, is(9));
    }

    @Test
    public void test7() {
        int[][] grid = {
                {1, 1, 0, 1, 1, 1, 1, 0}, {0, 1, 1, 0, 0, 0, 1, 1}, {1, 0, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 0, 0, 1, 1, 1}, {1, 0, 1, 0, 1, 1, 1, 0}, {1, 1, 1, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 0, 1, 1, 1}, {0, 1, 1, 0, 1, 1, 0, 1}
        };
        int n = largest1BorderedSquare(grid);
        assertThat(n, is(16));
    }

    @Test
    public void test8() {
        int[][] grid = {
                {1, 1, 1, 1, 1, 1, 1, 0}, {1, 1, 1, 1, 1, 0, 1, 1}, {1, 1, 1, 0, 0, 1, 1, 1}, {1, 0, 1, 1, 1, 0, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 0, 1, 1}, {0, 1, 0, 0, 0, 0, 1, 1}, {1, 1, 1, 1, 0, 1, 1, 0}, {1, 1, 0, 1, 1, 1, 1, 1}
        };
        int n = largest1BorderedSquare(grid);
        assertThat(n, is(25));
    }

    @Test
    public void test9() {
        int[][] grid = {
                {1, 0, 1, 1, 0, 1, 1, 1, 0}, {1, 1, 1, 1, 1, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1}, {0, 0, 0, 0, 1, 0, 0, 0, 1}, {1, 1, 1, 1, 0, 1, 1, 0, 1}
        };
        int n = largest1BorderedSquare(grid);
        assertThat(n, is(9));
    }
}
