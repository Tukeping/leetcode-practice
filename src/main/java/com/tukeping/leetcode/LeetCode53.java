package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=53 lang=java
 *
 * [53] 最大子序和
 *
 * https://leetcode-cn.com/problems/maximum-subarray/description/
 *
 * algorithms
 * Easy (48.40%)
 * Likes:    1634
 * Dislikes: 0
 * Total Accepted:    162.2K
 * Total Submissions: 329.3K
 * Testcase Example:  '[-2,1,-3,4,-1,2,1,-5,4]'
 *
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 * 进阶:
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * array | divide-and-conquer | dynamic-programming
 *
 * bloomberg | linkedin | microsoft
 *
 * @author tukeping
 * @date 2020/2/21
 **/
public class LeetCode53 {

    /**
     * 202/202 cases passed (1 ms)
     * Your runtime beats 99.64 % of java submissions
     * Your memory usage beats 8.59 % of java submissions (41.4 MB)
     */

    public int maxSubArray(int[] nums) {
        int n = nums.length, maxSum = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i - 1] > 0) {
                nums[i] += nums[i - 1];
            }
            maxSum = Math.max(maxSum, nums[i]);
        }
        return maxSum;
    }

    /**
     * 输入: [-2,1,-3,4,-1,2,1,-5,4]
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     */
    @Test
    public void test1() {
        int n = maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        assertThat(n, is(6));
    }
}
