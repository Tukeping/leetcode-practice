package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=912 lang=java
 *
 * [912] 排序数组
 *
 * https://leetcode-cn.com/problems/sort-an-array/description/
 *
 * algorithms
 * Medium (53.32%)
 * Likes:    100
 * Dislikes: 0
 * Total Accepted:    48.1K
 * Total Submissions: 81.2K
 * Testcase Example:  '[5,2,3,1]'
 *
 * 给你一个整数数组 nums，请你将该数组升序排列。
 *
 *
 *
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [5,2,3,1]
 * 输出：[1,2,3,5]
 *
 *
 * 示例 2：
 *
 * 输入：nums = [5,1,1,2,0,0]
 * 输出：[0,0,1,1,2,5]
 *
 *
 *
 *
 * 提示：
 *
 *
 * 1 <= nums.length <= 50000
 * -50000 <= nums[i] <= 50000
 *
 *
 */

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * binary-search | random
 *
 * @author tukeping
 * @date 2020/3/31
 **/
public class LeetCode912 {

    public int[] sortArray2(int[] nums) {
        Arrays.sort(nums);
        return nums;
    }

    public int[] sortArray(int[] nums) {
        qsort(nums, 0, nums.length - 1);
        return nums;
    }

    private void qsort(int[] nums, int low, int high) {
        if(low >= high) return;
        int pivot = pickPivot(low, high);
        pivot = partition(nums, low, high, pivot);
        qsort(nums, low, pivot - 1);
        qsort(nums, pivot + 1, high);
    }

    private int partition(int[] nums, int low, int high, int pivot) {
        swap(nums, pivot, high);
        for (int i = low; i < high; i++) {
            if (nums[i] < nums[high]) swap(nums, low++, i);
        }
        swap(nums, low, high);
        return low;
    }

    private int pickPivot(int low, int high) {
        return (low + high) >> 1;
    }

    private void swap(int[] nums, int a, int b) {
        if (a == b) return;
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    /**
     * 输入：nums = [5,2,3,1]
     * 输出：[1,2,3,5]
     */
    @Test
    public void test1() {
        int[] ans = sortArray(new int[]{5, 2, 3, 1});
        assertThat(ans, is(new int[]{1, 2, 3, 5}));
    }

    /**
     * 输入：nums = [5,1,1,2,0,0]
     * 输出：[0,0,1,1,2,5]
     */
    @Test
    public void test2() {
        int[] ans = sortArray(new int[]{5, 1, 1, 2, 0, 0});
        assertThat(ans, is(new int[]{0, 0, 1, 1, 2, 5}));
    }
}
