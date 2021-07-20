package com.tukeping.leetcode.problems;

/**
 * @author tukeping
 * @date 2021/7/20
 **/
public class LeetCode189 {

    public void rotate(int[] nums, int k) {
        // 7, 6, 5, 4, 3, 2, 1
        // 5, 6, 7, 1, 2, 3, 4
        int n = nums.length;
        k = k % n;
        if (k == 0) return;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int l, int r) {
        while (l < r) {
            swap(nums, l, r);
            l++;
            r--;
        }
    }

    private void swap(int[] nums, int source, int target) {
        int tmp = nums[source];
        nums[source] = nums[target];
        nums[target] = tmp;
    }
}
