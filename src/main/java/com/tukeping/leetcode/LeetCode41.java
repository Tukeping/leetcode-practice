package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=41 lang=java
 *
 * [41] 缺失的第一个正数
 *
 * https://leetcode-cn.com/problems/first-missing-positive/description/
 *
 * algorithms
 * Hard (37.15%)
 * Likes:    332
 * Dislikes: 0
 * Total Accepted:    30.9K
 * Total Submissions: 83.1K
 * Testcase Example:  '[1,2,0]'
 *
 * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
 *
 * 示例 1:
 *
 * 输入: [1,2,0]
 * 输出: 3
 *
 *
 * 示例 2:
 *
 * 输入: [3,4,-1,1]
 * 输出: 2
 *
 *
 * 示例 3:
 *
 * 输入: [7,8,9,11,12]
 * 输出: 1
 *
 *
 * 说明:
 *
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
 *
 */

import org.junit.Assert;
import org.junit.Test;

/**
 * @author tukeping
 * @date 2019/12/23
 **/
public class LeetCode41 {

    @Test
    public void test() {
        int[] nums = new int[]{1, 2, 0};
        int i = firstMissingPositive(nums);
        Assert.assertEquals(3, i);
    }

    @Test
    public void test2() {
        int[] nums = new int[]{3, 4, -1, 1};
        int i = firstMissingPositive(nums);
        Assert.assertEquals(2, i);
    }

    /**
     *  检查 1 是否存在于数组中。如果没有，则已经完成，1 即为答案。
     *  如果 nums = [1]，答案即为 2 。
     *  将负数，零，和大于 n 的数替换为 1 。
     *  遍历数组。当读到数字 a 时，替换第 a 个元素的符号。
     *  注意重复元素：只能改变一次符号。由于没有下标 n ，使用下标 0 素保存是否存在数字 n。
     *  再次遍历数组。返回第一个正数元素的下标。
     *  如果 nums[0] > 0，则返回 n 。
     *  如果之前的步骤中没有发现 nums 中有正数元素，则返回n + 1。
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        boolean containOne = false;
        for (int num : nums) {
            if (num == 1) {
                containOne = true;
                break;
            }
        }

        if (!containOne) {
            return 1;
        }

        if (n == 1) {
            return 2;
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0 || nums[i] > n) {
                nums[i] = 1;
            }
        }

        for (int i = 0; i < n; i++) {
            int idx = Math.abs(nums[i]);

            if (idx == n) {
                nums[0] = -Math.abs(nums[0]);
            } else {
                nums[idx] = -Math.abs(nums[idx]);
            }
        }

        for (int i = 1; i < n; i++) {
            if (nums[i] > 0) {
                return i;
            }
        }

        if (nums[0] > 0) {
            return n;
        }

        return n + 1;
    }
}
