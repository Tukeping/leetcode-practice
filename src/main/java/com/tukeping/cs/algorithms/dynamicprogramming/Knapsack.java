package com.tukeping.cs.algorithms.dynamicprogramming;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * 背包问题
 *
 * @author tukeping
 * @date 2020/2/15
 **/
public class Knapsack {

    /**
     * 有 N 件物品和一个容量是 V 的背包。每件物品只能使用一次。
     * 第 i 件物品的体积是 vi，价值是 wi。
     * 求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
     * 输出最大价值。
     */
    public int knapsack01(int[] w /*物品价值*/, int[] v /*物品重量*/, int m /*背包容量*/) {
        int n = w.length; // 物品数量
        int[][] f = new int[n + 1][m + 1]; // f[i][j]: 只考虑前i个物品且最大承重不超过j的最大价值

        for (int i = 1; i <= n; i++) { // 第 i 个物品 是否需要 放入背包
            int cw = w[i - 1]; // 当前物品的重量，数组下标从0开始 需要i-1
            int cv = v[i - 1]; // 当前物品的价值
            for (int j = 1; j <= m; j++) { // 当前背包容量为 j 时
                f[i][j] = f[i - 1][j]; // 默认 不选择拿 第i件物品
                // 当背包容量(j)大于了物品重量后，可以选择拿第i件物品
                // 需要前一个选择情况i-1下的j-w[j]的总价值再加上当前物品的价值v[i]
                if (j >= cw) f[i][j] = Math.max(f[i][j], f[i - 1][j - cw] + cv);
            }
        }
        // 返回考虑了前n件物品的所有情况后，背包承重在m容量下的总价值
        return f[n][m];
    }

    @Test
    public void test1() {
        int n = knapsack01(new int[]{2, 3, 4, 5, 9}, new int[]{3, 4, 5, 8, 10}, 20);
        assertThat(n, is(26));
    }

    public int knapsack01_stateless(int[] w, int[] v, int m) {
        int n = w.length;
        int[] f = new int[m + 1];

        // 需要注意的地方：使用滚动数组压缩状态空间时需要从后往前遍历
        // 因为每次当前数组表示的是上一个行的状态，被覆盖的顺序必须是从后往前
        for (int i = 1; i <= n; i++)
            for (int j = m; j >= w[i - 1]; j--)
                f[j] = Math.max(f[j], f[j - w[i - 1]] + v[i - 1]);

        return f[m];
    }

    @Test
    public void test2() {
        int n = knapsack01_stateless(new int[]{2, 3, 4, 5, 9}, new int[]{3, 4, 5, 8, 10}, 20);
        assertThat(n, is(26));
    }

    /**
     * 有 N 种物品和一个容量是 V 的背包，每种物品都有无限件可用。
     * 第 i 种物品的体积是 wi，价值是 vi。
     * 求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
     * 输出最大价值。
     */
    public int knapsack01_complete(int[] w, int[] v, int m) {
        int n = w.length;
        int[][] f = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                f[i][j] = f[i - 1][j];
                if (j >= w[i - 1]) f[i][j] = Math.max(f[i][j], f[i][j - w[i - 1]] + v[i - 1]);
            }
        }
        return f[n][m];
    }

    @Test
    public void test3() {
        int n = knapsack01_complete(new int[]{2, 3, 4, 5, 9}, new int[]{3, 4, 5, 8, 10}, 20);
        assertThat(n, is(32));
    }

    public int knapsack01_complete_stateless(int[] w, int[] v, int m) {
        int n = w.length;
        int[] f = new int[m + 1];

        for (int i = 1; i <= n; i++)
            for (int j = w[i - 1]; j <= m; j++)
                f[j] = Math.max(f[j], f[j - w[i - 1]] + v[i - 1]);

        return f[m];
    }

    @Test
    public void test4() {
        int n = knapsack01_complete_stateless(new int[]{2, 3, 4, 5, 9}, new int[]{3, 4, 5, 8, 10}, 20);
        assertThat(n, is(32));
    }
}
