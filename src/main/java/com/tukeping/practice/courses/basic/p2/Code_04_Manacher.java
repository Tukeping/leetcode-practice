package com.tukeping.practice.courses.basic.p2;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class Code_04_Manacher {

    private char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    public int maxLcpsLength(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = manacherString(str);
        int[] p = new int[chars.length];
        int C = -1, R = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < chars.length; i++) {
            p[i] = R > i ? Math.min(p[2 * C - i], R - i) : 1;
            while (i + p[i] < chars.length && i - p[i] > -1) {
                if (chars[i + p[i]] == chars[i - p[i]]) p[i]++;
                else break;
            }
            if (i + p[i] > R) {
                R = i + p[i];
                C = i;
            }
            max = Math.max(max, p[i]);
        }
        return max - 1;
    }

    @Test
    public void test1() {
        String str1 = "abc1234321ab";
        int n = maxLcpsLength(str1);
        System.out.println(n);
        assertThat(n, is(7));
    }
}
