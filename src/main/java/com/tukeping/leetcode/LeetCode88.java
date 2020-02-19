package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=88 lang=java
 *
 * [88] 合并两个有序数组
 *
 * https://leetcode-cn.com/problems/merge-sorted-array/description/
 *
 * algorithms
 * Easy (45.97%)
 * Likes:    417
 * Dislikes: 0
 * Total Accepted:    108.5K
 * Total Submissions: 232.8K
 * Testcase Example:  '[1,2,3,0,0,0]\n3\n[2,5,6]\n3'
 *
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 *
 * 说明:
 *
 *
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *
 *
 * 示例:
 *
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * 输出: [1,2,2,3,5,6]
 *
 */

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * array | two-pointers
 *
 * bloomberg | facebook | microsoft
 *
 * frequency 5
 *
 * @author tukeping
 * @date 2020/2/16
 **/
public class LeetCode88 {

    /*
     * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
     * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
     */

    /*
     * 59/59 cases passed (1 ms)
     * Your runtime beats 31.61 % of java submissions
     * Your memory usage beats 5.1 % of java submissions (41.8 MB)
     */

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1 == null) nums1 = nums2;
        if (nums2 == null) return;
        if (m + n == 0) return;

        int p1 = 0, p2 = 0;
        while (m > 0) {
            while (p2 < n) {
                if (nums2[p2] < nums1[p1]) {
                    System.arraycopy(nums1, p1, nums1, p1 + 1, m);
                    nums1[p1++] = nums2[p2++];
                } else {
                    break;
                }
            }
            if (p2 == n)
                break;
            p1++;
            m--;
        }

        while (p2 < n) {
            nums1[p1++] = nums2[p2];
            p2++;
        }
    }

    /**
     * 输入:
     * nums1 = [1,2,3,0,0,0], m = 3
     * nums2 = [2,5,6],       n = 3
     * 输出: [1,2,2,3,5,6]
     */
    @Test
    public void test1() {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        merge(nums1, 3, nums2, 3);
        assertThat(nums1, is(new int[]{1, 2, 2, 3, 5, 6}));
    }

    @Test
    public void test2() {
        int[] nums1 = {0};
        int[] nums2 = {1};
        merge(nums1, 0, nums2, 1);
        assertThat(nums1, is(new int[]{1}));
    }

    @Test
    public void test3() {
        int[] nums1 = {1, 2, 4, 5, 6, 0};
        int[] nums2 = {3};
        merge(nums1, 5, nums2, 1);
        assertThat(nums1, is(new int[]{1, 2, 3, 4, 5, 6}));
    }

    @Test
    public void test4() {
        int[] nums1 = {4, 5, 6, 0, 0, 0};
        int[] nums2 = {1, 2, 3};
        merge(nums1, 3, nums2, 3);
        assertThat(nums1, is(new int[]{1, 2, 3, 4, 5, 6}));
    }

    @Test
    public void test5() {
        int[] nums1 = {0, 0, 3, 0, 0, 0, 0, 0, 0};
        int[] nums2 = {-1, 1, 1, 1, 2, 3};
        merge(nums1, 3, nums2, 6);
        assertThat(nums1, is(new int[]{-1, 0, 0, 1, 1, 1, 2, 3, 3}));
    }
}
