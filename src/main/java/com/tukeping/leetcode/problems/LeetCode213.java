package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=213 lang=java
 *
 * [213] 打家劫舍 II
 *
 * https://leetcode-cn.com/problems/house-robber-ii/description/
 *
 * algorithms
 * Medium (35.49%)
 * Likes:    189
 * Dislikes: 0
 * Total Accepted:    20.5K
 * Total Submissions: 56.3K
 * Testcase Example:  '[2,3,2]'
 *
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *
 * 示例 1:
 *
 * 输入: [2,3,2]
 * 输出: 3
 * 解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 *
 *
 * 示例 2:
 *
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * dynamic-programming
 *
 * microsoft
 *
 * @author tukeping
 * @date 2020/2/21
 **/
public class LeetCode213 {

    public int robV2(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        else if (n == 2) return Math.max(nums[0], nums[1]);
        else return Math.max(rob(nums, 0, n - 2), rob(nums, 1, n - 1));
    }

    private int rob(int[] nums, int l, int r) {
        int[][] dp = new int[r + 1][2];
        dp[l][0] = 0;
        dp[l][1] = nums[l];
        for (int i = l + 1; i <= r; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = Math.max(dp[i - 1][0] + nums[i], dp[i - 1][1]);
        }
        return Math.max(dp[r][0], dp[r][1]);
    }

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;

        if (n == 1) {
            return nums[0];
        } else if (n == 2) {
            return Math.max(nums[0], nums[1]);
        } else {
            int[] tmp = new int[n - 1];
            System.arraycopy(nums, 1, tmp, 0, n - 1);
            int max1 = robMax(tmp, n - 1);
            System.arraycopy(nums, 0, tmp, 0, n - 1);
            int max2 = robMax(tmp, n - 1);
            return Math.max(max1, max2);
        }
    }

    private int robMax(int[] nums, int n) {
        int maxTmp, gap;
        int max = Math.max(nums[0], nums[1]);
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
        return max;
    }

    /**
     * 输入: [2,3,2]
     * 输出: 3
     * 解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
     */
    @Test
    public void test1() {
        int n = rob(new int[]{2, 3, 2});
        assertThat(n, is(3));
    }

    /**
     * 输入: [1,2,3,1]
     * 输出: 4
     * 解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     */
    @Test
    public void test2() {
        int n = rob(new int[]{1, 2, 3, 1});
        assertThat(n, is(4));
    }

    @Test
    public void test3() {
        int n = rob(new int[]{0});
        assertThat(n, is(0));
    }

    @Test
    public void test4() {
        int n = rob(new int[]{1, 3});
        assertThat(n, is(3));
    }

    @Test
    public void test5() {
        int n = rob(new int[]{3, 1});
        assertThat(n, is(3));
    }

    @Test
    public void test6() {
        int n = rob(new int[]{1, 3, 1});
        assertThat(n, is(3));
    }

    @Test
    public void test7() {
        int n = rob(new int[]{3, 1, 3});
        assertThat(n, is(3));
    }

    @Test
    public void test8() {
        int n = rob(new int[]{3, 1, 3, 1});
        assertThat(n, is(6));
    }

    @Test
    public void test9() {
        int n = rob(new int[]{5, 3, 1, 3});
        assertThat(n, is(6));
    }
}
