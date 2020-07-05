package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=33 lang=java
 *
 * [33] 搜索旋转排序数组
 *
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/description/
 *
 * algorithms
 * Medium (36.26%)
 * Likes:    505
 * Dislikes: 0
 * Total Accepted:    72.2K
 * Total Submissions: 198.6K
 * Testcase Example:  '[4,5,6,7,0,1,2]\n0'
 *
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *
 * 你可以假设数组中不存在重复的元素。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 示例 1:
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 *
 * 示例 2:
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 */

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * array | binary-search
 *
 * bloomberg | facebook | linkedin | microsoft | uber
 *
 * @author tukeping
 * @date 2020/2/12
 **/
public class LeetCode33 {

    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) return -1;

        int lo = 0, hi = n - 1;

        if (nums[0] > nums[n - 1]) {
            int l = 0, r = n - 1;
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (nums[mid] >= nums[0]) l = mid;
                else r = mid - 1;
            }

            if (target >= nums[0]) hi = l;
            else lo = l + 1;
        }

        while (lo < hi) {
            int mid = lo + hi >> 1;
            if (nums[mid] < target) lo = mid + 1;
            else hi = mid;
        }
        return nums[lo] == target ? lo : -1;
    }

    public int search3(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) return -1;
        int idx;
        if (nums[0] <= nums[n - 1]) {
            idx = findTarget(nums, 0, n - 1, target);
        } else {
            int rot = findRotation(nums, 0, n - 1);
            if (target >= nums[0]) {
                idx = findTarget(nums, 0, rot, target);
            } else {
                idx = findTarget(nums, rot + 1, n - 1, target);
            }
        }
        return nums[idx] == target ? idx : -1;
    }

    private int findRotation(int[] nums, int lo, int hi) {
        while (lo < hi) {
            int mid = lo + hi + 1 >> 1;
            if (nums[mid] >= nums[0]) lo = mid;
            else hi = mid - 1;
        }
        return lo;
    }

    private int findTarget(int[] nums, int lo, int hi, int target) {
        while (lo < hi) {
            int mid = lo + hi >> 1;
            if (nums[mid] < target) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    /**
     * 你可以假设数组中不存在重复的元素。
     * 你的算法时间复杂度必须是 O(log n) 级别。
     *
     * 做了两次二分搜索
     *
     * time: O(2 * (log n)) => O(log n) space: O(2 * 1) => O(1)
     */
    public int search2(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length == 0) return -1;

        int len = nums.length;

        // rotationIdx = the smallest number
        int rotationIdx = findRotationIndex(nums, len);

        if (rotationIdx == -1) { // not happen rotation
            return bsearch(nums, 0, len - 1, target);
        }

        // base on rotation index split left and right array, all of two array is asc sort
        // find target in left or right array
        boolean targetInLeft = isTargetInLeft(nums, target);

        if (targetInLeft) { // left array binary search target
            return bsearch(nums, 0, rotationIdx, target);
        } else { // right array binary search target
            return bsearch(nums, rotationIdx, len - 1, target);
        }
    }

    private int findRotationIndex(int[] nums, int len) {
        // not happen rotation because of only one number
        if (len == 1) return -1;

        int low = 0, high = len - 1, mid;

        while (low <= high) {
            mid = (low + high) >> 1;
            if (mid == len - 1) {
                return -1; // rotation not found
            } else if (nums[mid] > nums[mid + 1]) {
                return mid + 1; // rotation found
            } else { // nums[mid] <= nums[mid+1]
                if (nums[mid] >= nums[0]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }

        return -1;
    }

    /** true = left, false = right **/
    private boolean isTargetInLeft(int[] nums, int target) {
        return target >= nums[0];
    }

    /** 二分搜索算法 time: O(log n) space: O(1) **/
    private int bsearch(int[] nums, int start, int end, int target) {
        int low = start, high = end, mid;

        while (low <= high) {
            mid = (low + high) / 2; // (low+high) >> 1
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else { // nums[mid] < target
                low = mid + 1;
            }
        }

        // 没找到
        return -1;
    }

    /**
     * 示例 1:
     * 输入: nums = [4,5,6,7,0,1,2], target = 0
     * 输出: 4
     */
    @Test
    public void test1() {
        int n = search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0);
        assertThat(n, is(4));
    }

    /**
     * 示例 2:
     * 输入: nums = [4,5,6,7,0,1,2], target = 3
     * 输出: -1
     */
    @Test
    public void test2() {
        int n = search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3);
        assertThat(n, is(-1));
    }

    /**
     * 输入: nums = [1], target = 0
     * 输出: -1
     */
    @Test
    public void test3() {
        int n = search(new int[]{1}, 0);
        assertThat(n, is(-1));
    }

    /**
     * 输入: nums = [1], target = 1
     * 输出: 0
     */
    @Test
    public void test4() {
        int n = search(new int[]{1}, 1);
        assertThat(n, is(0));
    }

    /**
     * 输入: nums = [1,3], target = 3
     * 输出: 1
     */
    @Test
    public void test5() {
        int n = search(new int[]{1, 3}, 3);
        assertThat(n, is(1));
    }

    /**
     * 输入: nums = [3,1], target = 1
     * 输出: 1
     */
    @Test
    public void test6() {
        int n = search(new int[]{3, 1}, 1);
        assertThat(n, is(1));
    }

    /**
     * 输入: nums = [5,1,3], target = 5
     * 输出: 0
     */
    @Test
    public void test7() {
        int n = search(new int[]{5, 1, 3}, 5);
        assertThat(n, is(0));
    }
}
