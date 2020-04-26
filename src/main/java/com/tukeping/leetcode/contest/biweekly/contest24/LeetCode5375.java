package com.tukeping.leetcode.contest.biweekly.contest24;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/18
 **/
public class LeetCode5375 {

    public int numberOfArrays(String s, int k) {
        int MOD = 1000000007;
        char[] a = s.toCharArray();
        int n = a.length;
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 0; i < n; i++) {
            if (a[i] == '0') continue;
            System.out.println();
            long u = 0;
            for (int j = i; j < n; j++) {
                u = u * 10 + a[j] - 48;
                if (u > k) break;
                f[j + 1] += f[i];
                System.out.println("f[" + (j + 1) + "] = " + f[j + 1]);
                f[j + 1] %= MOD;
            }
        }
        return f[n];
    }

    @Test
    public void test1() {
        int n = numberOfArrays("1000", 10000);
        assertThat(n, is(1));
    }

    @Test
    public void test2() {
        int n = numberOfArrays("1000", 10);
        assertThat(n, is(0));
    }

    @Test
    public void test3() {
        int n = numberOfArrays("1317", 2000);
        assertThat(n, is(8));
    }

    @Test
    public void test4() {
        int n = numberOfArrays("2020", 30);
        assertThat(n, is(1));
    }

    @Test
    public void test5() {
        int n = numberOfArrays("1234567890", 90);
        assertThat(n, is(34));
    }
}
