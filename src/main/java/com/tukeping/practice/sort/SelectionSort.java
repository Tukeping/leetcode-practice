package com.tukeping.practice.sort;

/**
 * @author tukeping
 * @date 2021/7/29
 **/
public class SelectionSort extends Sort {

    public void sort(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[min]) {
                    min = j;
                }
            }
            swap(nums, i, min);
        }
    }
}
