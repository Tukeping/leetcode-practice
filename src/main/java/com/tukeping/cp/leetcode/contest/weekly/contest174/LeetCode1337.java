package com.tukeping.cp.leetcode.contest.weekly.contest174;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/10
 **/
public class LeetCode1337 {

    class Datum {
        int[] row;
        int id;

        public Datum(int[] row, int id) {
            this.row = row;
            this.id = id;
        }
    }

    public int[] kWeakestRows2(int[][] mat, int k) {
        int n = mat.length;
        Datum[] ds = new Datum[n];
        for (int i = 0; i < n; i++) {
            ds[i] = new Datum(mat[i], i);
        }
        Arrays.sort(ds, new Comparator<Datum>() {
            @Override
            public int compare(Datum a, Datum b) {
                for (int i = 0; i < a.row.length; i++) {
                    if (a.row[i] != b.row[i]) return a.row[i] - b.row[i];
                }
                return a.id - b.id;
            }
        });
        int[] ret = new int[k];
        for (int i = 0; i < k; i++) {
            ret[i] = ds[i].id;
        }
        return ret;
    }

    public int[] kWeakestRows(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        boolean[] used = new boolean[m];
        int[] ans = new int[k];
        int cnt = 0;
        for (int col = 0; col < n; col++) {
            for (int row = 0; row < m; row++) {
                if (!used[row] && mat[row][col] == 0) {
                    ans[cnt++] = row;
                    used[row] = true;
                }
                if (cnt == k) {
                    return ans;
                }
            }
        }
        int row = 0;
        while (cnt < k) {
            if (used[row])
                row++;
            else {
                ans[cnt++] = row;
                row++;
            }
        }
        return ans;
    }

    /**
     * 输入：mat =
     * [[1,1,0,0,0],
     *  [1,1,1,1,0],
     *  [1,0,0,0,0],
     *  [1,1,0,0,0],
     *  [1,1,1,1,1]],
     * k = 3
     * 输出：[2,0,3]
     * 解释：
     * 每行中的军人数目：
     * 行 0 -> 2
     * 行 1 -> 4
     * 行 2 -> 1
     * 行 3 -> 2
     * 行 4 -> 5
     * 从最弱到最强对这些行排序后得到 [2,0,3,1,4]
     */
    @Test
    public void test1() {
        int[][] mat = {
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 1}
        };
        int[] ans = kWeakestRows(mat, 3);
        assertThat(ans, is(new int[]{2, 0, 3}));
    }

    /**
     * 输入：mat =
     * [[1,0,0,0],
     *  [1,1,1,1],
     *  [1,0,0,0],
     *  [1,0,0,0]],
     * k = 2
     * 输出：[0,2]
     * 解释：
     * 每行中的军人数目：
     * 行 0 -> 1
     * 行 1 -> 4
     * 行 2 -> 1
     * 行 3 -> 1
     * 从最弱到最强对这些行排序后得到 [0,2,3,1]
     */
    @Test
    public void test2() {
        int[][] mat = {
                {1, 0, 0, 0},
                {1, 1, 1, 1},
                {1, 0, 0, 0},
                {1, 0, 0, 0}
        };
        int[] ans = kWeakestRows(mat, 2);
        assertThat(ans, is(new int[]{0, 2}));
    }
}
