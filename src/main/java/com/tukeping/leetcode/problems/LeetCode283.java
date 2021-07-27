package com.tukeping.leetcode.problems;

/*
 * @lc app=leetcode.cn id=283 lang=java
 *
 * [283] 移动零
 *
 * https://leetcode-cn.com/problems/move-zeroes/description/
 *
 * algorithms
 * Easy (58.34%)
 * Likes:    503
 * Dislikes: 0
 * Total Accepted:    111.5K
 * Total Submissions: 187.1K
 * Testcase Example:  '[0,1,0,3,12]'
 *
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 *
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 *
 * 说明:
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 */

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * array | two-pointers
 *
 * bloomberg | facebook
 *
 * @author tukeping
 * @date 2020/2/27
 **/
public class LeetCode283 {

    public void moveZeroesV2(int[] nums) {
        int size = nums.length;
        if (size == 1) return;
        int zeroIndex = 0, nonZeroIndex = 0;
        while (true) {
            while (zeroIndex < size) {
                if (nums[zeroIndex] == 0) break;
                else zeroIndex++;
            }
            if (zeroIndex >= size) break;
            while (nonZeroIndex < size) {
                if (nums[nonZeroIndex] != 0) break;
                else nonZeroIndex++;
            }
            if (nonZeroIndex >= size) break;
            if (zeroIndex > nonZeroIndex) {
                nonZeroIndex = zeroIndex + 1;
            } else {
                swap(nums, zeroIndex, nonZeroIndex);
            }
        }
    }

//    private void swap(int[] nums, int source, int target) {
//        int tmp = nums[source];
//        nums[source] = nums[target];
//        nums[target] = tmp;
//    }

    public void moveZeroes(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (nums[i] == 0 && nums[j] != 0) {
                    swap(nums, i, j);
                    break;
                }
            }
        }
    }

    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    @Test
    public void test1() {
        int[] nums = new int[]{0, 1, 0, 3, 12};
        moveZeroes(nums);
        assertThat(nums, is(new int[]{1, 3, 12, 0, 0}));
    }
}
