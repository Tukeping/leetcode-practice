package com.tukeping.leetcode.lcof;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/30
 **/
public class LCOF62 {

    public int lastRemaining(int n, int m) {
        if (n == 1) return 0;
        int x = lastRemaining(n - 1, m);
        return (m + x) % n;
    }

    public int lastRemaining3(int n, int m) {
        int f = 0;
        for (int i = 2; i != n + 1; ++i)
            f = (m + f) % i;
        return f;
    }

    /**
     * 输入: n = 5, m = 3
     * 输出: 3
     */
    @Test
    public void test1() {
        int n = lastRemaining(5, 3);
        assertThat(n, is(3));
    }

    /**
     * 输入: n = 10, m = 17
     * 输出: 2
     */
    @Test
    public void test2() {
        int n = lastRemaining(10, 17);
        assertThat(n, is(2));
    }
}
