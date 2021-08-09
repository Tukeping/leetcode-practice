package com.tukeping.leetcode.problems;

/**
 * @author tukeping
 * @date 2021/8/2
 **/
public class LeetCode1953 {

    public long numberOfWeeks(int[] nums) {
        int n = nums.length;
        long sum = 0, max = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            max = Math.max(max, nums[i]);
        }
        long rest = sum - max;
        if (max > rest) {
            return 2 * rest + 1;
        } else {
            return sum;
        }
    }
}
