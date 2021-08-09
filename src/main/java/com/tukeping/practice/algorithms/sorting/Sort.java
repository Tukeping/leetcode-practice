package com.tukeping.practice.algorithms.sorting;

import java.util.Random;

/**
 * @author tukeping
 * @date 2021/7/29
 **/
public class Sort {

    public void swap(int[] nums, int a, int b) {
        if (a == b) return;
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    Random random = new Random();

    public int[] shuffle(int[] nums) {
        int n = nums.length;
        for (int i = n - 1; i > 0; i--) {
            int randomIdx = random.nextInt(i); // [0, i-1]
            swap(nums, randomIdx, i);
        }
        int[] copy = new int[n];
        System.arraycopy(nums, 0, copy, 0, n);
        return copy;
    }
}
