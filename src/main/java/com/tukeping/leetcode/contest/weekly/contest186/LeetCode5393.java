package com.tukeping.leetcode.contest.weekly.contest186;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/4/26
 **/
public class LeetCode5393 {

    public int maxScore(int[] a, int k) {
        int n = a.length, sum = 0, max = 0;
        for (int i = 0; i < k; i++) sum += a[i];

        max = Math.max(max, sum);

        for (int i = 0; i < k; i++) {
            sum -= a[k - 1 - i];
            sum += a[n - 1 - i];
            max = Math.max(max, sum);
        }
        return max;
    }

    /**
     * 输入：cardPoints = [1,2,3,4,5,6,1], k = 3
     * 输出：12
     * 解释：第一次行动，不管拿哪张牌，你的点数总是 1 。但是，先拿最右边的卡牌将会最大化你的可获得点数。最优策略是拿右边的三张牌，最终点数为 1 + 6 + 5 = 12 。
     */
    @Test
    public void test1() {
        int n = maxScore(new int[]{1, 2, 3, 4, 5, 6, 1}, 3);
        assertThat(n, is(12));
    }

    /**
     * 输入：cardPoints = [2,2,2], k = 2
     * 输出：4
     * 解释：无论你拿起哪两张卡牌，可获得的点数总是 4 。
     */
    @Test
    public void test2() {
        int n = maxScore(new int[]{2, 2, 2}, 2);
        assertThat(n, is(4));
    }

    /**
     * 输入：cardPoints = [9,7,7,9,7,7,9], k = 7
     * 输出：55
     * 解释：你必须拿起所有卡牌，可以获得的点数为所有卡牌的点数之和。
     */
    @Test
    public void test3() {
        int n = maxScore(new int[]{9, 7, 7, 9, 7, 7, 9}, 7);
        assertThat(n, is(55));
    }

    /**
     * 输入：cardPoints = [1,1000,1], k = 1
     * 输出：1
     * 解释：你无法拿到中间那张卡牌，所以可以获得的最大点数为 1 。
     */
    @Test
    public void test4() {
        int n = maxScore(new int[]{1, 1000, 1}, 1);
        assertThat(n, is(1));
    }

    /**
     * 输入：cardPoints = [1,79,80,1,1,1,200,1], k = 3
     * 输出：202
     */
    @Test
    public void test5() {
        int n = maxScore(new int[]{1, 79, 80, 1, 1, 1, 200, 1}, 3);
        assertThat(n, is(202));
    }
}
