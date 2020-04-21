package com.tukeping.cs.algorithms.stringmatching;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/21
 **/
public class Manacher {

    private char[] manacherString(String str) {
        int n = str.length();
        char[] res = new char[2 * n + 1];
        int idx = 0, len = res.length;
        for (int i = 0; i < len; i++) {
            res[i] = (i & 1) == 0 ? '#' : str.charAt(idx++);
        }
        return res;
    }

    public int maxPalindromeLength(String str) {
        if (str.isEmpty()) return 0;

        char[] p = manacherString(str);
        int len = p.length;

        int[] radius = new int[len]; /*回文半径数组*/
        int C = -1 /*回文中心*/, R = -1 /*回文右边界*/, max = Integer.MIN_VALUE;
        for (int i = 0; i <= len; i++) {
            radius[i] = R > i ? Math.min(2 * C - i, R - i) : 1;
            while (i + radius[i] < len && i - radius[i] >= 0) {
                if (p[i + radius[i]] == p[i - radius[i]]) radius[i]++;
                else break;
            }
            if (i + p[i] > R) {
                R = i + p[i];
                C = i;
            }
            max = Math.max(radius[i], max);
        }
        return max - 1;
    }

    @Test
    public void test1() {
        int n = maxPalindromeLength("babad");
        assertThat(n, is(3));
    }

    @Test
    public void test2() {
        int n = maxPalindromeLength("cbbd");
        assertThat(n, is(2));
    }
}
