package com.tukeping.algorithms.sort;

/**
 * @author tukeping
 * @date 14-7-15
 */
public class InsertionSort extends Sort {

    public void sort(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0; j--) {
                if (nums[j] < nums[j - 1]) {
                    swap(nums, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }
}
