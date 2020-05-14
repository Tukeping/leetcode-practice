package com.tukeping.cp.leetcode.contest.biweekly.contest25;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/5/2
 **/
public class LeetCode5385 {

    public int maxDiff(int num) {
        List<Integer> nums = new ArrayList<>();
        int tmp = num;
        while (tmp > 0) {
            nums.add(tmp % 10);
            tmp /= 10;
        }
        int base = 10;
        int mx = 0, mxRep = -1;
        for (int i = nums.size() - 1; i >= 0; i--) {
            if (nums.get(i) == 9) {
                mx += 9 * (int) Math.pow(base, i);
            } else if (mxRep == -1) {
                mxRep = nums.get(i);
                mx += 9 * (int) Math.pow(base, i);
            } else if (mxRep == nums.get(i)) {
                mx += 9 * (int) Math.pow(base, i);
            } else {
                mx += nums.get(i) * (int) Math.pow(base, i);
            }
        }
        int min = 0, minRep = -1, minRepNum = -1, noNum = -1;
        for (int i = nums.size() - 1; i >= 0; i--) {
            if (nums.get(i) == 1 && i == nums.size() - 1) {
                noNum = 1;
                min += (int) Math.pow(base, i);
            } else if (minRep == -1 && nums.get(i) > noNum) {
                minRep = nums.get(i);
                if (i == nums.size() - 1) minRepNum = 1;
                else minRepNum = 0;
                min += minRepNum * (int) Math.pow(base, i);
            } else if (minRep == nums.get(i)) {
                min += minRepNum * (int) Math.pow(base, i);
            } else {
                min += nums.get(i) * (int) Math.pow(base, i);
            }
        }
        return mx - min;
    }

    /**
     * 输入：num = 555
     * 输出：888
     * 解释：第一次选择 x = 5 且 y = 9 ，并把得到的新数字保存在 a 中。
     * 第二次选择 x = 5 且 y = 1 ，并把得到的新数字保存在 b 中。
     * 现在，我们有 a = 999 和 b = 111 ，最大差值为 888
     */
    @Test
    public void test1() {
        int n = maxDiff(555);
        assertThat(n, is(888));
    }

    /**
     * 输入：num = 9
     * 输出：8
     * 解释：第一次选择 x = 9 且 y = 9 ，并把得到的新数字保存在 a 中。
     * 第二次选择 x = 9 且 y = 1 ，并把得到的新数字保存在 b 中。
     * 现在，我们有 a = 9 和 b = 1 ，最大差值为 8
     */
    @Test
    public void test2() {
        int n = maxDiff(9);
        assertThat(n, is(8));
    }

    /**
     * 输入：num = 123456
     * 输出：820000
     */
    @Test
    public void test3() {
        int n = maxDiff(123456);
        assertThat(n, is(820000));
    }

    /**
     * 输入：num = 10000
     * 输出：80000
     */
    @Test
    public void test4() {
        int n = maxDiff(10000);
        assertThat(n, is(80000));
    }

    /**
     * 输入：num = 9288
     * 输出：8700
     */
    @Test
    public void test5() {
        int n = maxDiff(9288);
        assertThat(n, is(8700));
    }

    @Test
    public void test6() {
        int n = maxDiff(1);
        assertThat(n, is(8));
    }

    @Test
    public void test7() {
        int n = maxDiff(100000000);
        assertThat(n, is(800000000));
    }

    @Test
    public void test8() {
        int n = maxDiff(1101057);
        assertThat(n, is(8808050));
    }
}