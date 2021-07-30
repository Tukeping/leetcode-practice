package com.tukeping.course.problems;

import com.tukeping.tools.DPHelper;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/26
 **/
public class MinCost {

    public int findMinCost(String A, int n, String B, int m, int c0, int c1, int c2) {
        int[][] f = new int[n + 1][m + 1];
        for (int i = 1; i <= m; i++) {
            f[0][i] = i * c0;
        }
        for (int i = 1; i <= n; i++) {
            f[i][0] = i * c1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    f[i][j] = f[i - 1][j - 1];
                } else {
                    f[i][j] = f[i - 1][j - 1] + c2;
                }
                f[i][j] = Math.min(f[i][j], f[i][j - 1] + c0);
                f[i][j] = Math.min(f[i][j], f[i - 1][j] + c1);
            }
        }
        DPHelper.print(f, A, B);
        return f[n][m];
    }

    @Test
    public void test1() {
        int n = findMinCost("abc", 3, "adc", 3, 5, 3, 100);
        assertThat(n, is(8));
    }
}
