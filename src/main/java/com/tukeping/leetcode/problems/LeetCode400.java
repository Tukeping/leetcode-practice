package com.tukeping.leetcode.problems;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/3
 **/
public class LeetCode400 {

    public int findNthDigit(int n) {
        if (n <= 9) return n;
        n -= 1;
        for (long digits = 1; digits < 11; ++digits) {
            int first_num = (int) Math.pow(10, digits - 1);
            if (n < 9 * first_num * digits) {
                String numStr = String.valueOf(first_num + n / digits);
                return (int) (numStr.charAt((int) (n % digits))) - '0';
            }
            n -= 9 * first_num * digits;
        }
        return 0;
    }

    /**
     * 输入：n = 3
     * 输出：3
     */
    @Test
    public void test1() {
        int n = findNthDigit(3);
        assertThat(n, is(3));
    }

    /**
     * 输入：n = 11
     * 输出：0
     */
    @Test
    public void test2() {
        int n = findNthDigit(11);
        assertThat(n, is(0));
    }
}
