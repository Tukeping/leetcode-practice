package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=16 lang=java
 *
 * [16] 最接近的三数之和
 *
 * https://leetcode-cn.com/problems/3sum-closest/description/
 *
 * algorithms
 * Medium (43.42%)
 * Likes:    396
 * Dislikes: 0
 * Total Accepted:    89.5K
 * Total Submissions: 205.1K
 * Testcase Example:  '[-1,2,1,-4]\n1'
 *
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target
 * 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 *
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 *
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 *
 *
 */

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * array | two-pointers
 *
 * bloomberg
 *
 * @author tukeping
 * @date 2020/4/6
 **/
public class LeetCode16 {

    public int threeSumClosest(int[] nums, int target) {
        int len = nums.length;

        Arrays.sort(nums);

        int ans = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < len; i++) {
            int start = i + 1, end = len - 1;
            while (start < end) {
                int threeSum = nums[start] + nums[end] + nums[i];
                if (Math.abs(threeSum - target) < Math.abs(ans - target)) {
                    ans = threeSum;
                }
                if (threeSum > target) {
                    end--;
                } else if (threeSum < target) {
                    start++;
                } else { // threeSum == target
                    return ans;
                }
            }
        }
        return ans;
    }

    /**
     * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
     *
     * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
     */
    @Test
    public void test1() {
        int n = threeSumClosest(new int[]{-1, 2, 1, -4}, 1);
        assertThat(n, is(2));
    }
}
