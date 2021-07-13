package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=34 lang=java
 *
 * [34] 在排序数组中查找元素的第一个和最后一个位置
 *
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/
 *
 * algorithms
 * Medium (38.29%)
 * Likes:    343
 * Dislikes: 0
 * Total Accepted:    71.4K
 * Total Submissions: 182.2K
 * Testcase Example:  '[5,7,7,8,8,10]\n8'
 *
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 示例 1:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 *
 * 示例 2:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 *
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * array | binary-search
 *
 * linkedin
 *
 * @author tukeping
 * @date 2020/3/16
 **/
public class LeetCode34 {

    /** time: O(log n) space: O(1) **/
    public int[] searchRange(int[] nums, int target) {
        int len = nums.length;
        int start = bsearch(nums, 0, len - 1, target, true, false);
        int end = bsearch(nums, 0, len - 1, target, false, false);
        return new int[]{start, end};
    }

    private int bsearch(int[] nums, int start, int end, int target, boolean first, boolean found) {
        if (start > end) {
            if (found) return first ? start : end;
            else return -1;
        }

        int mid = (start + end) / 2;

        if (nums[mid] == target) {
            if (first) {
                return bsearch(nums, start, mid - 1, target, first, true);
            } else {
                return bsearch(nums, mid + 1, end, target, first, true);
            }
        } else if (nums[mid] > target) {
            return bsearch(nums, start, mid - 1, target, first, found);
        } else { // nums[mid] < target
            return bsearch(nums, mid + 1, end, target, first, found);
        }
    }

    /**
     * 输入: nums = [5,7,7,8,8,10], target = 8
     * 输出: [3,4]
     */
    @Test
    public void test1() {
        int[] res = searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8);
        assertThat(res, is(new int[]{3, 4}));
    }

    /**
     * 输入: nums = [5,7,7,8,8,10], target = 6
     * 输出: [-1,-1]
     */
    @Test
    public void test2() {
        int[] res = searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6);
        assertThat(res, is(new int[]{-1, -1}));
    }
}
