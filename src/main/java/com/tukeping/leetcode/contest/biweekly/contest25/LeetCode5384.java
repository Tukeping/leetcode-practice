package com.tukeping.leetcode.contest.biweekly.contest25;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/5/3
 **/
public class LeetCode5384 {

    public List<Boolean> kidsWithCandies(int[] a, int m) {
        int n = a.length;
        int mx = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) mx = Math.max(mx, a[i]);

        List<Boolean> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            res.add(a[i] + m >= mx);
        }
        return res;
    }

    /**
     * 输入：candies = [2,3,5,1,3], extraCandies = 3
     * 输出：[true,true,true,false,true]
     * 解释：
     * 孩子 1 有 2 个糖果，如果他得到所有额外的糖果（3个），那么他总共有 5 个糖果，他将成为拥有最多糖果的孩子。
     * 孩子 2 有 3 个糖果，如果他得到至少 2 个额外糖果，那么他将成为拥有最多糖果的孩子。
     * 孩子 3 有 5 个糖果，他已经是拥有最多糖果的孩子。
     * 孩子 4 有 1 个糖果，即使他得到所有额外的糖果，他也只有 4 个糖果，无法成为拥有糖果最多的孩子。
     * 孩子 5 有 3 个糖果，如果他得到至少 2 个额外糖果，那么他将成为拥有最多糖果的孩子。
     */
    @Test
    public void test1() {
        List<Boolean> ans = kidsWithCandies(new int[]{2, 3, 5, 1, 3}, 3);
        assertThat(ans, is(Arrays.asList(Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE, Boolean.TRUE)));
    }

    /**
     * 输入：candies = [4,2,1,1,2], extraCandies = 1
     * 输出：[true,false,false,false,false]
     * 解释：只有 1 个额外糖果，所以不管额外糖果给谁，只有孩子 1 可以成为拥有糖果最多的孩子。
     */
    @Test
    public void test2() {
        List<Boolean> ans = kidsWithCandies(new int[]{4, 2, 1, 1, 2}, 1);
        assertThat(ans, is(Arrays.asList(Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE)));
    }

    /**
     * 输入：candies = [12,1,12], extraCandies = 10
     * 输出：[true,false,true]
     */
    @Test
    public void test3() {
        List<Boolean> ans = kidsWithCandies(new int[]{12, 1, 12}, 10);
        assertThat(ans, is(Arrays.asList(Boolean.TRUE, Boolean.FALSE, Boolean.TRUE)));
    }
}
