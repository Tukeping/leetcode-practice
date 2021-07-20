package com.tukeping.lcof;

/*
 * 面试题53 - II. 0～n-1中缺失的数字
 *
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 *
 * 示例 1:
 *
 * 输入: [0,1,3]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [0,1,2,3,4,5,6,7,9]
 * 输出: 8
 *
 * 限制：
 *
 * 1 <= 数组长度 <= 10000
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/27
 **/
public class LCOF53_2 {

    public int missingNumber(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i != nums[i]) return nums[i] - 1;
        }
        return n;
    }

    public int missingNumber2(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n + 1];
        for (int i = 0; i <= n; i++) ans[i] = i;
        for (int num : nums) ans[num] = -1;
        for (int i = 0; i <= n; i++) {
            if (ans[i] != -1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 输入: [0,1,3]
     * 输出: 2
     */
    @Test
    public void test1() {
        int n = missingNumber(new int[]{0, 1, 3});
        assertThat(n, is(2));
    }

    /**
     * 输入: [0,1,2,3,4,5,6,7,9]
     * 输出: 8
     */
    @Test
    public void test2() {
        int n = missingNumber(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 9});
        assertThat(n, is(8));
    }

    @Test
    public void test3() {
        int n = missingNumber(new int[]{1, 2});
        assertThat(n, is(0));
    }

    @Test
    public void test4() {
        int n = missingNumber(new int[]{0, 1});
        assertThat(n, is(2));
    }

    @Test
    public void test5() {
        int n = missingNumber(new int[]{0, 2});
        assertThat(n, is(1));
    }

    @Test
    public void test6() {
        int n = missingNumber(new int[]{0, 2, 3});
        assertThat(n, is(1));
    }

    @Test
    public void test7() {
        int n = missingNumber(new int[]{0, 1, 3});
        assertThat(n, is(2));
    }
}
