package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=169 lang=java
 *
 * [169] 多数元素
 *
 * https://leetcode-cn.com/problems/majority-element/description/
 *
 * algorithms
 * Easy (60.78%)
 * Likes:    389
 * Dislikes: 0
 * Total Accepted:    93.2K
 * Total Submissions: 153.3K
 * Testcase Example:  '[3,2,3]'
 *
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,3]
 * 输出: 3
 *
 * 示例 2:
 *
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 *
 *
 */

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author tukeping
 * @date 2019/12/23
 **/
public class LeetCode169 {

    public static int majorityElement(int[] nums) {
        int n = nums.length;
        int m = n / 2;

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int cur = map.merge(nums[i], 1, Integer::sum);
            if (cur > m) {
                return nums[i];
            }
        }

        return 0;
    }

    public static int majorityElementV2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    private static int count(int value, int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (value == nums[i]) {
                count++;
            }
        }
        return count;
    }

    public static int majorityElementV3(int[] nums) {
        int m = nums.length / 2;

        Random random = new Random();

        while (true) {
            int r = nums[random.nextInt(nums.length)];
            int c = count(r, nums);
            if (c > m) {
                return r;
            }
        }
    }

    public static int countInRange(int[] nums, int num, int low, int high) {
        int count = 0;
        for (int i = low; i <= high; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    public static int majorityElementV4(int[] nums) {
        return majorityElementDC(nums, 0, nums.length - 1);
    }

    public static int majorityElementDC(int[] nums, int low, int high) {
        // 递归终止条件
        if (low == high) {
            return nums[low];
        }

        // 切分子问题
        int mid = (high - low) / 2 + low;

        // 计算两个子问题 left and right
        int left = majorityElementDC(nums, low, mid);
        int right = majorityElementDC(nums, mid + 1, high);

        if (left == right) {
            return left;
        }

        // 子问题 判断多数的核心逻辑
        int leftCount = countInRange(nums, left, low, high);
        int rightCount = countInRange(nums, right, low, high);

        return leftCount > rightCount ? left : right;
    }

    /**
     * Boyer-Moore
     */
    public static int majorityElementBM(int[] nums) {
        int count = 1;
        int candidate = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == candidate) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                i++;
                count = 1;
                candidate = nums[i];
            }
        }
        return candidate;
    }

    @Test
    public void test2() {
        int[] nums = new int[]{2, 2, 1, 1, 1, 2, 2};
        int ret = majorityElementBM(nums);
        Assert.assertEquals(2, ret);
    }

    @Test
    public void test1() {
        int[] nums = new int[]{3, 2, 3};
        int ret = majorityElementBM(nums);
        Assert.assertEquals(3, ret);
    }
}
