package com.tukeping.leetcode.problems;

/**
 * @author tukeping
 * @date 2021/7/20
 **/
public class LeetCode977 {

    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        int i = 0, j = n - 1, k = n - 1;
        while (i <= j) {
            int i2 = Math.abs(nums[i] * nums[i]);
            int j2 = Math.abs(nums[j] * nums[j]);
            if (i2 > j2) {
                ret[k--] = i2;
                i++;
            } else {
                ret[k--] = j2;
                j--;
            }
        }
        return ret;
    }
}
