package com.tukeping.leetcode.problems;

import java.util.Arrays;

/**
 * @author tukeping
 * @date 2021/8/17
 **/
public class LeetCode1968 {

    public int[] rearrangeArray(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] ret = new int[n];
        int k = 0;
        for (int i = 0; i < n; i += 2) ret[i] = nums[k++];
        for (int i = 1; i < n; i += 2) ret[i] = nums[k++];
        return ret;
    }
}
