package com.tukeping.leetcode;

import com.tukeping.tools.DPHelper;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/26
 **/
public class LeetCode583 {

    public int minDistance(String A, String B) {
        int n = A.length(), m = B.length();
        int[][] f = new int[n + 1][m + 1];
        for (int i = 0; i <= m; i++) f[0][i] = i;
        for (int i = 0; i <= n; i++) f[i][0] = i;
        DPHelper.print(f, A, B);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    f[i][j] = f[i - 1][j - 1];
                } else {
                    f[i][j] = Math.min(f[i - 1][j], f[i][j - 1]) + 1;
                }
            }
        }
        DPHelper.print(f, A, B);
        return f[n][m];
    }

    /**
     * 输入: "sea", "eat"
     * 输出: 2
     * 解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
     */
    @Test
    public void test1() {
        int n = minDistance("sea", "eat");
        assertThat(n, is(2));
    }
}
