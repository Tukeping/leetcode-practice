package com.tukeping.leetcode.contest.weekly.contest128;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/14
 **/
public class LeetCode1010 {

    public int numPairsDivisibleBy60(int[] time) {
        int[] count = new int[60];
        for (int i = 0; i < time.length; i++) {
            count[time[i] % 60]++;
        }

        int ans = 0;
        for (int i = 1; i < 30; i++) {
            ans += count[i] * count[60 - i];
        }

        ans += count[0] * (count[0] - 1) / 2;
        ans += count[30] * (count[30] - 1) / 2;

        return ans;
    }

    /**
     * 输入：[30,20,150,100,40]
     * 输出：3
     * 解释：这三对的总持续时间可被 60 整数：
     * (time[0] = 30, time[2] = 150): 总持续时间 180
     * (time[1] = 20, time[3] = 100): 总持续时间 120
     * (time[1] = 20, time[4] = 40): 总持续时间 60
     */
    @Test
    public void test1() {
        int n = numPairsDivisibleBy60(new int[]{30, 20, 150, 100, 40});
        assertThat(n, is(3));
    }

    /**
     * 输入：[60,60,60]
     * 输出：3
     * 解释：所有三对的总持续时间都是 120，可以被 60 整数。
     */
    @Test
    public void test2() {
        int n = numPairsDivisibleBy60(new int[]{60, 60, 60});
        assertThat(n, is(3));
    }
}
