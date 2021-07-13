package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=31 lang=java
 *
 * [31] 下一个排列
 *
 * https://leetcode-cn.com/problems/next-permutation/description/
 *
 * algorithms
 * Medium (32.33%)
 * Likes:    396
 * Dislikes: 0
 * Total Accepted:    44.4K
 * Total Submissions: 135.1K
 * Testcase Example:  '[1,2,3]'
 *
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须原地修改，只允许使用额外常数空间。
 *
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * array
 *
 * google
 *
 * @author tukeping
 * @date 2020/2/27
 **/
public class LeetCode31 {

    public void nextPermutation(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return;

        int len = nums.length, i = len - 1;

        for (; i >= 1; i--) { // 从尾部到头部
            if (nums[i] > nums[i - 1]) { // 非降序
                int p = bsearch(nums, i, len - 1, nums[i - 1]); // 二分查找
                swap(nums, i - 1, p); // 交换位置
                if (len - 1 - i > 0) { // 需要反转的元素大于1个时才需要执行反转
                    reversed(nums, i, len - 1); // 反转数组元素
                }
                break;
            }
        }

        if (i == 0) { // 整个数组就是降序的, 将整个数组进行反转
            reversed(nums, 0, len - 1);
        }
    }

    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    private int bsearch(int[] nums, int start, int end, int target) {
        if (start > end) return end;

        int mid = (start + end) / 2;

        if (nums[mid] > target) {
            return bsearch(nums, mid + 1, end, target);
        } else { // nums[mid] <= target
            return bsearch(nums, start, mid - 1, target);
        }
    }

    private void reversed(int[] nums, int start, int end) {
        for (int i = start, j = end; j > i; i++, j--) {
            swap(nums, i, j);
        }
    }

    /**
     * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
     * 1,2,3 → 1,3,2
     * 3,2,1 → 1,2,3
     * 1,1,5 → 1,5,1
     */
    @Test
    public void test1() {
        int[] nums1 = new int[]{1, 2, 3};
        nextPermutation(nums1);
        assertThat(nums1, is(new int[]{1, 3, 2}));

        nums1 = new int[]{3, 2, 1};
        nextPermutation(nums1);
        assertThat(nums1, is(new int[]{1, 2, 3}));

        nums1 = new int[]{1, 1, 5};
        nextPermutation(nums1);
        assertThat(nums1, is(new int[]{1, 5, 1}));
    }

    @Test
    public void test2() {
        int[] nums = new int[]{1, 5, 8, 4, 7, 6, 5, 3, 1};
        nextPermutation(nums);
        assertThat(nums, is(new int[]{1, 5, 8, 5, 1, 3, 4, 6, 7}));
    }

    @Test
    public void test3() {
        int[] nums = new int[]{1, 3, 2};
        nextPermutation(nums);
        assertThat(nums, is(new int[]{2, 1, 3}));
    }

    @Test
    public void test4() {
        int[] nums = new int[]{1, 5, 1};
        nextPermutation(nums);
        assertThat(nums, is(new int[]{5, 1, 1}));
    }

    @Test
    public void test5() {
        int[] nums = new int[]{2, 2, 7, 5, 4, 3, 2, 2, 1};
        nextPermutation(nums);
        assertThat(nums, is(new int[]{2, 3, 1, 2, 2, 2, 4, 5, 7}));
    }
}
