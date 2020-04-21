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

        char[] chars = manacherString(str);
        int len = chars.length;

        int[] p = new int[len]; /*回文半径数组*/
        int C = -1 /*回文中心*/, R = -1 /*回文右边界*/;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < len; i++) {
            p[i] = R > i ? Math.min(p[2 * C - i], R - i) : 1;
            while (i + p[i] < len && i - p[i] >= 0) {
                if (chars[i + p[i]] == chars[i - p[i]]) p[i]++;
                else break;
            }
            if (i + p[i] > R) {
                R = i + p[i];
                C = i;
            }
            max = Math.max(p[i], max);
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
