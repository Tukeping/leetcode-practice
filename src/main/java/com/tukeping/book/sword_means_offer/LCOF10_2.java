package com.tukeping.book.sword_means_offer;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author tukeping
 * @date 2020/3/26
 **/
public class LCOF10_2 {

    public int numWays(int n) {
        if (n == 0) return 1;
        if (n == 1) return 1;
        if (n == 2) return 2;

        int f1 = 1;
        int f2 = 2;
        int cur = 0;
        int MOD = 1000000007;
        for (int i = 3; i <= n; i++) {
            cur = (f1 + f2) % MOD;
            f1 = f2;
            f2 = cur;
        }
        return cur;
    }

    @Test
    public void test0() {
        int n = numWays(1);
        assertThat(n, is(1));
    }

    @Test
    public void test1() {
        int n = numWays(2);
        assertThat(n, is(2));
    }

    @Test
    public void test2() {
        int n = numWays(3);
        assertThat(n, is(3));
    }

    @Test
    public void test3() {
        int n = numWays(4);
        assertThat(n, is(5));
    }

    @Test
    public void test4() {
        int n = numWays(5);
        assertThat(n, is(8));
    }

    @Test
    public void test5() {
        int n = numWays(10);
        assertThat(n, is(89));
    }
}
