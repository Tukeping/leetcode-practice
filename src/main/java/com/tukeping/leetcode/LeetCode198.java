package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=198 lang=java
 *
 * [198] 打家劫舍
 *
 * https://leetcode-cn.com/problems/house-robber/description/
 *
 * algorithms
 * Easy (42.29%)
 * Likes:    641
 * Dislikes: 0
 * Total Accepted:    76K
 * Total Submissions: 176.7K
 * Testcase Example:  '[1,2,3,1]'
 *
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *
 * 示例 1:
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 示例 2:
 * 输入: [2,7,9,3,1]
 * 输出: 12
 * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * dynamic-programming
 *
 * airbnb | linkedin
 *
 * @author tukeping
 * @date 2020/2/21
 **/
public class LeetCode198 {

    /**
     * 动态规划方程:
     * Max(Money) = f(x)
     * f(x) = f(x) + f(x-2)
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        int max, maxTmp, gap;

        if (n == 1) {
            max = nums[0];
        } else {
            max = Math.max(nums[0], nums[1]);
            for (int i = 2; i < n; i++) {
                maxTmp = -1;
                gap = 2;
                while (i - gap >= 0) {
                    maxTmp = Math.max(maxTmp, nums[i] + nums[i - gap]);
                    gap++;
                }
                nums[i] = maxTmp;
                max = Math.max(nums[i], max);
            }
        }

        return max;
    }

    /**
     * 输入: [1,2,3,1]
     * 输出: 4
     * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     */
    @Test
    public void test1() {
        int n = rob(new int[]{1, 2, 3, 1});
        assertThat(n, is(4));
    }

    /**
     * 输入: [2,7,9,3,1]
     * 输出: 12
     * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     */
    @Test
    public void test2() {
        int n = rob(new int[]{2, 7, 9, 3, 1});
        assertThat(n, is(12));
    }

    @Test
    public void test3() {
        int n = rob(new int[]{0});
        assertThat(n, is(0));
    }

    @Test
    public void test4() {
        int n = rob(new int[]{1, 3, 1});
        assertThat(n, is(3));
    }

    @Test
    public void test5() {
        int n = rob(new int[]{2, 1, 1, 2});
        assertThat(n, is(4));
    }
}
