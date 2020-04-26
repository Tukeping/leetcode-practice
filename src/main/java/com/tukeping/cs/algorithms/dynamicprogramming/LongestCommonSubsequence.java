package com.tukeping.cs.algorithms.dynamicprogramming;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * LCS 最长公共子序列问题 经典问题
 *
 * @author tukeping
 * @date 2020/4/22
 **/
public class LongestCommonSubsequence {

    public int longestCommonSubsequence(String A, String B) {
        int n = A.length();
        int m = B.length();

        // 所有 A[1-i] 与 B[1-j] 的公共子序列的最大值
        int[][] f = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                if (A.charAt(i - 1) == B.charAt(j - 1))
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - 1] + 1);
            }
        }
        return f[n][m];
    }

    @Test
    public void test1() {
        int n = longestCommonSubsequence("acbd", "abedc");
        assertThat(n, is(3));
    }
}
