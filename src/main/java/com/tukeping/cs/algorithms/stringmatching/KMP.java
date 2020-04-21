package com.tukeping.cs.algorithms.stringmatching;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/21
 **/
public class KMP {

    public int indexOf(String text, String pattern) {
        char[] s = text.toCharArray();
        char[] p = pattern.toCharArray();
        int n = s.length, m = p.length;

        int[] next = getNext(p);

        int i = 0, j = 0;
        while (i < n && j < m) {
            if (j == -1 || s[i] == p[j]) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        return j == m ? i - j : -1;
    }

    private int[] getNext(char[] p) {
        int n = p.length;
        int[] next = new int[n];
        next[0] = -1;
        int i = 0, k = -1;
        while (i < n - 1) {
            if (k == -1 || p[i] == p[k]) {
                next[++i] = ++k;
            } else {
                k = next[k];
            }
        }
        return next;
    }

    @Test
    public void test2() {
        int[] next = getNext("ababa".toCharArray());
        assertThat(next, is(new int[]{-1, 0, 0, 1, 2}));
    }

    @Test
    public void test3() {
        int[] next = getNext("abcabcabcabc".toCharArray());
        for (int val : next) {
            System.out.print(val + " ");
        }
    }

    @Test
    public void test1() {
        String text = "abcabcababaccc";
        String pattern = "ababa";
        assertThat(indexOf(text, pattern), is(6));
    }
}
