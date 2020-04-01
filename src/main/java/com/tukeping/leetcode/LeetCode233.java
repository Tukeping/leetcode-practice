package com.tukeping.leetcode;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/1
 **/
public class LeetCode233 {

    public int countDigitOne(int n) {
        int cnt = 0;
        for (long i = 1; i <= n; i *= 10) {
            long d = i * 10;
            cnt += (n / d) * i + Math.min(Math.max(n % d - i + 1, 0), i);
        }
        return cnt;
    }

    /**
     * 输入: 13
     * 输出: 6
     * 解释: 数字 1 出现在以下数字中: 1, 10, 11, 12, 13 。
     */
    @Test
    public void test1() {
        int n = countDigitOne(13);
        assertThat(n, is(6));
    }

    @Test
    public void test2() {
        int n = countDigitOne(1234);
        assertThat(n, is(689));
    }
}
