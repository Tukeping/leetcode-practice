package com.tukeping.leetcode;

/*
 * @lc app=leetcode.cn id=581 lang=java
 *
 * [581] 最短无序连续子数组
 *
 * https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/description/
 *
 * algorithms
 * Easy (33.24%)
 * Likes:    241
 * Dislikes: 0
 * Total Accepted:    19.3K
 * Total Submissions: 56.6K
 * Testcase Example:  '[2,6,4,8,10,9,15]'
 *
 * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 *
 * 你找到的子数组应是最短的，请输出它的长度。
 *
 * 示例 1:
 * 输入: [2, 6, 4, 8, 10, 9, 15]
 * 输出: 5
 * 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 *
 * 说明 :
 * 输入的数组长度范围在 [1, 10,000]。
 * 输入的数组可能包含重复元素 ，所以升序的意思是<=。
 */

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * array
 *
 * google
 *
 * @author tukeping
 * @date 2020/3/3
 **/
public class LeetCode581 {

    public int findUnsortedSubarray(int[] nums) {
        return mathTrick(nums);
    }

    private int mathTrick(int[] nums) {
        int len = nums.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < len - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                min = Math.min(min, nums[i + 1]);
                max = Math.max(max, nums[i]);
            }
        }

        int pMin = 0;
        for (int i = 0; i < len - 1; i++) {
            if (nums[i] > min) {
                pMin = i;
                break;
            }
        }

        int pMax = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (nums[i] < max) {
                pMax = i;
                break;
            }
        }

        return pMin == pMax ? 0 : pMax - pMin + 1;
    }

    private int cloneSortedArray(int[] nums) {
        int[] snums = nums.clone();
        Arrays.sort(snums);
        int start = nums.length, end = 0;
        for (int i = 0; i < nums.length; i++) {
            if (snums[i] != nums[i]) {
                start = Math.min(start, i);
                end = Math.max(end, i);
            }
        }
        return end >= start ? end - start + 1 : 0;
    }

    /**
     * 输入: [2, 6, 4, 8, 10, 9, 15]
     * 输出: 5
     * 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
     */
    @Test
    public void test1() {
        int n = findUnsortedSubarray(new int[]{2, 6, 4, 8, 10, 9, 15});
        assertThat(n, is(5));
    }
}
