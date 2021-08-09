package com.tukeping.practice.algorithms.sorting;

import java.util.Random;

/**
 * @author tukeping
 * @date 2021/8/1
 **/
public class Shuffling {

    Random random = new Random();

    public void shuffle(int[] nums) {
        for (int i = nums.length - 1; i >= 0; i--) {
            int j = random.nextInt(i);
            swap(nums, i, j);
        }
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j) return;
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
