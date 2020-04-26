package com.tukeping.leetcode.contest.weekly.contest128;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/13
 **/
public class LeetCode1012 {

    private int N;
    private int value = 0;
    private boolean[] taken = new boolean[10];

    private void rec(long num, int dig) {
        if (num > N) return;
        value++;
        for (int x = 0; x < 10; x++) {
            if (taken[x]) continue;
            if (dig + x == 0) continue;
            taken[x] = true;
            rec(num * 10 + x, dig + 1);
            taken[x] = false;
        }
    }

    private int allDifferent() {
        rec(0, 0);
        return value;
    }

    public int numDupDigitsAtMostN(int N) {
        this.N = N;
        return N - allDifferent() + 1;
    }

    public int numDupDigitsAtMostN3(int N) {
        int ret = 0;
        for (int i = 1; i < 10; i++)
            ret += F(N, (1 << i), i);
        return (N - ret);
    }

    private int F(int limit, int mask, long num) {
        if (num > limit) return 0;
        int ret = 1;
        for (int i = 0; i < 10; i++) {
            if (((mask >> i) & 1) == 0) continue;
            ret += F(limit, mask | (1 << i), num * 10 + i);
        }
        return ret;
    }

    public int numDupDigitsAtMostN2(int N) {
        return N - dp(N);
    }

    public int dp(int n) {
        List<Integer> digits = new ArrayList<>();
        while (n > 0) {
            digits.add(n % 10);
            n /= 10;
        }
        int k = digits.size();

        int[] used = new int[10];
        int total = 0;

        for (int i = 1; i < k; i++) {
            total += 9 * A(9, i - 1);
        }

        for (int i = k - 1; i >= 0; i--) {
            int num = digits.get(i);

            for (int j = i == k - 1 ? 1 : 0; j < num; j++) {
                if (used[j] != 0) {
                    continue;
                }
                total += A(10 - (k - i), i);
            }

            if (++used[num] > 1) {
                break;
            }

            if (i == 0) {
                total += 1;
            }
        }
        return total;
    }

    private int fact(int n) {
        if (n == 1 || n == 0) {
            return 1;
        }
        return n * fact(n - 1);
    }

    private int A(int m, int n) {
        return fact(m) / fact(m - n);
    }

    /**
     * 输入：20
     * 输出：1
     * 解释：具有至少 1 位重复数字的正数（<= 20）只有 11 。
     */
    @Test
    public void test1() {
        int n = numDupDigitsAtMostN(20);
        assertThat(n, is(1));
    }

    /**
     * 输入：100
     * 输出：10
     * 解释：具有至少 1 位重复数字的正数（<= 100）有 11，22，33，44，55，66，77，88，99 和 100 。
     */
    @Test
    public void test2() {
        int n = numDupDigitsAtMostN(100);
        assertThat(n, is(10));
    }

    /**
     * 输入：1000
     * 输出：262
     */
    @Test
    public void test3() {
        int n = numDupDigitsAtMostN(1000);
        assertThat(n, is(262));
    }
}
