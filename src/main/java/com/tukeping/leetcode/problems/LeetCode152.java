package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=152 lang=java
 *
 * [152] 乘积最大子序列
 *
 * https://leetcode-cn.com/problems/maximum-product-subarray/description/
 *
 * algorithms
 * Medium (36.29%)
 * Likes:    417
 * Dislikes: 0
 * Total Accepted:    39.7K
 * Total Submissions: 106K
 * Testcase Example:  '[2,3,-2,4]'
 *
 * 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
 *
 * 示例 1:
 *
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 *
 *
 * 示例 2:
 *
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * array | dynamic-programming
 *
 * linkedin
 *
 * @author tukeping
 * @date 2020/3/17
 **/
public class LeetCode152 {

    public int maxProductV2(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        int[][] dp = new int[n][2];
        dp[0][0] = nums[0]; // 0 - 极小值
        dp[0][1] = nums[0]; // 1 - 极大值
        int max = nums[0];
        for (int i = 1; i < n; i++) {
            int k1 = nums[i];
            int k2 = nums[i] * dp[i - 1][0];
            int k3 = nums[i] * dp[i - 1][1];
            dp[i][0] = Math.min(k1, Math.min(k2, k3));
            dp[i][1] = Math.max(k1, Math.max(k2, k3));
            max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
        }
        return max;
    }

    public int maxProduct(int[] nums) {
        int iThMax = 1, iThMin = 1, max = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num < 0) {
                int tmp = iThMax;
                iThMax = iThMin;
                iThMin = tmp;
            }
            iThMax = Math.max(iThMax * num, num);
            iThMin = Math.min(iThMin * num, num);
            max = Math.max(max, iThMax);
        }
        return max;
    }

    /**
     * 输入: [2,3,-2,4]
     * 输出: 6
     * 解释: 子数组 [2,3] 有最大乘积 6。
     */
    @Test
    public void test1() {
        int n = maxProduct(new int[]{2, 3, -2, 4});
        assertThat(n, is(6));
    }

    /**
     * 输入: [-2,0,-1]
     * 输出: 0
     * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
     */
    @Test
    public void test2() {
        int n = maxProduct(new int[]{-2, 0, -1});
        assertThat(n, is(0));
    }

    @Test
    public void test3() {
        int n = maxProduct(new int[]{-2, 3, -4});
        assertThat(n, is(24));
    }

    @Test
    public void test4() {
        int n = maxProduct(new int[]{-2});
        assertThat(n, is(-2));
    }

    @Test
    public void test5() {
        int n = maxProduct(new int[]{2, -5, -2, -4, 3});
        assertThat(n, is(24));
    }

    @Test
    public void test6() {
        int n = maxProduct(new int[]{1, -2, 3, -4, -3, -4, -3});
        assertThat(n, is(432));
    }
}
