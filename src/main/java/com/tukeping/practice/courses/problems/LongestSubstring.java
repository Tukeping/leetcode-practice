package com.tukeping.practice.courses.problems;

import com.tukeping.tools.DPHelper;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/26
 **/
public class LongestSubstring {

    public int findLongest(String A, int n, String B, int m) {
        int[][] f = new int[n + 1][m + 1];
        int max = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) f[i][j] = f[i - 1][j - 1] + 1;
                else f[i][j] = 0;
                max = Math.max(max, f[i][j]);
            }
        }
        DPHelper.print(f, A, B);
        return max;
    }

    @Test
    public void test1() {
        int n = findLongest("1AB2345CD", 9, "12345EF", 7);
        assertThat(n, is(4));
    }
}
