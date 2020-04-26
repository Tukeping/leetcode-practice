package com.tukeping.leetcode.contest.biweekly.contest24;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/19
 **/
public class LeetCode5372 {

    public int minStartValue(int[] nums) {
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            if (sum < min) min = sum;
        }
        return min > 0 ? 1 : -min + 1;
    }

    @Test
    public void test1() {
        int n = minStartValue(new int[]{-3, 2, -3, 4, 2});
        assertThat(n, is(5));
    }

    @Test
    public void test2() {
        int n = minStartValue(new int[]{1, 2});
        assertThat(n, is(1));
    }

    @Test
    public void test3() {
        int n = minStartValue(new int[]{1, -2, -3});
        assertThat(n, is(5));
    }
}
