package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=303 lang=java
 *
 * [303] 区域和检索 - 数组不可变
 *
 * https://leetcode-cn.com/problems/range-sum-query-immutable/description/
 *
 * algorithms
 * Easy (60.48%)
 * Likes:    142
 * Dislikes: 0
 * Total Accepted:    32.4K
 * Total Submissions: 53.4K
 * Testcase Example:  '["NumArray","sumRange","sumRange","sumRange"]\n' +
  '[[[-2,0,3,-5,2,-1]],[0,2],[2,5],[0,5]]'
 *
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
 *
 * 示例：
 *
 * 给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()
 *
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 *
 * 说明:
 *
 *
 * 你可以假设数组不可变。
 * 会多次调用 sumRange 方法。
 *
 *
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author tukeping
 * @date 2020/3/24
 **/
public class LeetCode303 {

    class NumArray {

        private int[] sum;

        public NumArray(int[] nums) {
            sum = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                sum[i + 1] = sum[i] + nums[i];
            }
        }

        public int sumRange(int i, int j) {
            return sum[j + 1] - sum[i];
        }
    }

    class NumArray2 {

        int[][] dp;

        public NumArray2(int[] nums) {
            int len = nums.length;
            dp = new int[len][len];
            for (int i = 0; i < len; i++) {
                for (int j = i; j < len; j++) {
                    if (i == 0 && j == 0) dp[i][j] = nums[j];
                    else dp[i][j] = dp[i][j - 1] + nums[j];
                }
            }
        }

        public int sumRange(int i, int j) {
            return dp[i][j];
        }
    }

    /**
     * 给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()
     *
     * sumRange(0, 2) -> 1
     * sumRange(2, 5) -> -1
     * sumRange(0, 5) -> -3
     */
    @Test
    public void test1() {
        NumArray numArray = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});
        assertThat(numArray.sumRange(0, 2), is(1));
        assertThat(numArray.sumRange(2, 5), is(-1));
        assertThat(numArray.sumRange(0, 5), is(-3));
    }
}
