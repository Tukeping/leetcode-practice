package com.tukeping.cs.algorithms.dynamicprogramming;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * 合并石子
 *
 * 区间DP
 *
 * @author tukeping
 * @date 2020/4/22
 **/
public class MergeStones {

    public int mergeStones(int[] stones) {
        int n = stones.length;

        // 求前缀和
        int[] s = new int[n];
        s[0] = stones[0];
        for (int i = 1; i < n; i++) {
            s[i] = stones[i];
            s[i] += s[i - 1];
        }

        // f[i][j]表示: 将所有[i,j]区间内合并成一堆的最小代价
        int[][] f = new int[n][n];
        for (int gap = 1; gap < n; gap++) {
            for (int i = 0; i < n - gap; i++) {
                int j = i + gap;
                f[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int sum = s[j] - (i == 0 ? 0 : s[i - 1]);
                    f[i][j] = Math.min(f[i][j], f[i][k] + f[k + 1][j] + sum);
                }
            }
        }
        return f[0][n - 1];
    }

    @Test
    public void test1() {
        int n = mergeStones(new int[]{1, 3, 5, 2});
        assertThat(n, is(22));
    }
}
