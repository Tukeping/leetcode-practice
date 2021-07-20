package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=35 lang=java
 *
 * [35] 搜索插入位置
 *
 * https://leetcode-cn.com/problems/search-insert-position/description/
 *
 * algorithms
 * Easy (44.70%)
 * Likes:    435
 * Dislikes: 0
 * Total Accepted:    120K
 * Total Submissions: 265.4K
 * Testcase Example:  '[1,3,5,6]\n5'
 *
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 你可以假设数组中无重复元素。
 *
 * 示例 1:
 *
 * 输入: [1,3,5,6], 5
 * 输出: 2
 *
 * 示例 2:
 *
 * 输入: [1,3,5,6], 2
 * 输出: 1
 *
 * 示例 3:
 *
 * 输入: [1,3,5,6], 7
 * 输出: 4
 *
 * 示例 4:
 *
 * 输入: [1,3,5,6], 0
 * 输出: 0
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * array | binary-search
 *
 * Unknown
 *
 * @author tukeping
 * @date 2020/2/21
 **/
public class LeetCode35 {

    public int searchInsertV2(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= target) r = mid;
            else l = mid + 1;
        }
        return nums[l] < target ? l + 1 : l;
    }

    public int searchInsert(int[] nums, int target) {
        return bsearch(nums, target, 0, nums.length - 1);
    }

    private int bsearch(int[] nums, int target, int start, int end) {
        if (start > end) return start;

        int mid = (start + end) / 2;

        if (nums[mid] == target)
            return mid;

        if (nums[mid] > target) {
            if (start == end) return mid;
            else return bsearch(nums, target, start, mid - 1);
        } else {
            if (start == end) return mid + 1;
            else return bsearch(nums, target, mid + 1, end);
        }
    }

    /**
     * 输入: [1,3,5,6], 5
     * 输出: 2
     */
    @Test
    public void test1() {
        int n = searchInsert(new int[]{1, 3, 5, 6}, 5);
        assertThat(n, is(2));
    }

    /**
     * 输入: [1,3,5,6], 2
     * 输出: 1
     */
    @Test
    public void test2() {
        int n = searchInsert(new int[]{1, 3, 5, 6}, 2);
        assertThat(n, is(1));
    }

    /**
     * 输入: [1,3,5,6], 7
     * 输出: 4
     */
    @Test
    public void test3() {
        int n = searchInsert(new int[]{1, 3, 5, 6}, 7);
        assertThat(n, is(4));
    }

    /**
     * 输入: [1,3,5,6], 0
     * 输出: 0
     */
    @Test
    public void test4() {
        int n = searchInsert(new int[]{1, 3, 5, 6}, 0);
        assertThat(n, is(0));
    }

    @Test
    public void test5() {
        int n = searchInsert(new int[]{3, 5, 7, 9, 10}, 8);
        assertThat(n, is(3));
    }
}
