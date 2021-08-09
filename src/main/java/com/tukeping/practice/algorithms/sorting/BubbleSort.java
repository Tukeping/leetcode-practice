package com.tukeping.practice.algorithms.sorting;

/**
 * @author tukeping
 * @date 14-7-15
 */
public class BubbleSort extends Sort {

    public void sort(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] > nums[j]) {
                    swap(nums, i, j);
                }
            }
        }
    }
}
