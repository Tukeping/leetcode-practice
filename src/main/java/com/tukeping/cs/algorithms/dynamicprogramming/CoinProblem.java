package com.tukeping.cs.algorithms.dynamicprogramming;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/2/15
 **/
public class CoinProblem {

    /**
     * 现在有1，5，11三种面值的纸币，现在需要找够凑足 n 元（比如15元）的最少纸币的 "张数"
     */
    public int solution(int n) {
        int[] f = new int[n + 1];
        f[0] = 0;

        for (int i = 1; i <= n; i++) {
            int cost = Integer.MAX_VALUE;
            if (i - 1 >= 0) cost = Math.min(cost, f[i - 1] + 1);
            if (i - 5 >= 0) cost = Math.min(cost, f[i - 5] + 1);
            if (i - 11 >= 0) cost = Math.min(cost, f[i - 11] + 1);
            f[i] = cost;
            System.out.println(String.format("f[%d] = %d", i, cost));
        }

        return f[n];
    }

    @Test
    public void test1() {
        int n = solution(15);
        assertThat(n, is(3));
    }
}
