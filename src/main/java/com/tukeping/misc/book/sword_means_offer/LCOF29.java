package com.tukeping.misc.book.sword_means_offer;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/27
 **/
public class LCOF29 {

    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length <= 0) return new int[0];
        int m = matrix.length, n = matrix[0].length;
        int total = m * n;
        int[][] d = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int di = 0, x = 0, y = 0, sx = 0, sy = 0;
        int[] ans = new int[total];
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < total; i++) {
            ans[i] = matrix[x][y];
            visited[x][y] = true;
            sx = x + d[di][0];
            sy = y + d[di][1];
            if (0 <= sx && sx < m && 0 <= sy && sy < n && !visited[sx][sy]) {
                x = sx;
                y = sy;
            } else {
                di = (di + 1) % 4;
                x += d[di][0];
                y += d[di][1];
            }
        }
        return ans;
    }

    /**
     * 输入:
     * [
     *  [ 1, 2, 3 ],
     *  [ 4, 5, 6 ],
     *  [ 7, 8, 9 ]
     * ]
     * 输出: [1,2,3,6,9,8,7,4,5]
     */
    @Test
    public void test1() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[] res = spiralOrder(matrix);
        assertThat(res, is(new int[]{1, 2, 3, 6, 9, 8, 7, 4, 5}));
    }

    /**
     * 输入:
     * [
     *   [1, 2, 3, 4],
     *   [5, 6, 7, 8],
     *   [9,10,11,12]
     * ]
     * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
     */
    @Test
    public void test2() {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        int[] res = spiralOrder(matrix);
        assertThat(res, is(new int[]{1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7}));
    }

    @Test
    public void test3() {
        int[][] matrix = {
                {3, 2}
        };
        int[] res = spiralOrder(matrix);
        assertThat(res, is(new int[]{3, 2}));
    }

    @Test
    public void test4() {
        int[][] matrix = {
                {3},
                {2}
        };
        int[] res = spiralOrder(matrix);
        assertThat(res, is(new int[]{3, 2}));
    }

    @Test
    public void test5() {
        int[][] matrix = {
                {6, 9, 7}
        };
        int[] res = spiralOrder(matrix);
        assertThat(res, is(new int[]{6, 9, 7}));
    }
}
