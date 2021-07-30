package com.tukeping.practice.sort;

/**
 * @author tukeping
 * @date 14-7-15
 */
public class ShellSort extends Sort {

    public void sort(int[] nums) {
        int n = nums.length;

        int h = 1;
        while (h < n / 3) h = 3 * h + 1;

        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && nums[j] < nums[j - h]; j -= h) {
                    swap(nums, j, j - h);
                }
            }
            h = h / 3;
        }
    }
}
